<template>
  <div class="analysis-page" v-ceiecScrollbar>
    <el-tabs type="card" @tab-click="tabClick" v-model="openedTab">
      <el-tab-pane label="设备告警分析" name="alarm">
      </el-tab-pane>
      <el-tab-pane label="设备使用率" name="device">
      </el-tab-pane>
      <el-tab-pane label="告警被监控人TOP10" name="person">
        <div class="search-bar">
          <popup-department @treeCurrentChange="treeCurrentChange" :popupWidth="379" :popupSize="'normal'"></popup-department>
          <el-select v-model="searchForm.alarmType" placeholder="告警类型">
            <el-option
              v-for="item in alarmTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
          <el-row :gutter="20">
            <el-col :lg="24">
              <chart ref="personTop10Chart" :option="personTop10BarOption" :width="barChart.width" :height="barChart.height"></chart>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import PopupDepartment from '@/components/PopupDepartment'
import { mapGetters } from 'vuex'
import { personAlarmCountApi } from '@/http/api/analysis'
import Chart from '@/components/ECharts/Index'
import loadChartOption from '@/components/ECharts/options'
export default {
  data () {
    return {
      openedTab: 'person',
      alarmTypes: [
        { label: '暴力拆卸', value: 1},
        { label: '越界告警', value: 2},
        { label: '低电量告警', value: 3},
        { label: '设备离线', value: 4},
        { label: '一键求助', value: 5}
      ],
      timeRange: '',
      searchForm : {
        departmentId: null,
        startTime: null,
        endTime: null,
        alarmType: null
      },
      personTop10BarOption: {},
      barChart: {
        width: 1800,
        height: 600
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
      let res = await personAlarmCountApi(this.searchForm)
      // 柱状图表初始化
      let option = await loadChartOption('personTop10BarOption')
      // if (res.data.data.length > 0) {
      //   let source = [
      //     ['product','04-01','04-02','04-03','04-04','04-05'],
      //     ['暴力拆卸', 0, 0, 0, 0, 0], 
      //     ['越界告警', 0, 0, 0, 0, 0], 
      //     ['低电量告警', 0, 0, 0, 0, 0], 
      //     ['设备离线', 0, 0, 0, 0, 0], 
      //     ['一键求助', 0, 0, 0, 0, 0]
      //   ]
      //   lineChartRes.data.data.forEach((v) => {
      //     let idx = source[0].indexOf(v.alarmTime)
      //     source[v.alarmType][idx] = v.num
      //   })
      //   option.dataset.source = source
      // }
      this.personTop10BarOption = option
    }
  },

  async mounted () {
    this.query()
    window.onresize = () => {
      this.barChart.width = this.$refs.personTop10Chart.$el.parentElement.clientWidth
      this.barChart.height = this.$refs.personTop10Chart.$el.parentElement.clientHeight
      this.$refs.personTop10Chart.chart.resize()
    }
  }

}
</script>

<style lang="stylus">
@import 'analysis-css.styl'
</style>
