<template>
  <el-popover
    class="ceiec-department"
    placement="bottom"
    :visible-arrow="false"
    trigger="click"
    title="组织机构"
    :width="popupWidth"
    popper-class="ceiec-popup-department"
    @show="refreshData">
    <div class="line"></div>
    <el-tree
      :data="departmentList"
      node-key="departmentId"
      :props="defaultProps"
      :check-on-click-node="true"
      @current-change="currentChange">
    </el-tree>
    <el-input slot="reference" readonly placeholder="请选择组织机构" v-model="departmentName" :size="popupSize">
      <i class="el-input__icon el-icon-arrow-down" slot="suffix"></i>
    </el-input>
  </el-popover>
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
      },
      departmentName: ''
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  props: {
    popupWidth: Number,
    popupSize: String
  },
  methods: {
    currentChange(e){
      this.departmentName = e.departmentName
      this.$emit('treeCurrentChange', e)
    },
    refreshData () {
      this.departmentList = []
      this.departmentList.push(this.globalConfig.department)
    }
  },
  mounted () {
    this.refreshData()
  }
}
</script>

<style lang="stylus">
.ceiec-popup-department
  height 394px
  background-image linear-gradient(135deg, #242340 0%, #191730 100%)
  border-radius 6px
  padding 0px
  border none
  .el-popover__title
    color #9A9AC6
    font-size 14px
    margin 0px
    padding 23px 24px 17px 24px
  .line
    border-bottom 1px solid rgba(255,255,255,0.10)
    margin 0px 24px 10px 24px
  .el-tree
    background none
    .el-tree-node__content
      height 32px
      &:hover
        background-color #3D3D64
      .el-icon-caret-right
        margin-left 12px
  .el-popper[x-placement^="top"] .popper__arrow
    display none
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
