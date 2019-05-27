<template>
  <div class="task-approve-page search-table-page">
    <div class="tool-bar">
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
        <!-- 监控任务编号 -->
        <el-input v-model="searchForm.taskCode" placeholder="监控任务编号" size="normal"></el-input>
        <!-- 审批状态 -->
        <el-select v-model="searchForm.approvalStatus" 
          placeholder="请选择审批状态" size="normal">
            <el-option label="全部" value="0"></el-option>
            <el-option label="已撤回" value="1"></el-option>
            <el-option label="已提交" value="2"></el-option>
            <el-option label="未通过" value="3"></el-option>
            <el-option label="已通过" value="4"></el-option>
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
            min-width="20%">
          </el-table-column>
          <!-- 监控任务状态 -->
          <el-table-column
            prop="submitTime"
            label="申请时间"
            min-width="15%">
          </el-table-column>
          <!-- 申请类型 -->
          <el-table-column
            prop="approvalType"
            label="申请类型"
            min-width="15%">
            <template slot-scope="scope">
              <div class="task-type">
                <svg class="icon" aria-hidden="true" width="18" height="18" fill="#9A9AC6">
                  <use :xlink:href="getTaskType(scope.row.approvalType).icon"></use>
                </svg>
              {{getTaskType(scope.row.approvalType).name}}
              </div>
            </template>
          </el-table-column>
          <!-- 审批状态 -->
          <el-table-column
            prop="approvalStatus"
            label="审批状态"
            min-width="15%">
            <template slot-scope="scope">
              <div class="task-type">
                <label class="success" v-if="scope.row.approvalStatus === 4">
                  {{ getTaskStatus(scope.row.approvalStatus) }}
                </label>
                <template v-else-if="scope.row.approvalStatus === 3">
                  <label class="error">
                    {{ getTaskStatus(scope.row.approvalStatus) }}
                  </label>
                  <svg class="icon" aria-hidden="true" width="15" height="15" fill="#F94837" style="margin-left:8px">
                    <use xlink:href="#iconchakanyuanyin"></use>
                  </svg>
                </template>
                <label v-else>{{ getTaskStatus(scope.row.approvalStatus) }}</label>
              </div>
            </template>
          </el-table-column>
          <!-- 审批人 -->
          <el-table-column
            prop="approvalUser"
            label="审批人"
            min-width="15%">
          </el-table-column>
          <!-- 操作按钮 -->
          <el-table-column
            label="操作"
            align="center"
            header-align="center"
            min-width="20%">
            <template slot-scope="scope">
              <!-- 撤回审批按钮 -->
              <el-tooltip effect="dark" content="撤回申请" placement="bottom">
                <span class="svg-button">
                  <svg aria-hidden="true" width="20" height="20" @click="recall(scope)">
                    <use xlink:href="#iconchexiao"></use>
                  </svg>
                </span>
              </el-tooltip>
              <!-- 审批按钮(审批权限) -->
              <el-tooltip effect="dark" content="审批申请" placement="bottom">
                <span class="svg-button" style="margin-left: 15px">
                  <svg aria-hidden="true" width="20" height="20" @click="approve(scope)">
                    <use xlink:href="#iconshenpi"></use>
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
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { approvalListApi } from '@/http/api/task'
export default {
  data() {
    return {
      timeRange: '',
      searchForm: {
        taskCode: '',
        approvalStatus: '',
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
      }
    }
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
    // 获取审批任务类型
    getTaskType (type) {
      let obj = {
        name: '',
        icon: ''
      }
      switch (type) {
        case 1:
          obj.name = '创建任务'
          obj.icon = '#icontianjiarenwu'
          break
        case 2:
          obj.name = '结束任务'
          obj.icon = '#iconjieshu'
          break
        case 3: 
          obj.name = '变更设备'
          obj.icon = '#iconsheibeibiangeng'
          break
        case 4: 
          obj.name = '变更参数'
          obj.icon = '#iconcanshubiangeng'
          break
        case 5: 
          obj.name = '终止任务'
          obj.icon = '#iconzhongzhi'
          break
        default:
          break
      }
      return obj
    },
    // 获取审批任务状态
    getTaskStatus (status) {
      let statusName = ''
      switch (status) {
        case 1:
          statusName = '已撤回'
          break
        case 2:
          statusName = '已提交'
          break
        case 3:
          statusName = '未通过'
          break
        case 4:
          statusName = '已通过'
          break
        default:
          break
      }
      return statusName
    },
    // 审批任务查询
    taskQuery () {
      this.isLoading = true
      this.searchForm.startTime = this.timeRange[0]
      this.searchForm.endTime = this.timeRange[1]
      this.searchForm.pageNo = this.pageInfo.currentPage
      this.searchForm.pageSize = this.pageInfo.pageSize
      approvalListApi(this.searchForm).then((res) => {
        this.isLoading = false
        if (res.data.code === 10000) {
          this.pageInfo.total = res.data.data.total
          this.tableData = res.data.data.items
        } else {
          this.$message.error(res.data.error)
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
.task-approve-page
  .tool-bar
    border-bottom 1px solid rgba(255,255,255,0.10)
    justify-content flex-end
  .table-content
    .task-type
      display flex
      align-items center
      .success
        color #50E3C2
      .error
        color #F94837
      svg
        margin 0px 5px 3px 0px
</style>
<style lang="stylus">
.task-approve-page
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
</style>

