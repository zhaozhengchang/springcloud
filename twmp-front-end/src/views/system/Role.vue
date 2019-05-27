<template>
    <div class="role-page">
        <!-- 角度管理角色列表界面 -->
        <div v-show="!ope">
            <!-- 搜索条件部分 -->
            <div id="role-condition">
                <el-row type="flex" justify="center">
                    <el-col :md="24" :lg="24">
                        <span class="label-title">Gestión de roles</span>
                    </el-col>
                </el-row>
                <el-row style="margin-top:22px">
                    <el-col :md="24" :lg="6" :offset="9">
                        <el-input id="role-input" v-model="roleName" placeholder="角色名称"></el-input>
                    </el-col>
                    <el-col :md="24" :lg="2">
                        <el-button class="ceiec-query" @click="query">
                            <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                                <use xlink:href="#iconsearchicon"></use>
                            </svg>
                        </el-button>
                    </el-col>
                    <el-col :md="24" :lg="4" :offset="3">
                      <el-button class="ceiec-primary" @click="add">
                          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                              <use xlink:href="#iconaddrolebutton1"></use>
                          </svg>
                      </el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- 结果列表部分 -->
            <div class="role-list">
                <el-row :gutter="15" v-for="(items,index) in roleDataSplice()" :key="index">
                    <el-col :lg="8" v-for="(item, index) in items.data" :key="index">
                        <div class="role-info">
                            <el-row>
                                <el-col :span="20" class="roleName-info">{{item.roleName}}</el-col>
                                <el-col :span="2" class="role-ope">
                                    <div class="svg-button" @click="edit">
                                    <svg class="icon" aria-hidden="true" width="20" height="20" fill="#666681">
                                        <use xlink:href="#iconediticon"></use>
                                    </svg>
                                    </div>
                                </el-col>
                                <el-col :span="2"  class="role-ope" style="text-align:right">
                                    <div class="svg-button"  @click="handleDelete(item.roleId)">
                                        <svg class="icon" aria-hidden="true" width="20" height="20" fill="#666681">
                                            <use xlink:href="#icondeleteicon"></use>
                                        </svg>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row class="split-line">
                            </el-row>
                            <el-row>
                                <div class="block">
                                    <el-timeline>
                                        <el-timeline-item color="#50E3C2">
                                            <label>{{item.creator}} de creacion</label>
                                            <label style="float:right">{{item.createTime}}</label>
                                        </el-timeline-item>
                                        <el-timeline-item color="#7E68FF">
                                            <label>{{item.updator}} de creacion</label>
                                            <label style="float:right">{{item.updateTime}}</label>
                                        </el-timeline-item>
                                    </el-timeline>
                                </div>
                            </el-row>
                        </div>
                    </el-col>
                </el-row>
            </div>  
            <!-- 结果分页 -->
            <div id="role-pagination" @returnRole="returnRoleList($event)" v-show="roleData.length > 0">
                <el-row>
                    <el-pagination
                    @current-change="query"
                    :current-page.sync="currentPage"
                    :page-size="pageSize"
                    layout="prev, pager, next, total"
                    :total="totalPage">
                    </el-pagination>
                </el-row>
            </div>
        </div>
        <role-edit :pages="pages" :showRole.sync="ope" v-if="ope"></role-edit>
    </div>
</template>


<script>
import { mapGetters } from 'vuex'
import { roleListApi, roleDeleteApi } from '@/http/api/system'
import roleEdit from '@/views/system/RoleEdit'


export default {
  components: {
    "role-edit":roleEdit
  },
  data () {
    return {
      ope: false,
      roleName:"",
      currentPage : 1,
      pageSize : 9,
      totalPage : 0,
      roleData : [],
      permissionList: [{

      }],  
      pages: {
        title:"",
        roleId:"",
        roleName:"",
        authority:[]
      }    
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  watch: {
    
  },
  methods: {
    query(){
      roleListApi({roleName:this.roleName,pageNo:this.currentPage,pageSize:this.pageSize}).then((res)=>{
          let data = res.data.data
          this.totalPage = Number.parseInt(data.total);
          this.roleData = [];
          data.items.forEach(item => {
              this.roleData.push({
                  roleId : item.roleID,
                  roleName : item.roleName,
                  creator : item.creator,
                  createTime : item.createTime,
                  updateTime : item.updateTime,
                  updator : item.updator
              })
          });
          
      })
    },
    add(){
      this.pages = {
        title:"添加角色",
        roleId:"0001",
        roleName:"test1",
        authority:[]
      }
      this.ope = true
    },
    edit(){
      this.pages = {
        title:"添加角色",
        roleId:"0001",
        roleName:"test1",
        authority:[]
      }
      this.ope = true
    },
    handleDelete(roleId){
        this.$confirm('确定要删除吗', '提示',{type: 'warning'}).then(()=>{
            roleDeleteApi(roleId).then((res)=>{
              return res
                //TODO 结果反馈
            })
        })
        
    },
    roleDataSplice(){
        let result = [];
        for(let i=0,len=this.roleData.length;i<len;i+=3){
            result.push({
                data:this.roleData.slice(i,i+3)
                });
         }
         return result
    }
  },
  mounted() {
    this.query()
  }
}
</script>
<style lang="stylus">
#role-input
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

#role-condition
  .ceiec-query
    margin-left -43px

::-webkit-input-placeholder { 
  color: #666681;
  font-size: 16px;
}

.role-list
  .el-timeline
    padding 0
  .el-timeline-item__content
    color #666681
    text-align left
  .el-timeline-item__tail
    border-left 2px solid #413F6A
    height 200%
  .el-timeline
    margin-top 22px
  .el-timeline-item
    padding-bottom 0

#role-pagination
  .el-pagination
    margin-top 61px
    
</style>

<style lang="stylus" scoped>
.role-page
  position absolute 
  width calc(100% - 30px)
  height calc(100% - 30px)
  text-align center
  display flex
  justify-content center
  align-items center

#role-condition
  background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
  border-radius: 6px;
  width 1281px
  height 86px
  padding 25px 0

.role-list
  width 1281px
  height 495px
  .el-row
    margin-top 20px

.role-info
  background-image: linear-gradient(-135deg, #222139 0%, #252442 100%);
  border-radius: 6px;
  padding 20px 30px 30px 30px
  height 101px
  &:hover
    background-image: linear-gradient(135deg, #3C3B5A 0%, #242340 100%);
  .el-row
    margin-top 0

.roleName-info
  color #ffffff
  text-align left 
  font-family ArialMT
  font-size 15px
  line-height 17px
  
.split-line
  border-bottom 1px solid rgba(255,255,255,0.10);
  height 20px

.label-title
  font-family Arial-BoldMT
  font-size 20px
  color #FFFFFF
  letter-spacing 0
  line-height 22px

</style>
