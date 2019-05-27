import request from '../index'

// 获取监控任务列表
export function monitorTaskListApi(data) {
  return request({
    url: '/task/monitorTaskList',
    method: 'POST',
    data
  })
}

// 添加/编辑监控任务
export function taskDataApi(data) {
  return request({
    url: '/task/taskData',
    method: 'POST',
    data
  })
}

// 获取监控任务详情
export function taskDetailApi(data) {
  return request({
    url: '/task/taskDetail',
    method: 'POST',
    data
  })
}

// 提交监控任务审批
export function submitApproveApi(data) {
  return request({
    url: '/task/submitApprove',
    method: 'POST',
    data
  })
}

// 变更监控任务参数
export function changeParmameterApi(data) {
  return request({
    url: '/task/changeParmameter',
    method: 'POST',
    data
  })
}

// 导出监控任务报告书
export function taskReportExportApi(data) {
  return request({
    url: '/task/taskReportExport/' + data,
    method: 'POST'
  })
}

// 删除监控任务
export function monitorTaskDelApi(data) {
  return request({
    url: '/task/monitorTaskDel/' + data,
    method: 'POST'
  })
}

// 获取监控任务审批列表
export function approvalListApi(data) {
  return request({
    url: '/task/approvalList',
    method: 'POST',
    data
  })
}

// 撤回监控任务审批
export function applyRecallApi(data) {
  return request({
    url: '/task/applyRecall',
    method: 'POST',
    data
  })
}

// 审批任务操作
export function approveApi(data) {
  return request({
    url: '/task/approve',
    method: 'POST',
    data
  })
}
