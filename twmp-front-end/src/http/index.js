import Vue from 'vue'
import axios from 'axios'
import { Message } from 'element-ui'
import Cookies from 'js-cookie'
import webBaseUrl from '@/utils/baseUrl'
var token = Cookies.get('token')
// create an axios instance
const service = axios.create({
  // 允许跨域带token
  withCredentials: true,
  baseURL: webBaseUrl,
  timeout: 30000 // request timeout
})
// service.defaults.headers.common["Authorization"] = token
// service.defaults.headers.common["token"] = token
axios.defaults.headers.post['Content-Type'] = 'application/json'
// request interceptor
service.interceptors.request.use(config => {
  let url = config.url
  if (url.indexOf('getUserInfo') > -1) {
    token  = Cookies.get('token')
  }
  // config.headers.Authorization = token
  config.headers.token = token

  return config
}, error => {
  Promise.reject(error)
})

// respone interceptor
service.interceptors.response.use(
  response => response,
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过xmlhttprequest来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  response => {
    if (response.code == "ECONNABORTED") {
      Message({
        message: '登录超时，服务器无响应',
        type: 'error',
        duration: 3 * 1000
      })
      // // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      // if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
      //   // 请自行在引入 MessageBox
      //   // import { Message, MessageBox } from 'element-ui'
      //   MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
      //     confirmButtonText: '重新登录',
      //     cancelButtonText: '取消',
      //     type: 'warning'
      //   }).then(() => {
      //     store.dispatch('FedLogOut').then(() => {
      //       location.reload() // 为了重新实例化vue-router对象 避免bug
      //     })
      //   })
      // }
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    if (error.response.status === 404) {
      Message({
        message: '[404错误] 请求不存在',
        type: 'error',
        duration: 3 * 1000
      })
    } else {
      Message({
        message: error.message,
        type: 'error',
        duration: 3 * 1000
      })
    }
    return Promise.reject(error)
})

Vue.prototype.$http = Vue.http = service
export default service
