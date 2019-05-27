<template>
    <div id="userListPage">
        <el-row class="query-row" :gutter="20">
            <el-col :span="1">
                <el-button class="user-add-btn" @click="addUser">
                  <svg class="icon" aria-hidden="true" width="24" height="24" fill="#9A9AC6">
                      <use xlink:href="#icontianjiayongh"></use>
                  </svg>
                </el-button>
            </el-col>
            <el-col :span="5" :offset="11">
                <el-input class="user-name-input" v-model="userName" placeholder="用户名">
                    <svg class="icon" aria-hidden="true" width="24" height="24" fill="#9A9AC6">
                        <use xlink:href="#icontianjiayongh"></use>
                    </svg>
                </el-input>
            </el-col>
            <el-col :span="5">
                <el-select class="user-state-sel" v-model="userState" placeholder="用户状态">
                  <el-option 
                  v-for="item in userStatesLib" 
                  :key="item.value" 
                  :label="item.label" 
                  :value="item.value">
                  </el-option>
                </el-select>
            </el-col>
            <el-col :span="2" style="text-align: right">
                <el-button class="ceiec-query" @click="query">
                  <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                      <use xlink:href="#iconsearchicon"></use>
                  </svg>
                </el-button>
            </el-col>
        </el-row>
        <el-row class="table-row">
          <el-table
            id="user-list-table"
            :data="userListData"
            style="width: 100%"
            stripe
            size="medium">
            <el-table-column
              label="Nombre de usuario"
              width="250">
              <template slot-scope="scope">
                <el-row type="flex" align="middle">
                  <el-col :span="6">
                    <div class="user-photo">
                      <img src="/images/user/default.png">
                    </div>
                  </el-col>
                  <el-col :span="12">{{scope.row.userName}}</el-col>
                
                </el-row>
                
              </template>
            </el-table-column>
              
            <el-table-column
              prop="roleName"
              label="Papel">
            </el-table-column>
            <el-table-column
              prop="departmentName"
              label="Organización">
            </el-table-column>
            <el-table-column
              prop="email"
              label="Correo">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              prop="phone"
              label="Teléfono de contacto">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              prop="creatTime"
              label="Tiempo añadido">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              label="ope"
              width="150">
              <template class="ope-buttonGroup" slot-scope="scope">
                  <span class="svg-button" @click="showUserDetail(scope.row)">
                    <svg class="icon" aria-hidden="true" width="20" height="20" fill="#666681">
                      <use xlink:href="#iconlistdetailicon"></use>
                    </svg>
                  </span>
                  <span class="svg-button">
                    <svg class="icon"  @click="userEdit(scope.row)" aria-hidden="true" width="20" height="20" fill="#666681">
                      <use xlink:href="#iconediticon"></use>
                    </svg>
                  </span>
                  <span class="svg-button">
                    <svg class="icon" aria-hidden="true" @click="userDelete(scope.row)" width="20" height="20" fill="#666681">
                      <use xlink:href="#icondeleteicon"></use>
                    </svg>
                  </span>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
        <el-row class="pagination-row">
          <el-pagination
            @current-change="query"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            layout="prev, pager, next, total"
            :total="totalPage">
          </el-pagination>
        </el-row>
        <new-dialog ref="editDialog" id="editDialog">      
          <template slot="content">
            <el-row :gutter="44" class="user-info-row">
              <el-col :span="11" class="edit-form-col" style="padding-left:30px">
                <el-form :rule="rules" :label-position="top">
                  <el-form-item label="Nombre de usuario" id="userName">
                    <el-input v-model="userInfo.userName"></el-input>
                  </el-form-item>
                  <el-form-item label="Correo">
                    <el-input v-model="userInfo.email"></el-input>
                  </el-form-item>
                  <el-form-item label="Teléfono de contacto">
                    <el-input v-model="userInfo.phone"></el-input>
                  </el-form-item>
                  <el-form-item label="Papel">
                    <el-select v-model="userInfo.roleId" placeholder="请选择">
                      <el-option
                        v-for="item in roleList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="Contraseña de inicio">
                    <el-input placeholder="请输入密码" v-model="userInfo.newPassWord" show-password></el-input>
                  </el-form-item>
                  <el-form-item label="Repita la contraseña de inicio de sesión">
                    <el-input placeholder="请输入密码" v-model="userInfo.repetPassword" show-password></el-input>
                  </el-form-item>
                </el-form>
              </el-col>
              <el-col :span="12" class="edit-tree-col">
                <department-list @treeCurrentChange="treeCurrentChange"></department-list>
              </el-col>
            </el-row>
            <el-row class="user-button-row">
              <el-button class="ceiec-primary confirm" @click="submit">确 定</el-button>
            </el-row>
          </template>
        </new-dialog>
        <new-dialog  ref="detailDialog" id="userDetailDialog">
          <template slot="content">
            <div class="user-map">
              <div class="map-container" id="mapContainer" ref="mapContainer">
                <ceiec-map mapContainer="mapContainer" v-on:showMapCenter="showMapCenter" ref="userMap"></ceiec-map>
              </div>
            </div>
            <div class="user-detail-form">
              <el-form label-position="top" label-width="200px" :model="userDetail">
                <el-form-item id="userPhotoRow">
                  <div class="user-detail-photo">
                    <img src="/images/user/default.png">
                  </div>
                </el-form-item>
                <el-form-item class="firstItem" label="Nombre de usuario">
                  {{ userDetail.userName }}
                </el-form-item>
                <el-form-item label="Correo">
                  {{ userDetail.email }}
                </el-form-item>
                <el-form-item label="Teléfono de contacto">
                  {{ userDetail.phone }}
                </el-form-item>
                <el-form-item label="Papel">
                  {{ userDetail.roleName }}
                </el-form-item>
                <el-form-item label="Organización">
                  {{ userDetail.departmentName }}
                </el-form-item>
              </el-form>
            </div>
          </template>
        </new-dialog>
    </div>
