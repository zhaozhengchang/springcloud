import request from '../index'
// 获取组织机构列表
export function departmentListApi() {
  return request({
    url: '/systemManagement/departmentList',
    method: 'POST'
  })
}
// 新增、修改组织机构
export function departmentDataApi(data) {
  return request({
    url: '/systemManagement/departmentData',
    method: 'POST',
    data
  })
}
// 删除组织机构
export function departmentDelApi(data) {
  return request({
    url: '/systemManagement/departmentDel/' + data,
    method: 'POST'
  })
}
//获取角色列表
export function roleListApi(data) {
  return request({
    url: '/systemManagement/roleQuery',
    method: 'POST',
    data
  })
}
//删除角色
export function roleDeleteApi(data) {
  return request({
    url: '/systemManagement/roleDel'+data,
    method: 'POST'
  })
}
//添加/编辑角色
export function roleDataApi(data) {
  return request({
    url: '/systemManagement/roleData',
    method: 'POST',
    data
  })
}
// 获取系统参数
export function systemParametersApi() {
  return request({
    url: '/systemManagement/systemParameters',
    method: 'GET'
  })
}
// 修改系统参数
export function systemParametersUpdateApi(data) {
  return request({
    url: '/systemManagement/systemParametersUpdate',
    method: 'POST',
    data
  })
}
// 获取可编辑数据字典
export function dictTypesApi() {
  return request({
    url: '/systemManagement/dictTypes',
    method: 'POST'
  })
}
// 添加/编辑数据字典
export function dictDataApi(data) {
  return request({
    url: '/systemManagement/dictData',
    method: 'POST',
    data
  })
}
// 删除数据字典
export function dictDelApi(data) {
  return request({
    url: '/systemManagement/dictDel/' + data,
    method: 'POST'
  })
}
//获取用户信息
export function userListApi(data){
  return request({
    url: '/systemManagement/userInfoQuery',
    method: 'POST',
    data
  })
}
//// 新增、修改用户信息
export function userDataApi(data) {
  return request({
    url: '/systemManagement/userData',
    method: 'POST',
    data
  })
}
//// 删除用户
export function userDeleteApi(data) {
  return request({
    url: '/systemManagement/userDel/'+data,
    method: 'POST'
  })
}
//获取文书列表
export function paperworkListApi(data) {
  return request({
    url: '/systemManagement/paperworkQuery',
    method: 'POST',
    data
  })
}
//添加文书
export function paperworkDataApi(data) {
  return request({
    url: '/systemManagement/paperworkData',
    method: 'POST',
    data
  })
}
//删除文书
export function paperworkDeleteApi(data) {
  return request({
    url: 'systemManagement/paperworkDel/'+data,
    method: 'post',
  })
}

// 上传文件/图片
export function fileUploadApi(data) {
  return request({
    url: '/system/fileUpload',
    method: 'POST',
    data
  })
}

// 下载文件/图片
export function fileDownloadApi(data) {
  return request({
    url: '/downloadFile/' + data,
    method: 'GET'
  })
}
//系统登录日志查询
export function loginLogQueryApi(data){
  return request({
    url: '/log/userLoginQuery',
    method: 'POST',
    data
  })
}
//系统登录日志导出
export function loginLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//日志查询
export function logQueryApi(data){
  return request({
    url: '/log/queryLog',
    method: 'POST',
    data
  })
}
//组织机构管理日志导出
export function departmentLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//用户管理日志导出
export function userLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//人员信息管理日志导出
export function personLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//设备信息管理日志导出
export function deviceLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//监控任务管理日志导出
export function taskLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//审批日志导出
export function approvalLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//文书管理日志导出
export function paperworkLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}
//参数管理日志导出
export function paramLogExportApi(data){
  return request({
    url: '/log/userLoginExport',
    method: 'POST',
    data
  })
}