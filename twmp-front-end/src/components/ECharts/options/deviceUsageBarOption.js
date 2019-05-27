export default {
	tooltip: {
		trigger: 'axis',
		axisPointer: {
			type: 'shadow'
		}
	},
	legend: {
		data: ['未安装', '已安装', '损坏', '丢失']
	},
	grid: {
		left: '3%',
		right: '4%',
		bottom: '3%',
		containLabel: true
	},
	xAxis: [{
		type: 'category',
		data: ['2019-04-10', '2019-04-11', '2019-04-12', '2019-04-13', '2019-04-14', '2019-04-15']
	}],
	yAxis: [{
		type: 'value'
	}],
	series: 
	[
		{
			name: '未安装',
			type: 'bar',
      stack: '占比',
      barMaxWidth: '20%',
			data: [100, 90, 80, 55, 43, 22]
    },
    {
			name: '已安装',
			type: 'bar',
			stack: '占比',
			data: [0, 10, 15, 40, 57, 75]
    },
    {
			name: '损坏',
			type: 'bar',
			stack: '占比',
			data: [0, 0, 3, 4, 0, 2]
    },
    {
			name: '丢失',
			type: 'bar',
			stack: '占比',
			data: [0, 0, 2, 1, 0, 1]
		}
	]
}