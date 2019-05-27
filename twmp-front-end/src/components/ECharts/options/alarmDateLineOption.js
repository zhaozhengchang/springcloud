export default {
  title: {
    text: '设备告警日期统计'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data:['暴力拆卸','越界告警','低电量告警','设备离线','一键求助']
  },
  // grid: {
  //   left: '3%',
  //   right: '4%',
  //   bottom: '3%',
  //   containLabel: true
  // },
  toolbox: {
    feature: {
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category'
  },
  yAxis: {},
  dataZoom: [
    {
        type: 'slider',
        xAxisIndex: 0,
        filterMode: 'empty'
    },
    {
      type: 'inside',
      xAxisIndex: 0,
      filterMode: 'empty'
    }
  ],
  dataset: {
    source: []
  },
  series: [
    {name: '暴力拆卸', type: 'line', smooth: true, seriesLayoutBy: 'row'},
    {name: '越界告警', type: 'line', smooth: true, seriesLayoutBy: 'row'},
    {name: '低电量告警', type: 'line', smooth: true, seriesLayoutBy: 'row'},
    {name: '设备离线', type: 'line', smooth: true, seriesLayoutBy: 'row'},
    {name: '一键求助', type: 'line', smooth: true, seriesLayoutBy: 'row'},
  ]
}
