<template>
  <div id="role-edit-page">
    <el-row id="row-top">
      <el-col :span="12" class="role-name-col">
        {{this.pageInfo.title}}
        <el-input id="role-name-input" v-model="pageInfo.roleName" placeholder="请输入角色名称"></el-input>
      </el-col>
      <el-col :span="1" :offset="10">
        <el-button class="ceiec-primary" @click="roleEditConfirm">
          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
              <use xlink:href="#iconconfirm"></use>
          </svg>
        </el-button>
      </el-col>
      <el-col :span="1">
        <el-button class="role-cancel-btn" @click="returnRolePage">
          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
              <use xlink:href="#iconcancelicon"></use>
          </svg>
        </el-button>
      </el-col>
    </el-row>
    <el-row :gutter="20" id="role-authority" style="display:flex; align-items:center; height:calc(100% - 67px)">
      <el-col :span="1" style="padding-left:0">
        <button :disabled="firstPoint===1" class="prev-btn" @click="firstPoint--">
          <svg class="icon" aria-hidden="true" width="40" height="40" fill="#9A9AC6">
            <use xlink:href="#iconleftarrow"></use>
          </svg>
        </button>
      </el-col>
      
      <el-col class="col-authority" :span="5" v-for="item in authorityList" :key="item.authorityId" v-show="item.show">
        <div class="authority-box">
            <div class="authority-icon">
                <svg class="icon" aria-hidden="true" width="36" height="36" fill="#50E3C2">
                    <use :xlink:href="'#'+item.icon"></use>
                </svg>
            </div>
            <el-row class="authority-title">{{item.zhName}}</el-row>
            <el-row class="row-authority">
              <div class="authority-tree">
                <el-tree
                  :data="item.childrenList"
                  show-checkbox
                  node-key="authorityId"
                  ref="treeKey"
                  :props="defaultProps">
                </el-tree>
              </div>
            </el-row>
            
        </div>
      </el-col>
      <el-col :span="1">
        <button :disabled="firstPoint===3" @click="firstPoint++" class="next-btn">
          <svg class="icon" aria-hidden="true" width="40" height="40" fill="#9A9AC6">
            <use xlink:href="#iconrightarrow"></use>
          </svg>
        </button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { roleDataApi } from '@/http/api/system'
const MAX_SHOW_COUNT = 4
export default {
  props:['pages','ope'],
  data () {
    return {
      pageInfo : this.pages,
      firstPoint : 1,
      authorityList:[],
      defaultProps : {
        label : "zhName",
        children : "childrenList",
      }
    }
  },
  components: {
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  watch: {
    firstPoint:function(val){
      this.updateShowStatus(val)
      this.$forceUpdate()
    }
  },
  methods: {
    returnRolePage(){
      this.$emit('update:showRole', false)
    },
    updateShowStatus(startPoint){
      for(let i=0;i<this.authorityList.length;i++){
        this.authorityList[i].show = this.authorityList[i].authorityId>=startPoint && this.authorityList[i].authorityId<startPoint+MAX_SHOW_COUNT
      }
    },
    roleEditConfirm(){
      let chooseNode=[];
      for(let i=0; i<this.authorityList.length; i++){
        let chooseItem = this.$refs.treeKey[i].getCheckedKeys()
        chooseNode = [...chooseNode,...chooseItem];
      }
      roleDataApi({roleId:this.pages.roleId,roleName:this.pages.roleName,rolePermission:chooseNode}).then((res)=>{
        let data = res.data.data
        if(data.code === "00000"){
          this.$message({
              message: '保存成功',
              type: 'success'
            })
        }
      })
    }
    
  },
  mounted() {
    let tempAuthority = JSON.parse(JSON.stringify(this.globalConfig.authority)) // 深拷贝不改变globalConfig.authority
    this.authorityList = tempAuthority.filter((startPoint)=>{
      return !!startPoint.authorityId
    })
    this.updateShowStatus(this.firstPoint)    
  }
}
</script>

<style lang="stylus">
#role-name-input
  background #16142C
  border 1px solid transparent
  border-radius: 4px;
  height 40px
  width 360px
  color #666681
  margin-left 30px
  font-size 14px
  &:hover
    background #16142C;
    border 1px solid #393973;
    color #666681
  &:focus
    background #16142C;
    border 1px solid #9A9AC6;
    color #ffffff

#role-authority
  .el-col-5
    width 25.83333%
  .el-col-1
    width 2.16667%

.authority-tree
  margin-top 20px
  width 95%
  height 500px
  overflow auto
  text-align center
  .el-tree
    background transparent
  .el-tree-node__content
    .el-checkbox
      margin-right 16px
    &:hover
      background-color transparent
  // 复选框样式
  .el-checkbox__inner
    width 18px
    height 18px
    border: 1px solid #4F4E73;
    border-radius: 2px;
    background-color transparent
  
  .el-checkbox__input.is-indeterminate
    .el-checkbox__inner
      background-color transparent
      border-color #4F4E73
    .el-checkbox__inner::before
      top 7px
      left 3px
      height 2px
      width 10px
      background-color #22C5A1
      transform scale(1)

  .el-checkbox__input.is-focus
    .el-checkbox__inner
      border-color #4F4E73
      
  .el-checkbox__input.is-checked
  .el-checkbox__input.is-foucs
    .el-checkbox__inner
      border-color #22C5A1 
      background-color #22C5A1 

  .el-checkbox__inner::after
    border 2px solid #ffffff
    border-left 0
    border-top 0
    width 4px
    height 9px
    left 5px

  

  

  // 复选框样式end
  .el-checkbox.is-checked+.el-tree-node__label
      color #9A9AC6
  .el-tree-node
    padding 15px 0
    border-bottom 1px solid rgba(255,255,255,0.10);
    $:last-child
      border-bottom none
    &:focus
      .el-tree-node__content
        background-color transparent

  .el-tree-node__children
    .el-tree-node
      border-bottom none
      padding 0 0
      margin-top 15px
  
</style>

<style lang="stylus" scoped>
#role-edit-page
  background #211F3A
  border-radius 6px
  height 100%
  width calc(100% - 30px)
  padding 0 34px

#row-top
  padding 15px 0 11px 0
  border-bottom 1px solid #424168
  color #ffffff
  text-align left 

.role-name-col
  display flex
  align-items center
  white-space nowrap

.role-cancel-btn
  background transparent
  border none
  float right 
  &:hover
    svg
      fill #9A9AC6

.prev-btn
.next-btn
  background transparent 
  border none
  padding-left 0
  &:disabled
    svg
      fill #3D3D64;
  &:focus
    outline none

.col-authority
  $:last-child
    margin-right 0

.authority-box
  width 100%
  height 650px
  background  linear-gradient(-135deg, #2C2B4A 0%, #262543 100%)
  border-radius 6px
  border-top 2px solid #50E3C2

.authority-icon
  width 68px
  height 58px
  background: #2A2A48;
  box-shadow: 0 -8px 8px 0 #201F39;
  border-radius: 6px;
  margin-top -29px
  margin-left 141px
  padding-top 10px

.authority-title
  font-family ArialMT
  font-size 14px
  color #FFFFFF
  letter-spacing 0
  text-align center
  margin-top 9px

.row-authority
  padding 0 24px
</style>
