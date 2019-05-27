<template>
  <div class="task-manage-page search-table-page">
    <div class="tool-bar">
      <div class="button-bar">
        <el-tooltip class="item" effect="dark" content="新建监控任务" placement="bottom">
          <el-button class="ceiec-default" @click="addNewTask">
            <svg class="icon" aria-hidden="true" width="24" height="24">
              <use xlink:href="#icontianjiarenwu"></use>
            </svg>
          </el-button>
        </el-tooltip>
      </div>
      <div class="search-bar">
        <!-- 日期时间范围 -->
        <el-date-picker
          v-model="timeRange"
          type="datetimerange"
          size="normal"
          :picker-options="globalConfig.pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :format="globalConfig.defaultDateTimeFormat"
          :value-format="globalConfig.defaultDateTimeFormat"
          align="right">
        </el-date-picker>
        <!-- 监控任务编号、被监控人、设备编号、组织机构名称查询框 -->
        <el-input placeholder="请输入内容" v-model="selectInputValue" size="normal" class="ceiec-select-input">
          <el-select v-model="selectInputType" placeholder="" slot="prepend" popper-class="ceiec-select-input-popper">
            <template slot="prefix">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use :xlink:href="getSelectedInputType()"></use>
              </svg>
            </template>
            <el-option value="1" label=" ">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use xlink:href="#iconanjianbhao"></use>
              </svg>
              监控任务编号
            </el-option>
            <el-option value="2" label=" ">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use xlink:href="#iconrenyuanmingzicopy"></use>
              </svg>
              被监控人
            </el-option>
            <el-option value="3" label=" ">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use xlink:href="#iconsheibeibianhao"></use>
              </svg>
              设备编号
            </el-option>
            <!-- <el-option value="4" label=" ">
              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                <use xlink:href="#iconzuzhijigou"></use>
              </svg>
              组织机构名称
            </el-option> -->
          </el-select>
        </el-input>
        <!-- 任务状态 -->
        <el-select v-model="searchForm.taskStatus" 
          placeholder="请选择监控任务状态" size="normal">
            <el-option label="全部" value="0"></el-option>
            <el-option label="未启动" value="1"></el-option>
            <el-option label="已启动" value="2"></el-option>
            <el-option label="已结束" value="3"></el-option>
        </el-select>
        <el-button class="ceiec-query" @click="taskQuery()">
          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
              <use xlink:href="#iconsearchicon"></use>
          </svg>
        </el-button>
      </div>
    </div>
    <!-- 监控任务列表 -->
    <div class="table-content">
      <el-table
        v-ceiecScrollbar:table
        :data="tableData"
        stripe
        size="medium"
        tooltip-effect="dark"
        highlight-current-row
        :header-cell-style="{'font-size': '13px', 'font-weight': 'normal'}"
        style="width: 100%">
          <template slot="empty">
            <div v-show="showTipIcon" class="tip-icon">
              <div class="empty" v-show="isEmpty">
                <img src="/images/empty.png">
                <p>{{$t('table_no_data')}}</p>
              </div>
              <div class="loading" v-show="isLoading">
                <img src="/images/loading.svg">
              </div>
            </div>
          </template>
          <!-- 监控任务编号 -->
          <el-table-column
            prop="taskCode"
            label="监控任务编号"
            min-width="12%">
          </el-table-column>
          <!-- 监控任务状态 -->
          <el-table-column
            prop="taskProgress"
            label="任务状态"
            min-width="13%">
            <template slot-scope="scope">
              <el-progress 
                :percentage="scope.row.taskProgress"
                :stroke-width="3"
                color="#50E3C2"
                status="text"
                :class="scope.row.taskStatus === 3 ? 'finished' : ''">
                {{ scope.row.taskStatus === 3 ? '已结束' : scope.row.taskProgress + '%' }}
              </el-progress>
            </template>
          </el-table-column>
          <!-- 监控等级 -->
          <el-table-column
            prop="taskLevel"
            label="监控等级"
            min-width="12%">
            <template slot-scope="scope">
              <div class="task-level">
                <svg class="icon" aria-hidden="true" width="18" height="18" :fill="getTaskLevel(scope.row.taskLevel).color">
                  <use xlink:href="#iconjinjichengdu"></use>
                </svg>
              {{getTaskLevel(scope.row.taskLevel).name}}
              </div>
            </template>
          </el-table-column>
          <!-- 任务开始时间 -->
          <el-table-column
            prop="startTime"
            label="任务开始时间"
            min-width="12%">
          </el-table-column>
          <!-- 任务结束时间 -->
          <el-table-column
            prop="endTime"
            label="任务结束时间"
            min-width="12%">
          </el-table-column>
          <!-- 被监控人 -->
          <el-table-column
            prop="personName"
            label="被监控人"
            min-width="12%">
          </el-table-column>
          <!-- 设备编号 -->
          <el-table-column
            prop="deviceNumber"
            label="设备编号"
            min-width="12%">
          </el-table-column>
          <!-- 操作按钮 -->
          <el-table-column
            label="操作"
            align="center"
            header-align="center"
            min-width="20%">
            <template slot-scope="scope">
              <!-- 提交审批按钮 -->
              <el-tooltip effect="dark" content="提交审批" placement="bottom">
                <span class="svg-button" @click="openDialog(scope, 1)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#iconshenpi"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 设备变更按钮 -->
              <el-tooltip effect="dark" content="变更设备" placement="bottom">
                <span class="svg-button" style="margin-left: 15px" @click="openDialog(scope, 3)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#iconsheibeibiangeng"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 参数变更按钮 -->
              <el-tooltip effect="dark" content="变更参数" placement="bottom">
                <span class="svg-button" style="margin-left: 15px" @click="openDialog(scope, 4)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#iconcanshubiangeng"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 终止任务按钮（异常强制性） -->
              <el-tooltip effect="dark" content="终止任务(异常情况)" placement="bottom">
                <span class="svg-button" style="margin-left: 15px" @click="openDialog(scope, 5)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#iconzhongzhi"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 正常结束任务按钮 -->
              <el-tooltip effect="dark" content="结束任务" placement="bottom">
                <span class="svg-button" style="margin-left: 15px" @click="openDialog(scope, 2)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#iconjieshu"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 监控任务详情按钮 -->
              <el-tooltip effect="dark" content="任务详情" placement="bottom">
                <span class="svg-button" style="margin-left: 15px" @click="viewTask(scope)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#iconxiangqing"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 监控任务报告导出按钮 -->
              <el-tooltip effect="dark" content="导出任务报告" placement="bottom">
                <span class="svg-button" style="margin-left: 15px" @click="exportTaskReport(scope)">
                  <svg aria-hidden="true" width="20" height="20">
                    <use xlink:href="#icondaochu"></use>
                  </svg>
                </span>
              </el-tooltip>
            </template>
          </el-table-column>
      </el-table>
    </div>
    <!-- 分页显示区域 -->
    <div class="pagination">
      <el-pagination
        @current-change="taskQuery()"
        :current-page.sync="pageInfo.currentPage"
        :page-size="pageInfo.pageSize"
        layout="prev, pager, next, total"
        :total="pageInfo.total">
      </el-pagination>
    </div>
    <!-- 新任务提交申请弹出框 -->
    <new-dialog ref="taskDialog1" title="提交任务审批" class="new-task-dialog">
      <template slot="content">
        <div class="info-icon">
          <svg aria-hidden="true" width="40" height="40" fill="#9A9AC6">
            <use xlink:href="#iconshenpi"></use>
          </svg>
        </div>
        <div class="info-content">
          确认提交该任务至审批吗？
        </div>
      </template>
      <template slot="footer">
        <el-button class="ceiec-default confirm" @click.stop="closeDialog(1)">取消</el-button>
        <el-button class="ceiec-primary confirm" @click.stop="newTask">提交</el-button>
      </template>
    </new-dialog>
    <!-- 设备变更弹出框 -->
    <new-dialog ref="taskDialog3" title="申请任务设备变更" class="change-task-dialog">
       <el-form 
        slot="content"
        label-position="top" 
        :model="submitTaskForm">
        <el-form-item label="原因" style="margin:15px 0px 20px 0px">
          <el-input v-model="submitTaskForm.changeReason"
            :autosize="{ minRows: 6, maxRows: 6}"
            type="textarea" resize="none" :rows="6"></el-input>
        </el-form-item>
       </el-form>
      <template slot="footer">
        <el-button class="ceiec-default confirm" @click.stop="closeDialog(3)">取消</el-button>
        <el-button class="ceiec-primary confirm" @click.stop="changeDevice">提交</el-button>
      </template>
    </new-dialog>
    <!-- 终止任务弹出框 -->
    <new-dialog ref="taskDialog5" title="终止监控任务" class="change-task-dialog">
       <el-form 
        slot="content"
        label-position="top" 
        :model="submitTaskForm">
        <el-form-item lable="" style="margin-top: 15px">
          <el-radio v-model="submitTaskForm.unpack" label="1">可正常拆机</el-radio>
          <el-radio v-model="submitTaskForm.unpack" label="2">无需拆机</el-radio>
        </el-form-item>
        <el-form-item label="原因">
          <el-input v-model="submitTaskForm.changeReason"
            :autosize="{ minRows: 6, maxRows: 6}"
            type="textarea" resize="none" :rows="6"></el-input>
        </el-form-item>
       </el-form>
      <template slot="footer">
        <el-button class="ceiec-default confirm" @click.stop="closeDialog(5)">取消</el-button>
        <el-button class="ceiec-primary confirm" @click.stop="terminateTask">提交</el-button>
      </template>
    </new-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { monitorTaskListApi, submitApproveApi } from '@/http/api/task'
