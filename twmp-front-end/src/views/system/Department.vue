<template>
  <div class="department-page">
    <div class="department-header">
      组织机构管理
    </div>
    <div class="department-content">
      <!-- 组织机构树 -->
      <div class="department-tree">
        <div class="department-tree-header">
          <div class="title">组织机构列表</div>
          <el-input v-model="departmentSearch" placeholder="输入查询值" suffix-icon="el-icon-search">
          </el-input>
        </div>
        <el-tree
          ref="departmentTree"
          :data="departmentData"
          node-key="departmentId"
          default-expand-all
          :expand-on-click-node="false"
          :props="customProps"
          :filter-node-method="departmentFilter"
          :highlight-current="true"
          @node-click="detail"
          v-ceiecScrollbar>
          <span :class="['department-tree-node', {'new-child': data.department == null}]" slot-scope="{ node, data }">
            <span>{{ data.departmentName }}</span>
            <span>
            <span class="svg-button">
              <svg v-if="data.departmentId != null" class="icon" aria-hidden="true" width="20" height="20" @click.stop="() => add(node, data)">
                <use xlink:href="#iconaddicon"></use>
              </svg>
            </span>
            <span class="svg-button">
              <svg v-if="data.parentId != 0" class="icon" 
                aria-hidden="true" width="20" height="20" @click.stop="() => remove(node, data)">
                <use xlink:href="#icondeleteicon"></use>
              </svg>
            </span>
            </span>
          </span>
        </el-tree>
      </div>
      <!-- 组织机构地图、详情、新增、编辑功能区 -->
      <div class="department-data">
        <div class="department-map">
          <div class="map-disabled" v-show="showDetail"></div>
          <div class="map-center-top" v-show="!showDetail">
            <div class="title">设置地图开始位置</div>
          </div>
          <div class="map-center-bottom">
            <div class="map-center-bottom-font">
            当前地图中心位置<br/>
            经度：<span>{{ defaultMapCenter.center.x }}</span> 
            纬度：<span>{{ defaultMapCenter.center.y }}</span>
            缩放层级：<span>{{ defaultMapCenter.zoom }}</span>
            </div>
          </div>
          <div class="map-container" id="mapContainer" ref="mapContainer">
            <ceiec-map mapContainer="mapContainer" v-on:showMapCenter="showMapCenter" ref="departmentMap"></ceiec-map>
          </div>
        </div>
        <div class="department-form">
            <el-form v-show="!showDetail" ref="departmentForm" 
              label-position="top" 
              :model="departmentForm"
              :rules="departmentRules">
              <el-form-item label="组织机构名称" prop="departmentName">
                <el-input v-model="departmentForm.departmentName" :disabled="isEdit"></el-input>
              </el-form-item>
              <el-form-item label="组织机构代码" prop="departmentCode">
                <el-input v-model="departmentForm.departmentCode" :disabled="isEdit"></el-input>
              </el-form-item>
              <el-form-item label="上级组织" class="is-required">
                <el-input v-model="departmentForm.parentId" disabled></el-input>
              </el-form-item>
              <el-form-item label="联系人">
                <el-input v-model="departmentForm.contacts" :disabled="isEdit"></el-input>
              </el-form-item>
              <el-form-item label="联系电话">
                <el-input v-model="departmentForm.phone" :disabled="isEdit"></el-input>
              </el-form-item>
              <el-form-item label="传真号码">
                <el-input v-model="departmentForm.fax" :disabled="isEdit"></el-input>
              </el-form-item>
              <el-form-item label="地址">
                <el-input v-model="departmentForm.address" :disabled="isEdit"></el-input>
              </el-form-item>
              <div class="department-submit">
                <el-button @click="resetDepartment" class="ceiec-default">
                  <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                    <use xlink:href="#iconcancel"></use>
                  </svg>
                </el-button>
                <el-button @click="submitDepartment" class="ceiec-primary">
                  <svg class="icon" aria-hidden="true" width="28" height="28" fill="#FFFFFF">
                    <use xlink:href="#iconconfirm"></use>
                  </svg>
                </el-button>
              </div>
            </el-form>
            <el-form v-show="showDetail" class="department-form-detail" ref="departmentFormDetail" label-position="top" label-width="200px" :model="departmentForm">
              <el-form-item label="组织机构名称">
                <span>{{ departmentForm.departmentName }}</span>
              </el-form-item>
              <el-form-item label="组织机构代码">
                <span>{{ departmentForm.departmentCode }}</span>
              </el-form-item>
              <el-form-item label="上级组织">
                <span>{{ departmentForm.parentId }}</span>
              </el-form-item>
              <el-form-item label="联系人">
                <span>{{ departmentForm.contacts }}</span>
              </el-form-item>
              <el-form-item label="联系电话">
                <span>{{ departmentForm.phone }}</span>
              </el-form-item>
              <el-form-item label="传真号码">
                <span>{{ departmentForm.fax }}</span>
              </el-form-item>
              <el-form-item label="地址">
                <span>{{ departmentForm.address }}</span>
              </el-form-item>
              <el-button @click="edit" class="ceiec-primary department-edit">
                <svg class="icon" aria-hidden="true" width="28" height="28" fill="#FFFFFF">
                  <use xlink:href="#iconeditbutton"></use>
                </svg>
              </el-button>
            </el-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import CeiecMap from '@/components/CeiecMap/Index'
