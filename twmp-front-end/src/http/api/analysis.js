import request from '../index'
// 获取设备告警日期统计
export function queryAlarmApi(data) {
  return request({
    url: '/chart/queryAlarm',
    method: 'POST',
    data
  })
}

// 获取设备告警类型统计
export function queryAlarmTypeApi(data) {
  return request({
    url: '/chart/queryAlarmType',
    method: 'POST',
    data
  })
}

// 导出统计图表
export function alarmExportApi(data) {
  return request({
    url: '/chart/alarmExport',
    method: 'POST',
    data
  })
}

// 获取设备使用率
export function deviceUsageRateApi(data) {
  return request({
    url: '/chart/deviceUsageRate',
    method: 'POST',
    data
  })
}

// 人员告警TOP10
export function personAlarmCountApi(data) {
  return request({
    url: '/chart/personAlarmCount',
    method: 'POST',
    data
  })
}

// 导出设备使用率统计图表
export function deviceUsageRateExportApi(data) {
  return request({
    url: '/chart/deviceUsageRateExport',
    method: 'POST',
    data
  })
}