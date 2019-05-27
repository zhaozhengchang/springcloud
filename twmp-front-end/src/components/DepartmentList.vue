<template>
  <div>
    <el-row class="department-title">
        <span>Organización</span>
    </el-row>
    <el-row class="department-list-row">
      <el-tree
        :data="departmentList"
        node-key="departmentId"
        :props="defaultProps"
        :check-on-click-node="true"
        @current-change="currentChange"
        >
      </el-tree>  
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      departmentList:[],
      defaultProps:{
        label: "departmentName",
        children: "childrenList"
      }
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  props: {
  },
  methods: {
    currentChange(e){
      this.$emit('treeCurrentChange', e)
    },
    refreshData () {
      this.departmentList = []
      this.departmentList.push(this.globalConfig.department)
    }
  },
  mounted () {
    this.departmentList.push(this.globalConfig.department)
  }
}
</script>

<style lang="stylus">
  .department-title
    border-bottom 1px solid rgba(255,255,255,0.10);
    font-family ArialMT
    font-size 14px
    color #666681
    letter-spacing 0
    text-align left 
    padding-bottom 9px
  .department-list-row
    margin-top 10px
    .el-tree-node__content
      height 32px
    .el-tree 
      background transparent
</style>
