import request from '../index'

// 被监控人基本信息
export function personBasicDetailApi(data) {
  return request({
    url: '/monitor/personBasicDetail/' + data,
    method: 'POST'
  })
}