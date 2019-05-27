export default {
  color: ['#3398DB'],
  tooltip : {
    trigger: 'axis',
    axisPointer : {
      type : 'shadow'
    }
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
	xAxis: {
		type: 'category',
    data: ['Person Name 1', 'Person Name 2', 'Person Name 3', 'Person Name 4', 'Person Name 5', 
           'Person Name 6', 'Person Name 7', 'Person Name 8', 'Person Name 9', 'Person Name 10']
	},
	yAxis: {
		type: 'value'
	},
	series: [{
		data: [320, 200, 150, 80, 71, 69, 68, 59, 59, 33],
    type: 'bar',
    barMaxWidth: '20%',
	}]
}