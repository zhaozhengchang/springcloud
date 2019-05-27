<template>
  <div class="monitor-page">
    <div id="mapContainer" ref="mapContainer">
      <leftside-panel></leftside-panel>
      <map-toolbar mapContainer="mapContainer"></map-toolbar>
    </div>
    <div :class="['monitorContainer', hideMonitor ? 'hide-monitor': '']">
      <div class="monitor-list">
        <div class="list-header">
          <div class="list-header-title">
            <el-button type="text" @click="hideMonitor = !hideMonitor">
              被监控人员<i :class="hideMonitor ? 'el-icon-arrow-up' : 'el-icon-arrow-down'"></i>
            </el-button>
          </div>
        </div>
        <div class="list-search">
          <el-row :gutter="10">
            <el-col :md="24" :lg="6" :xl="6"><el-input placeholder="人名/设备编号"></el-input></el-col>
            <el-col :md="24" :lg="6" :xl="6">
              <el-cascader
                :options="departmentOptions">
              </el-cascader>
            </el-col>
            <el-col :md="24" :lg="2" :xl="2"><el-button type="primary" icon="el-icon-search"></el-button></el-col>
          </el-row>
        </div>
        <el-table
          :data="monitorList"
          :height="tableHeight"
          tooltip-effect="light"
          v-ceiecScrollbar:table
          size="medium"
          style="width: 100%;position: relative"
          @row-click="showMonitor">
          <el-table-column
            prop="userName"
            label="人名"
            max-width="20%">
          </el-table-column>
          <el-table-column
            prop="deviceNumber"
            label="设备编号"
            show-overflow-tooltip
            max-width="20%">
          </el-table-column>
          <el-table-column
            prop="department"
            label="组织机构"
            show-overflow-tooltip
            max-width="20%">
          </el-table-column>
          <el-table-column
            prop="userLevel"
            label="监控等级"
            max-width="20%">
          </el-table-column>
          <el-table-column
            prop="energy"
            label="设备电量"
            max-width="20%">
          </el-table-column>
        </el-table>
        <el-pagination
          background
          :current-page="1"
          :page-size="globalConfig.pageLimit"
          layout="total, prev, pager, next"
          :total="monitorList.length">
        </el-pagination>
      </div>
      <div class="alarm-list">
        <div class="list-header">
          <div class="list-header-title" style="background: #FF6666">
            <el-button type="text" @click="hideMonitor = !hideMonitor">
              一键报警<i :class="hideMonitor ? 'el-icon-arrow-up' : 'el-icon-arrow-down'"></i>
            </el-button>
          </div>
        </div>
        <div class="alarm-tabs">
          <el-tabs tab-position="left" style="height: 100%;">
            <el-tab-pane :label="'待处置(' + alarmPendingList.length + ')'">
              <el-table
                :data="alarmPendingList"
                :height="tableHeight"
                tooltip-effect="light"
                v-ceiecScrollbar:table
                size="medium"
                style="width: 100%;position: relative">
                <el-table-column
                  prop="alarmNumber"
                  label="告警编号"
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="userName"
                  label="监控对象"
                  show-overflow-tooltip
                  max-width="30%">
                </el-table-column>
                <el-table-column
                  prop="alarmType"
                  label="告警类型"
                  show-overflow-tooltip
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="alarmTime"
                  label="告警时间"
                  max-width="30%">
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane :label="'未分配(' + alarmUnassignedList.length + ')'">
              <div class="list-search">
                <el-row :gutter="10">
                  <el-col :md="24" :lg="6" :xl="6"><el-input placeholder="人名/设备编号"></el-input></el-col>
                  <el-col :md="24" :lg="2" :xl="2"><el-button type="primary" icon="el-icon-search"></el-button></el-col>
                </el-row>
              </div>
              <el-table
                :data="alarmUnassignedList"
                :height="tableHeight"
                tooltip-effect="light"
                v-ceiecScrollbar:table
                size="medium"
                style="width: 100%;position: relative">
                <el-table-column
                  prop="alarmNumber"
                  label="告警编号"
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="userName"
                  label="监控对象"
                  show-overflow-tooltip
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="alarmType"
                  label="告警类型"
                  show-overflow-tooltip
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="alarmTime"
                  label="告警时间"
                  max-width="20%">
                </el-table-column>
              </el-table>
              <el-pagination
                background
                :current-page="1"
                :page-size="globalConfig.pageLimit"
                layout="total, prev, pager, next"
                :total="alarmUnassignedList.length">
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane :label="'已处置(' + alarmProcessedList.length + ')'">
              <div class="list-search">
                <el-row :gutter="10">
                  <el-col :md="24" :lg="6" :xl="6"><el-input placeholder="人名/设备编号"></el-input></el-col>
                  <el-col :md="24" :lg="10" :xl="10">
                    <el-date-picker
                      type="datetimerange"
                      range-separator="-"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期">
                    </el-date-picker>
                  </el-col>
                  <el-col :md="24" :lg="6" :xl="6">
                    <el-select placeholder="告警类型">
                      <el-option key="1" label="暴力拆解" value="1"></el-option>
                      <el-option key="2" label="越界" value="2"></el-option>
                      <el-option key="3" label="低电量" value="3"></el-option>
                    </el-select>
                  </el-col>
                  <el-col :md="24" :lg="2" :xl="2"><el-button type="primary" icon="el-icon-search"></el-button></el-col>
                </el-row>
              </div>
              <el-table
                :data="alarmProcessedList"
                :height="tableHeight"
                tooltip-effect="light"
                v-ceiecScrollbar:table
                style="width: 100%;position: relative">
                <el-table-column
                  prop="alarmNumber"
                  label="告警编号"
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="userName"
                  label="监控对象"
                  show-overflow-tooltip
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="alarmType"
                  label="告警类型"
                  show-overflow-tooltip
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="alarmTime"
                  label="告警时间"
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="handleUserName"
                  label="处置人员"
                  max-width="20%">
                </el-table-column>
                <el-table-column
                  prop="handleTime"
                  label="处置时间"
                  max-width="20%">
                </el-table-column>
              </el-table>
              <el-pagination
                background
                :current-page="1"
                :page-size="globalConfig.pageLimit"
                layout="total, prev, pager, next"
                :total="alarmProcessedList.length">
              </el-pagination>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex"
