export default {
  title : {
    text: '设备告警类型统计'
  },
  tooltip : {
    trigger: 'item',
    formatter: "{a} <br/>{b} : {c} ({d}%)"
  },
  legend: {
    orient: 'vertical',
    itemGap: 20,
    top: '30%',
    right: '20%',
    data:['暴力拆卸','越界告警','低电量告警','设备离线','一键求助']
  },
  toolbox: {
    show : true,
    feature : {
      mark : {show: true},
      dataView : {show: true, readOnly: false},
      magicType : {
        show: true,
        type: ['pie', 'funnel']
      },
      restore : {show: true},
      saveAsImage : {show: true}
    }
  },
  calculable : true,
  series : 
  {
    name:'半径模式',
    type:'pie',
    radius : [20, 110],
    center : ['25%', '50%'],
    roseType : 'radius',
    label: {
      normal: {
        show: false
      },
      emphasis: {
        show: true
      }
    },
    lableLine: {
      normal: {
        show: false
      },
      emphasis: {
        show: true
      }
    },
    data:[
      {value:10, name:'暴力拆卸'},
      {value:5, name:'越界告警'},
      {value:15, name:'低电量告警'},
      {value:25, name:'设备离线'},
      {value:20, name:'一键求助'}
    ]
  }
}
