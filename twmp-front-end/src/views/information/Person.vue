<template>
  <div class="person-page search-table-page" v-ceiecScrollbar>
    <div class="tool-bar">
      <div class="button-bar">
        <el-tooltip class="item" effect="dark" content="添加新用户" placement="bottom">
          <el-button class="ceiec-default" @click="add()">
            <svg class="icon" aria-hidden="true" width="24" height="24">
                <use xlink:href="#icontianjiayongh"></use>
            </svg>
          </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="下载数据模板" placement="bottom">
          <el-button class="ceiec-default">
            <svg class="icon" aria-hidden="true" width="24" height="24">
                <use xlink:href="#iconmubanxiaz"></use>
            </svg>
          </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="批量数据导出" placement="bottom">
          <el-button class="ceiec-default">
            <svg class="icon" aria-hidden="true" width="24" height="24">
                <use xlink:href="#iconpiliangdaochu"></use>
            </svg>
          </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="批量数据导入" placement="bottom">
          <el-button class="ceiec-default">
            <svg class="icon" aria-hidden="true" width="24" height="24">
                <use xlink:href="#iconexporticon"></use>
            </svg>
          </el-button>
        </el-tooltip>
      </div>
      <div class="search-bar">
        <!-- 组织机构查询框 -->
        <popup-department @treeCurrentChange="treeCurrentChange" :popupWidth="360" :popupSize="'normal'"></popup-department>
        <!-- 姓名及人员编号查询框 -->
        <el-input placeholder="请输入内容" v-model="sNameNumber" size="normal" class="ceiec-select-input">
          <el-select v-model="sNameNumberSelect" placeholder="" slot="prepend" popper-class="ceiec-select-input-popper">
            <template slot="prefix">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use :xlink:href="sNameNumberSelect === '1' ? '#iconrenyuanmingzicopy' : '#iconanjianbhao'"></use>
              </svg>
            </template>
            <el-option value="1" label=" ">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use xlink:href="#iconrenyuanmingzicopy"></use>
              </svg>
              姓名
            </el-option>
            <el-option value="2" label=" ">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use xlink:href="#iconanjianbhao"></use>
              </svg>
              被监控人编号
            </el-option>
          </el-select>
        </el-input>
        <!-- 监控状态查询框 -->
        <el-select v-model="personStatus" 
          placeholder="请选择" size="normal">
            <el-option label="未监控" value="1"></el-option>
            <el-option label="监控中" value="2"></el-option>
            <el-option label="监控结束" value="3"></el-option>
        </el-select>
        <el-button class="ceiec-query" @click="personQuery()">
          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
              <use xlink:href="#iconsearchicon"></use>
          </svg>
        </el-button>
      </div>
    </div>
    <!-- 用户列表信息区域 -->
    <div class="no-data-tip" v-show="isEmpty || isLoading">
      <div class="empty" v-show="isEmpty">
        <img src="/images/empty.png" alt="loading">
        <p>{{$t('table_no_data')}}</p>
      </div>
      <div class="loading" v-show="isLoading">
        <img src="/images/loading.svg" alt="loading">
      </div>
    </div>
    <div class="person-table" v-show="!isEmpty && !isLoading" v-ceiecScrollbar>
      <div class="user-info-block" v-for="item in personList" :key="item.personId">
        <div class="status-icon" :style="'background:' + personStatusEnum[item.personStatus - 1].bgColor">
          <svg aria-hidden="true" width="28" height="28" :fill="personStatusEnum[item.personStatus - 1].svgColor">
              <use :xlink:href="personStatusEnum[item.personStatus - 1].svg"></use>
          </svg>
        </div>
        <div class="user-header">
          <img v-if="item.personUrl" :src="webBaseUrl + '/downloadFile/false/' + getUserHeaderPhoto(item.personUrl)" width="50" height="50" class="user-avatar"/>
          <div  v-else class="user-avatar">
            <svg aria-hidden="true" width="28" height="32" fill="#666681">
              <use xlink:href="#iconrenyuanmingzicopy"></use>
            </svg>
          </div>
          <div class="missing-info" v-if="!item.personUrl">
            <el-tooltip class="item" effect="dark" content="无头像" placement="top-start">
              <svg aria-hidden="true" width="16" height="16">
                <use xlink:href="#iconyichang"></use>
              </svg>
            </el-tooltip>
          </div>
        </div>
        <div class="user-info">
          <div class="column1">
            <div class="title name">{{ item.personName }}</div>
            <div class="content">{{ item.personNumber }}</div>
          </div>
          <div class="column2">
            <div class="title">性别</div>
            <div class="content">{{ item.gender}}</div>
          </div>
          <div class="column3">
            <div class="title">出生日期</div>
            <div class="content">{{ item.birthDate }}</div>
          </div>
          <div class="column4">
            <div class="title">身份证号</div>
            <div class="content">{{ item.personIdCard }}</div>
          </div>
          <div class="column5">
            <div class="title">联系电话</div>
            <div class="content">{{ item.phone }}</div>
          </div>
          <div class="column6">
            <div class="title">组织机构</div>
            <div class="content">xxxxxx</div>
          </div>
          <div class="column7">
            <div class="title">监控状态</div>
            <div class="content" :style="'color:' + personStatusEnum[item.personStatus - 1].svgColor">
              {{ personStatusEnum[item.personStatus - 1].statusName }}
            </div>
          </div>
        </div>
        <div class="user-operation">
          <span class="svg-button" @click="detail(item)">
            <svg aria-hidden="true" width="28" height="28">
              <use xlink:href="#iconlistdetailicon"></use>
            </svg>
          </span>
          <span class="svg-button" @click="edit(item)">
            <svg aria-hidden="true" width="28" height="28">
              <use xlink:href="#iconediticon"></use>
            </svg>
          </span>
          <span class="svg-button" @click="del(item)">
            <svg aria-hidden="true" width="28" height="28">
              <use xlink:href="#icondeleteicon"></use>
            </svg>
          </span>
        </div>
      </div>
    </div>
    <!-- 分页显示区域 -->
    <div class="pagination">
      <el-pagination
        @current-change="messageQuery(false)"
        :current-page.sync="pageInfo.currentPage"
        :page-size="pageInfo.pageSize"
        layout="prev, pager, next, total"
        :total="pageInfo.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { personListApi, personDataApi, personDelApi, personImportApi, personExportApi} from '@/http/api/information'