</template>


<script>
import { mapGetters } from 'vuex'
import { userListApi, userDataApi, userDeleteApi } from '@/http/api/system'
import NewDialog from '@/components/NewDialog1'
import DepartmentList from '@/components/DepartmentList'
import CeiecMap from '@/components/CeiecMap/Index'


export default {
  components: {
    NewDialog,
    DepartmentList,
    CeiecMap
  },
  data () {
    return {
      userStatesLib: [
        {
          laber : "监控中",
          value : "1"
        },
        {
          laber : "未开始监控",
          value : "2"
        },
        {
          laber : "监控结束",
          value : "3"
        }
      ],
      userInfo: {
            "userId": "",
            "userName": "",
            "roleId": "",
            "departmentId":"",
            "email": "",
            "phone": "",
            "fax": "",
            "creatTime": "",
            "mapCenter": "",
            "newPassWord":""
      },
      userDetail:{
            "userId": "",
            "userName": "",
            "roleId": "",
            "roleName":"",
            "departmentId":"",
            "departmentName":"",
            "email": "",
            "phone": "",
            "creatTime": "",
            "newPassWord":""
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
        ]
      },
      roleList: [
        {
          "value":"0",
          "label":"test1"
        },{
          "value":"1",
          "label":"test2"
        },
      ],
      userListData:[],
      userName:"",
      userState:"",
      currentPage:1,
      pageSize:10,
      totalPage:0,
      showDetail:true
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  watch: {
    
  },
  methods: {
    query(){
      userListApi({userName:this.userName,loginStatus:this.userState,pageNo:this.currentPage,pageSize:this.pageSize}).then((res)=>{
        let data = res.data.data
        this.totalPage = Number.parseInt(data.total);
        this.userListData = [];
        data.items.forEach(item => {
            this.userListData.push(item)
        });         
      })
    },
    addUser(){
      for(let item in this.userInfo){
        this.userInfo[item] = ""
      }
      this.$refs.editDialog.showDialog()
    },
    userEdit(row){
      for(let key in this.userInfo){
          this.userInfo[key] = row[key]
      }
      this.$refs.editDialog.showDialog()
    },
    userDelete(row){
      this.$confirm('确定要删除吗', '提示', {
        type: 'warning'
      }).then(() => {
        userDeleteApi(row.userId).then((res) => {
          if (res.data.code === 10000) {
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
    showUserDetail(row){
      for(let key in this.userDetail){
          this.userDetail[key] = row[key]
      }
      this.$refs.detailDialog.showDialog()
    },
    treeCurrentChange(e){
      this.userInfo.departmentId = e.departmentId
    },
    showMapCenter(data) {
      this.userDetail.mapCenter = data
    },
    submit(){
      userDataApi(this.userInfo).then((res) => {
        if (res.data.code === 10000){
          this.$message({
            message: '新增成功',
            type: 'success'
          })
          this.query()
        } else {
          this.$message({
            message: '添加失败',
            type: 'error'
          })
        }
      })
    }
  },
  mounted() {
    this.query()
  }
}
</script>
<style lang="stylus">
  #userListPage
    position absolute 
    width calc(100% - 30px)
    height calc(100% - 30px)
    background #211F3A
    border-radius 6px
    margin-right 0
    .ceiec-query
      margin-right 14px   
    .el-table--medium td
      padding 5px 0
      &:first-child
        color #ffffff
    .el-table--medium th
      font-weight normal
    .el-table .cell
      line-height 20px
  #userDetailDialog
    .new-dialog
      width 1031px
      height 568px
    .el-dialog__header
      display none
  .user-state-sel
    .el-input__inner
      height 40px
      width 360px
  
  .user-name-input
    background #16142C
    border 1px solid transparent
    border-radius: 4px;
    height 40px
    width 360px
    color #666681
    float right
    &:hover
      background #16142C;
      border 1px solid #393973;
      color #666681
    &:focus
      background #16142C;
      border 1px solid #9A9AC6;
      color #ffffff
    .el-input__inner
      height 100%
  
  #user-list-table
    height 100%
  
  .pagination-row
    background #2F2E55
    border-radius 0 0 5px 5px
    height 47px
    display flex
    align-items  center
    justify-content center
    
  #editDialog
    .edit-form-col
      width 46.83333% 
    .edit-tree-col
      width 51.6%
    .el-dialog__header
      display none
    .user-info-row
      height 467px
    .user-button-row
      height 70px
      width 762px
      margin-left 8px
      display flex
      align-items center
      justify-content center
      border-top 1px solid rgba(255,255,255,0.10)
    .new-dialog
      width 822px
      height 568px
    .el-select
      width 100%
      .el-input__inner
        height 32px
      
  .user-map
    width 100%
    .map-container
      position absolute !important
      width 100%
      height 100%
      border-radius 6px
      top 0
      left 0

  .user-detail-form
    position absolute
    right 0
    top 0
    z-index 400
    width 383px
    background rgba(25,23,48,0.94);
    border-radius 0 6px 6px 0
    padding 0px 24px
    height 100%
    .el-form
      margin-top 40px
    .el-form-item
      border-bottom 1px solid rgba(255,255,255,0.10);
      padding-bottom 14px
    .firstItem
      margin-top 9px
    .el-form-item__content
      padding-left 10px
    
  #userPhotoRow
    display flex
    justify-content center
    border none
  
  #userName
    .el-form-item__label
      margin-top 0
</style>
<style lang="stylus" scoped>
  .query-row
    height 64px
    display flex
    align-items center
    width 100%
    margin 0 !important 
    border-bottom 1px solid rgba(255,255,255,0.10)
    .el-col-offset-11
      margin-left 46.83333%

  .table-row
    height calc(100% - 112px)
    .svg-button
      margin-left 15px
      &:first-child
        margin-left 0

  .user-photo
    height 36px
    width 36px
    img
      height 36px
      width 36px
      border-radius 18px

  .user-detail-photo
    height 48px
    width 48px
    img
      height 48px
      width 48px
      border-radius 24px

  .user-add-btn
    border none 
    width 30px
    height 30px
    padding 0px
    background #37365D
    border-radius 4px
    margin-left 20px
    &:hover
      background #4A4976

  .ope-buttonGroup
    padding 0

</style>