import NewDialog from '@/components/NewDialog'
export default {
  data() {
    return {
      selectInputValue: '',
      selectInputType: '',
      timeRange: '',
      searchForm: {
        departmentName: '',
        personName: '',
        deviceNumber: '',
        taskCode: '',
        taskStatus: '',
        startTime: null,
        endTime: null,
        pageNo: 1,
        pageSize: 20
      },
      tableData: [],
      isLoading: true,
      pageInfo: {
        currentPage: 1,
        pageSize: 20,
        total: 0
      },
      submitTaskForm: {
        taskId: null,
        approvalType: null,
        changeReason: null,
        unpack: null
      }
    }
  },
  components: {
    NewDialog
  },
  computed: {
    ...mapGetters(['globalConfig']),
    isEmpty () {
      return !this.tableData.length && !this.isLoading
    },
    showTipIcon () {
      return this.isLoading || this.isEmpty
    }
  },
  methods: {
    getSelectedInputType () {
      switch (this.selectInputType) {
        case '1':
          this.searchForm.taskCode = this.selectInputValue
          return '#iconanjianbhao'
        case '2':
          this.searchForm.personName = this.selectInputValue
          return '#iconrenyuanmingzicopy'
        case '3':
          this.searchForm.deviceNumber = this.selectInputValue
          return '#iconsheibeibianhao'
        case '4':
          return '#iconzuzhijigou'
        default:
          return '#iconanjianbhao'
      }
    },
    getTaskLevel (level) {
      let obj = {
        name: '',
        color: '#FFFFFF'
      }
      switch (level) {
        case 1:
          obj.name = '1级-非常重要'
          obj.color = '#F94837'
          return obj
        case 2:
          obj.name = '2级-重要'
          obj.color = '#7E68FF'
          return obj
        case 3: 
          obj.name = '3级-普通'
          obj.color = '#40B59B'
          return obj
        default:
          return obj
      }
    },
    // 新建监控任务
    addNewTask () {
      this.$router.push({ path: '/task/taskData/add', query: {id: ""} })
    },
    // 监控任务查询
    taskQuery () {
      this.isLoading = true
      this.searchForm.startTime = this.timeRange[0]
      this.searchForm.endTime = this.timeRange[1]
      this.getSelectedInputType()
      this.searchForm.pageNo = this.pageInfo.currentPage
      this.searchForm.pageSize = this.pageInfo.pageSize
      monitorTaskListApi(this.searchForm).then((res) => {
        this.isLoading = false
        if (res.data.code === 10000) {
          this.pageInfo.total = res.data.data.total
          this.tableData = res.data.data.items
        } else {
          this.$message.error(res.data.error)
        }
      })
    },
    closeDialog (type) {
      this.$refs['taskDialog' + type].closeDialog()
    },
    // 打开对应Dialog
    openDialog (scope, type) {
      this.submitTaskForm.taskId = scope.row.taskId
      this.$refs['taskDialog' + type].showDialog()
    },
    /**
     *  1-创建监控任务审批
     *  2-结束监控任务审批
     *  3-设备变更审批
     *  4-监控任务参数变更审批
     *  5-终止监控任务审批
     */
    // 提交任务审批
    newTask () {
      this.submitTask(1)
    },
    // 变更任务设备
    changeDevice () {
      this.submitTask(3)
    },
    // 变更任务参数
    changeParam () {

    },
    // 终止任务(异常强制性)
    terminateTask () {
      this.submitTask(5)
    },
    // 结束任务
    closeTask () {

    },
    // 任务详情
    viewTask (){

    },
    // 导出任务报告
    exportTaskReport () {

    },
    // 审批任务提交接口
    submitTask (type) {
      submitApproveApi(this.submitTask).then((res) => {
        if (res.data.code === 10000) {
          this.$refs['taskDialog' + type].closeDialog()
        }
      })
    }
  },

  mounted () {
    this.pageInfo.pageSize = this.globalConfig.pageLimit
    this.taskQuery()
  }
}
</script>

<style lang="stylus" scoped>
.task-manage-page
  .tool-bar
    border-bottom 1px solid rgba(255,255,255,0.10)
  .table-content
    .task-level
      display flex
      align-items center
      svg
        margin 0px 5px 3px 0px
</style>
<style lang="stylus">
.task-manage-page
  .el-table > .el-table__body-wrapper
    height calc(100% - 160px)
    .el-progress
      width 158px
      &.finished
        .el-progress-bar > .el-progress-bar__inner
          background-color #9A9AC6
      .el-progress-bar > .el-progress-bar__outer
        background-color #191730
      .el-progress__text
        font-size 13px
        color #9A9AC6
  .new-task-dialog > .new-dialog
    width 418px
    .info-icon
      text-align center
      margin 30px 0px 0px 8px
    .info-content
      font-size 14px
      color #9A9AC6
      text-align center
      margin-bottom 30px
  .change-task-dialog > .new-dialog
    width 545px
    .el-textarea__inner
      height 148px !important
</style>