import PopupDepartment from '@/components/PopupDepartment'
import baseUrl from '@/utils/baseUrl'
export default {
  data() {
    return {
      webBaseUrl: baseUrl,
      departmentId: null,
      personStatus: null,
      sNameNumber: '',
      sNameNumberSelect: "1",
      personList: [],
      isLoading: true,
      pageInfo: {
        currentPage: 1,
        pageSize: 20,
        total: 0
      },
      personStatusEnum: [
        {bgColor: '#3C3C65', svg: '#iconweijiankong', svgColor: '#616198', statusName: '未监控'},
        {bgColor: '#38506B', svg: '#iconjiankongzhong', svgColor: '#4ED8BB', statusName: '监控中'},
        {bgColor: '#483C8E', svg: '#iconjiankongwancheng', svgColor: '#7E68FF', statusName: '监控完成'}
      ],
      personDataFlag: false
    }
  },
  components: {
    PopupDepartment
  },
  computed: {
    isEmpty () {
      return !this.personList.length && !this.isLoading
    }
  },
  methods: {
    // 查询被监控人-返回列表
    personQuery () {
      let postData = {
        personName: this.sNameNumberSelect === '1' ? this.sNameNumber : '',
        personNumber: this.sNameNumberSelect === '2' ? this.sNameNumber : '',
        departmentId: this.departmentId,
        personStatus: this.personStatus,
        pageNo: this.pageInfo.currentPage,
        pageSize: this.pageInfo.pageSize
      }
      this.isLoading = true
      personListApi(postData).then((res) => {
        this.isLoading = false
        if (res.data.code === 10000) {
          this.personList = res.data.data.items
        }
      })
    },
    // 多个用户头像显示第一个
    getUserHeaderPhoto (photoUrl) {
      let headerId = ''
      if (photoUrl) {
        headerId = photoUrl.split(',')[0]
      }
      return headerId
    },
    // 选取组织机构
    treeCurrentChange (e) {
      this.departmentId = e.departmentId
    },
    // 添加被监控人信息
    add () {
      this.$router.push({ path: '/information/personData/add', query: {id: ""} })
    },
    // 被监控人详情查看
    detail (item) {
      this.$router.push({ path: '/information/personData/detail', query: {id: item.personId} })
    },
    // 被监控人信息编辑
    edit (item) {
      this.$router.push({ path: '/information/personData/edit', query: {id: item.personId} })
    },
    // 被监控人删除
    del (item) {
      if (item.personId) {
        this.$confirm('确定要删除吗', '提示', {
          type: 'warning'
        }).then(() => {
          personDelApi(item.personId).then((res) => {
            if (res.data.code === 10000) {
              this.$message.success('删除成功')
              this.personQuery()
            } else {
              this.$message.error('删除失败')
            }
          })
        })
      } else {
        this.$message.error('删除异常无此信息')
      }
    }
  },

  mounted () {
    this.personQuery()
  }
}
</script>

<style lang="stylus" scoped>
.person-page
  .person-table
    padding 0px 28px
    position absolute
    width calc(100% - 60px)
    height calc(100% - 110px)
    .user-info-block
      display flex
      justify-content flex-start
      align-items center
      width 100%
      height 94px
      background-image linear-gradient(135deg, #2A2949 0%, #252446 100%)
      border-radius 6px
      flex-wrap nowrap
      flex-direction row
      margin-bottom 8px
      &:hover
        background-image linear-gradient(135deg, #373560 0%, #2A294E 100%)
        .user-info
          .title
            color #9A9AC6
            &.name
              color #FFFFFF
          .content
            color #FFFFFF
      .status-icon
        width 69px
        height 100%
        text-align center
        border-radius 6px 0 0 6px
        flex-shrink 0
        svg
          margin-top 33px
      .user-header
        width 106px
        flex-shrink 0
        position relative
        .user-avatar
          margin-left 34px
          width 50px
          height 50px
          border-radius 50%
          opacity: 0.9
          background #3D3D64
          text-align center
          svg
            margin-top 9px
        .missing-info
          position absolute
          top 0px
          right 18px
      .user-info
        width calc(100% - 158px)
        display flex
        align-items center
        font-size 13px
        letter-spacing 0
        .title
          color #73739A
          margin-bottom 8px
          &.name
            color #9A9AC6
            font-weight bold
        .content
          color #9A9AC6
        .column1
          width 15%
        .column2
          width 10%
        .column3
          width 15%
        .column4
          width 15%
        .column5
          width 15%
        .column6
          width 20%
        .column7
          width 10%
      .user-operation
        width 158px
        height 58px
        display flex
        flex-shrink 0
        border-left 1px solid rgba(255,255,255,0.10)
        align-items center
        justify-content space-between
        padding 0px 24px 0px 28px
        box-sizing border-box
</style>