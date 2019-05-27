import request from '../index'
// const baseURL = 'http://localhost:1003/'
const baseURL = 'http://localhost:8080/'
// 用户登录
export function loginApi(userName, password) {
  const data = {
    "userName": userName,
    "password": password
  }
  return request({
    baseURL: baseURL,
    url: '/authorize/login',
    method: 'POST',
    data
  })
}
// 用户退出
export function logoutApi() {
  return request({
    baseURL: baseURL,
    url: '/authorize/logout',
    method: 'POST'
  })
}

// 获取用户配置信息
export function getUserInfoApi() {
  return request({
    url: '/system/getUserInfo',
    method: 'POST'
  })
}

// 消息列表查询
export function messageQueryApi(data) {
  return request({
    url: '/user/messageQuery',
    method: 'POST',
    data
  })
}

// 设置消息已读
export function messageReadApi(data) {
  return request({
    url: '/user/messageRead',
    method: 'POST',
    data
  })
}

// 修改个人信息
export function userInfoEditApi(data) {
  return request({
    url: '/user/useInfoEdit',
    method: 'POST',
    data
  })
}

// 修改个人密码
export function usePasswordEditApi(data) {
  return request({
    url: '/user/usePasswordEdit',
    method: 'POST',
    data
  })
}
