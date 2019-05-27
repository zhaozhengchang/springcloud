<template>
    <div>
        <el-tabs type="card" stretch>
            <el-tab-pane label="系统登录日志">
                <login-log></login-log>
            </el-tab-pane>
            <el-tab-pane label="组织机构管理日志">
                <department-log></department-log>
            </el-tab-pane>
            <el-tab-pane label="用户管理日志">
                <user-log></user-log>
            </el-tab-pane>
            <el-tab-pane label="被监控人管理日志">
                <person-log></person-log>
            </el-tab-pane>
            <el-tab-pane label="设备信息管理日志">
                <device-log></device-log>
            </el-tab-pane>
            <el-tab-pane label="监控任务管理日志">
                <task-log></task-log>
            </el-tab-pane>
            <el-tab-pane label="审批日志">
                <approval-log></approval-log>
            </el-tab-pane>
            <el-tab-pane label="文书管理日志">
                <paperwork-log></paperwork-log>
            </el-tab-pane>
            <el-tab-pane label="参数管理日志">
                <param-log></param-log>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import loginLog from '@/views/system/LogSub/LoginLog'
import departmentLog from '@/views/system/LogSub/DepartmentLog'
import userLog from '@/views/system/LogSub/UserLog'
import personLog from '@/views/system/LogSub/PersonInfoLog'
import deviceLog from '@/views/system/LogSub/DeviceInfoLog'
import taskLog from '@/views/system/LogSub/TaskLog'
import approvalLog from '@/views/system/LogSub/ApprovalLog'
import paperworkLog from '@/views/system/LogSub/PaperworkLog'
import paramLog from '@/views/system/LogSub/ParamLog'
export default {
    components :{
        loginLog,
        departmentLog,
        userLog,
        personLog,
        deviceLog,
        taskLog,
        approvalLog,
        paperworkLog,
        paramLog
    },
    data(){
        return{
            logPageList:[]
        }
    },
    computed: {
        ...mapGetters(['globalConfig'])
    },
    methods:{
        
    },
    mounted(){
        this.logPageList = this.globalConfig.authority[8].children[6].children
    }
}
</script>
<style lang="stylus">
  .log-page
    position fixed 
    width calc(100% - 310px)
    height calc(100% - 150px)
    background #211F3A
    border-radius 0 0 6px 6px
    margin-right 0
    .log-export
      border none 
      width 30px
      height 30px
      padding 0px
      background #37365D
      border-radius 4px
      margin-left 20px
      &:hover
        background #4A4976
    .query-row
      height 64px
      display flex
      align-items center
      width 100%
      margin 0 !important 
      border-bottom 1px solid rgba(255,255,255,0.10)
      .el-col-offset-11
        margin-left 46.83333%
      .el-input
        .el-input__inner
            height 40px
            width 336px
      .el-range-editor--small.el-input__inner
        height 40px
        width 336px
      .col-query-btn
        width 3.8%
        margin-right 10px
      .col-log-input
        margin-right 20px
    .table-row
      height calc(100% - 112px)
      .svg-button
        margin-left 15px
        &:first-child
            margin-left 0
    .pagination-row
      height 47px
      display flex
      align-items center
      justify-content center
</style>
<style lang="stylus" scoped>

</style>

