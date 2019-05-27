import request from '../index'

// 获取被监控人列表
export function personListApi(data) {
  return request({
    url: '/information/personList',
    method: 'POST',
    data
  })
}

// 添加/编辑被监控人信息
export function personDataApi(data) {
  return request({
    url: '/information/personData',
    method: 'POST',
    data
  })
}

// 删除被监控人信息
export function personDelApi(data) {
  return request({
    url: '/information/personDel/' + data,
    method: 'POST'
  })
}

// 被监控人信息导入
export function personImportApi(data) {
  return request({
    url: '/information/personImport',
    method: 'POST',
    data
  })
}

// 被监控人信息导出
export function personExportApi(data) {
  return request({
    url: '/information/personExport',
    method: 'POST',
    data
  })
}