import { departmentDataApi, departmentDelApi } from '@/http/api/system'
import { getUserInfoApi } from '@/http/api/user'
export default {
  components: {
    CeiecMap
  },
  data () {
    return {
      showDetail: true,
      departmentSearch: '',
      isEdit: true,
      departmentData: [],
      customProps: {
        label: 'departmentName',
        children: 'childrenList'
      },
      departmentForm: {
        departmentId: null,
        departmentName: '',
        departmentCode: '',
        parentId: '',
        contacts: '',
        phone: '',
        fax: '',
        address: '',
        mapCenter: '',
        level: null
      },
      departmentRules: {
        departmentCode: {required: true, message: '必填项', trigger: 'blur'},
        departmentName: {required: true, message: '必填项', trigger: 'blur'}
      },
      defaultMapCenter: {
        center: {
          x: 0,
          y: 0
        },
        zoom: 0
      }
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  watch: {
    departmentSearch(val) {
      this.$refs.departmentTree.filter(val)
    }
  },
  methods: {
    ...mapActions(['updateDepartment']),
    // 前端组织机构过滤
    departmentFilter(val, data) {
      return data.departmentName.indexOf(val) !== -1
    },
    // 查看组织机构详情
    detail (data) {
      this.showDetail = true
      this.departmentForm.departmentId = data.departmentId
      this.departmentForm.departmentName = data.departmentName
      this.departmentForm.departmentCode = data.departmentCode || '-'
      this.departmentForm.parentId = data.parentId
      this.departmentForm.contacts = data.contacts || '-'
      this.departmentForm.phone = data.phone || '-'
      this.departmentForm.fax = data.fax || '-'
      this.departmentForm.mapCenter = data.mapCenter
      this.departmentForm.address = data.address || '-'
      if (data.mapCenter != null) {
        this.$refs.departmentMap.setDefalutMapCenter(JSON.parse(data.mapCenter))
      }
    },
    // 进入新增组织机构操作
    add (node, data) {
      const newChild = { departmentId: null, departmentName: '<empty>', parentId: data.departmentId, childrenList: [] }
      // if (!data.childrenList) {
      //   this.$set(data, 'childrenList', [])
      // }
      // data.childrenList.push(newChild)
      this.$refs.departmentTree.append(newChild, node)
      this.showDetail = false
      this.isEdit = false
      this.departmentForm.departmentId = null
      this.departmentForm.departmentName = '<empty>'
      this.departmentForm.departmentCode = ''
      this.departmentForm.parentId = data.departmentId
      this.departmentForm.contacts = ''
      this.departmentForm.phone = ''
      this.departmentForm.fax = ''
      this.departmentForm.address = ''
      this.departmentForm.level = 5
      this.departmentForm.mapCenter = JSON.stringify(this.defaultMapCenter)
      if (data.mapCenter != null) {
        this.$refs.departmentMap.setDefalutMapCenter(JSON.parse(data.mapCenter))
      }
    },
    // 进入编辑组织机构操作
    edit () {
      this.isEdit = false
      this.showDetail = false
    },
    // 删除当前组织机构
    remove (node, data) {
      if (data.departmentId == null) {
        const parent = node.parent
        const children = parent.data.childrenList || parent.data
        const index = children.findIndex(d => d.departmentId === data.departmentId)
        children.splice(index, 1)
        return
      }
      this.$confirm('确定要删除吗', '提示', {
        type: 'warning'
      }).then(() => {
        departmentDelApi(data.departmentId).then((res) => {
          if (res.data.code === 10000) {
            this.updateDepartmentList()
            this.$message({
              message: '删除成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '删除失败',
              type: 'error'
            })
          }
        })
      }).catch(()=>{})
    },
    // 新增组织机构数据提交后端
    submitDepartment () {
      departmentDataApi(this.departmentForm).then((res) => {
        if (res.data.code === 10000){
          this.$message({
            message: '新增成功',
            type: 'success'
          })
          this.updateDepartmentList()
        } else {
          this.$message({
            message: '添加失败',
            type: 'error'
          })
        }
      })
    },
    // 添加删除组织机构后更新组织机构数据
    updateDepartmentList () {
      getUserInfoApi().then((res) => {
        this.updateDepartment(res.data.data.departmentTree)
        this.departmentData = []
        this.departmentData.push(JSON.parse(JSON.stringify(res.data.data.departmentTree)))
      })
    },
    // 编辑操作里重置写入数据
    resetDepartment () {
      this.departmentForm.departmentName = ''
      this.departmentForm.contacts = ''
      this.departmentForm.phone = ''
      this.departmentForm.fax = ''
      this.departmentForm.address = ''
    },
    // 动态更改地图中心点显示
    showMapCenter (data) {
      this.defaultMapCenter = data
      this.departmentForm.mapCenter = JSON.stringify(data)
    }
  },
  mounted () {
    //初始显示地图位置信息
    this.defaultMapCenter.center.x = this.globalConfig.ceiecMapOptions.center.x
    this.defaultMapCenter.center.y = this.globalConfig.ceiecMapOptions.center.y
    this.defaultMapCenter.zoom = this.globalConfig.ceiecMapOptions.zoom
    // 获取组织结构列表
    let data = this.globalConfig.department
    this.departmentForm.departmentName = data.departmentName
    this.departmentForm.departmentCode = data.departmentCode || '-'
    this.departmentForm.parentId = data.parentId
    this.departmentForm.contacts = data.contacts || '-'
    this.departmentForm.phone = data.phone || '-'
    this.departmentForm.fax = data.fax || '-'
    this.departmentForm.address = data.address || '-'
    this.departmentForm.mapCenter = data.mapCenter || JSON.stringify(this.defaultMapCenter)
    if (data.mapCenter != null) {
      this.$refs.departmentMap.setDefalutMapCenter(JSON.parse(data.mapCenter))
    }
    this.departmentData.push(JSON.parse(JSON.stringify(data)))
  }
}
</script>

<style lang="stylus" scoped>
.department-page
  padding 65px 90px 0px 90px
  .department-header
    background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
    border-radius 6px
    width 100%
    height 67px
    font-family Arial-BoldMT
    font-size 20px
    color #FFFFFF
    letter-spacing 0
    text-align center
    line-height 67px
    margin-bottom 40px
  .department-content
    display flex
    height 650px
    .department-tree
      background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
      border-radius 6px
      margin-right 13px
      .department-tree-header
        padding 0px 24px
        .title
          width 336px
          font-size 14px
          margin 24px 0px 14px 0px
          padding-bottom 14px
          color #9A9AC6
          letter-spacing 0
          border-bottom 1px solid rgba(255,255,255,0.10)
          margin-bottom 15px
      .el-input
        width 100%
        margin-bottom 10px
        .el-input__inner
          background #16142C
          border-radius 4px
      .department-tree-node
        flex: 1
        display: flex
        align-items: center
        justify-content: space-between
        font-size: 14px
    .department-data
      width 100%
      position relative
      .department-map
        width 100%
        .map-disabled
          position absolute
          z-index 401
          width calc(100% - 383px)
          height calc(100% - 72px)
          background rgba(0, 0, 0, 0)
        .map-center-top
          position absolute
          z-index 401
          width calc(100% - 383px)
          background rgba(56,55,86,0.70)
          height 44px
          line-height 44px
          text-align center
          .title
            font-size 16px
            color #FFFFFF
            letter-spacing: 0
        .map-center-bottom
          position absolute
          z-index 401
          background rgba(56,55,86,0.7)
          height 72px
          bottom 0px
          width calc(100% - 383px)
          text-align center
          color #FFFFFF
          line-height 26px
          font-weight 100
          font-size 14px
          .map-center-bottom-font
            margin-top 10px
            > span
              font-weight bold
              margin-right 15px
              font-family Arial-BoldMT
        .map-container
          position absolute !important
          width 100%
          height 100%
          border-radius 6px
      .department-form
        position absolute
        right 0px
        z-index 400
        width 335px
        background rgba(25,23,48,0.94);
        border-radius 0 6px 6px 0
        padding 0px 24px
        height 100%
        .el-form
          margin-top 15px
          &.department-form-detail
            .el-form-item
              border-bottom 1px solid rgba(255,255,255,0.10)
              padding-bottom 15px
              .el-form-item__content > span
                margin-left 10px !important
              &:last-child
                border none
          .department-edit,.department-submit
            position absolute
            bottom 40px
            right 24px
          
</style>
<style lang="stylus">
.department-page
  .el-tree
    background none
    height 510px
    .el-tree-node__content
      height 32px
      padding-left 18px
      padding-right 24px
  .el-tree--highlight-current 
    .el-tree-node.is-current > .el-tree-node__content
      background-color #383756
      color #FFFFFF
</style>