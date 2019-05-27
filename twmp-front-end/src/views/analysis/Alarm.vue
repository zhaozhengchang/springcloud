<template>
  <div class="analysis-page" v-ceiecScrollbar>
    <el-tabs type="card" @tab-click="tabClick" v-model="openedTab">
      <el-tab-pane label="设备告警分析" name="alarm">
        <div class="search-bar">
          <popup-department @treeCurrentChange="treeCurrentChange" :popupWidth="379" :popupSize="'normal'"></popup-department>
          <el-date-picker
            v-model="timeRange"
            type="daterange"
            align="right"
            size="normal"
            unlink-panels
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :format="globalConfig.defaultDateTimeFormat"
            :value-format="globalConfig.defaultDateTimeFormat"
            :picker-options="globalConfig.pickerOptions">
          </el-date-picker>
          <el-button class="ceiec-query" @click="query()">
            <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                <use xlink:href="#iconsearchicon"></use>
            </svg>
          </el-button>
        </div>
        <div class="chart-block">
          <el-row>
            <el-col :lg="24">
              <chart ref="alarmDateChart" :option="alarmDateLine" :width="lineChart.width" :height="lineChart.height"></chart>
            </el-col>
          </el-row>
          <el-row>
            <el-col :lg="24">
              <chart ref="alarmTypeChart" :option="alarmTypePie" :width="pieChart.width" :height="pieChart.height"></chart>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="设备使用率" name="device">
      </el-tab-pane>
      <el-tab-pane label="告警被监控人TOP10" name="person">
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import PopupDepartment from '@/components/PopupDepartment'
import { mapGetters } from 'vuex'
import { queryAlarmApi, queryAlarmTypeApi } from '@/http/api/analysis'
import { Promise } from 'q'
import Chart from '@/components/ECharts/Index'
import loadChartOption from '@/components/ECharts/options'
export default {
  data () {
    return {
      openedTab: 'alarm',
      timeRange: '',
      searchForm : {
        departmentId: null,
        startTime: null,
        endTime: null
      },
      alarmDateLine: {},
      alarmTypePie: {},
      lineChart: {
        width: 1800,
        height: 300
      },
      pieChart: {
        width: 600,
        height: 400
      }
    }
  },

  components: {
    PopupDepartment,
    Chart
  },

  computed: {
    ...mapGetters(['globalConfig'])
  },

  methods: {
    treeCurrentChange (e) {
      this.searchForm.departmentId = e.departmentId
    },
    tabClick(tab) {
      let tabIdx = tab.index
      if (tab.index === "0") {
        this.$router.push({ path: '/analysis/alarm'})
      } else if (tab.index === "1") {
        this.$router.push({ path: '/analysis/device'})
      } else if (tab.index === "2") {
        this.$router.push({ path: '/analysis/person'})
      }
    },
    async query () {
      this.searchForm.startTime = this.timeRange[0]
      this.searchForm.endTime = this.timeRange[1]
      let [lineChartRes, pieChartRes] = await Promise.all([
        queryAlarmApi(this.searchForm),
        queryAlarmTypeApi(this.searchForm)
      ])
      // 线型图表初始化
      let option1 = await loadChartOption('alarmDateLineOption')
      if (lineChartRes.data.data.length > 0) {
        let source = [
          ['product','04-01','04-02','04-03','04-04','04-05'],
          ['暴力拆卸', 0, 0, 0, 0, 0], 
          ['越界告警', 0, 0, 0, 0, 0], 
          ['低电量告警', 0, 0, 0, 0, 0], 
          ['设备离线', 0, 0, 0, 0, 0], 
          ['一键求助', 0, 0, 0, 0, 0]
        ]
        lineChartRes.data.data.forEach((v) => {
          let idx = source[0].indexOf(v.alarmTime)
          source[v.alarmType][idx] = v.num
        })
        option1.dataset.source = source
      }
      this.alarmDateLine = option1
      // 饼状图表初始化
      let option2 = await loadChartOption('alarmTypePieOption')
      this.alarmTypePie = option2
    }
  },

  async mounted () {
    this.query()
    window.onresize = () => {
      this.lineChart.width = this.$refs.alarmDateChart.$el.parentElement.clientWidth
      this.lineChart.height = this.$refs.alarmDateChart.$el.parentElement.clientHeight
      this.$refs.alarmDateChart.chart.resize()

      this.pieChart.width = this.$refs.alarmTypeChart.$el.parentElement.clientWidth
      this.pieChart.height = this.$refs.alarmTypeChart.$el.parentElement.clientHeight
      this.$refs.alarmTypeChart.chart.resize()
    }
  }
}
</script>

<style lang="stylus">
@import 'analysis-css.styl'
</style>
