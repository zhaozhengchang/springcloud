<template>
  <div :class="className" ref="ceiecChart" :style="{width: width + 'px', height: height + 'px'}">
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  data () {
    return {
      chart: {}
    }
  },

  props: {
    option: Object,   // 图表配置项对象
    className: String,
    width: {
      type: Number,
      default: 500
    },
    height: {
      type: Number,
      default: 500
    }
  },

  watch: {
    option(newVal) {
      this.chart.setOption(newVal)
    }
  },

  methods: {
    setOption (option) {
      this.chart.setOption(option)
    }
  },

  mounted () {
    this.chart = echarts.init(this.$refs.ceiecChart)
  }
}
</script>