import MapToolbar from '@/components/CeiecMap/MapToolbar'
import { mapGetters } from 'vuex'
import LeftsidePanel from '@/components/MapPanels/LeftsidePanel'
export default {
  name: "monitor",
  data: () => {
    return {
      tableHeight: 200,
      hideMonitor: false,
      monitorList: [],
      alarmPendingList: [],
      alarmUnassignedList: [],
      alarmProcessedList: [],
      departmentOptions: [
        {
          value: '1000',
          label: '四川省',
          children: [
            {
              value: '1000-01',
              label: '成都市',
              children: [
                {
                  value: '1000-01-01',
                  label: '武侯区监狱'
                },
                {
                  value: '1000-01-02',
                  label: '高新区监狱'
                },
                {
                  value: '1000-01-03',
                  label: '青羊区监狱'
                }
              ]
            },
            {
              value: '1000-02',
              label: '绵阳市'
            },
            {
              value: '1000-03',
              label: '德阳市'
            }
          ]
        }
      ]
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  components: {
    MapToolbar,
    LeftsidePanel
  },
  methods: {
    ...mapActions(['updateShowMonitorPanel']),
    async getMonitorAndAlarmList () {
      let [monitorList, alarmList] = await Promise.all([
        this.$http.post('/monitor/monitorList'),
        this.$http.post('/monitor/alarmList')
      ])
      this.monitorList = monitorList.data.data.monitorList
      this.alarmPendingList = alarmList.data.data.pendingList
      this.alarmUnassignedList = alarmList.data.data.unassignedList
      this.alarmProcessedList = alarmList.data.data.processedList
    },

    showMonitor () {
      this.updateShowMonitorPanel(true)
    }
  },
  mounted() {
    this.getMonitorAndAlarmList()
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - this.$refs.mapContainer.offsetHeight - 138
    })
    
    window.onresize = () => {
      this.tableHeight = window.innerHeight - this.$refs.mapContainer.offsetHeight - 138
    }
  }
}
</script>

<style lang="stylus" scoped>
.monitor-page
  width 100%
  height 100%
  position absolute
  display flex
  flex-direction column
  #mapContainer
    height 100%
    width 100%
  .monitorContainer
    display flex
    width 100%
    height 420px
    position relative
    transition height 1s
    &.hide-monitor
      height 0px
    .monitor-list
      width 40%
      border-right 1px solid #ebeef5
    .alarm-list
      width 60%
      .alarm-tabs
        height 100%
    .list-header
      position relative
      .list-header-title
        font-size 16px
        position absolute
        bottom 0px
        z-index 500
        background #004384
        border-radius 16px 16px 0px 0px
        width 180px
        text-align center
        .el-button--text
          color #ffffff
    .list-search
      padding 3px
      line-height 42px
  .display-monitor-button
    position absolute
    right 10px
    bottom 180px
    z-index 400
    padding 3px
    border 1px solid #e4e6e9
    border-radius 5px
    height 35px
    background-color rgba(255, 255, 255, 0.6)
    z-index 500
    box-shadow 0 0 5px 0 rgba(0, 0, 0, 0.6)
</style>
<style lang="stylus">
.el-table--scrollable-y .el-table__body-wrapper
  overflow hidden
</style>

