import { loginApi, logoutApi } from '@/http/api/user'
import { save, clearAll } from '@/utils/storage'
import Cookies from 'js-cookie'
const user = {
  state: {
    userName: Cookies.get('userName') || '',
    userId: Cookies.get('userId') || null,
    token: Cookies.get('token') || '',
    roleId: Cookies.get('roleId') || '',
    roleName: Cookies.get('roleName') || '',
    photoId: null,
    email: '',
    phone: '',
    fax: '',
    departmentId: '',
    departmentName: '',
    loginTime: '',
    mapCenter: ''
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_USERNAME: (state, userName) => {
      state.userName = userName
    },
    SET_USERID: (state, userId) => {
      state.userId = userId
    },
    SET_ROLEID: (state, roleId) => {
      state.roleId = roleId
    },
    SET_ROLENAME: (state, roleName) => {
      state.roleName = roleName
    },
    SET_PHOTOID: (state, photoId) => {
      state.photoId = photoId
    },
    SET_EMAIL: (state, email) => {
      state.email = email
    },
    SET_PHONE: (state, phone) => {
      state.phone = phone
    },
    SET_FAX: (state, fax) => {
      state.fax = fax
    },
    SET_DEPARTMENTID: (state, departmentId) => {
      state.departmentId = departmentId
    },
    SET_DEPARTMENTNAME: (state, departmentName) => {
      state.departmentName = departmentName
    },
    SET_LOGINTIME: (state, loginTime) => {
      state.loginTime = loginTime
    },
    SET_MAPCENTER: (state, center) => {
      state.mapCenter = center
    }
  },

  getters: {
    userInfo(state) {
      return state
    }
  },

  actions: {
    // 用户名登录
    login({ commit }, userInfo) {
      const username = userInfo.userName.trim()
      return new Promise((resolve, reject) => {
        loginApi(username, userInfo.password).then(response => {
          let data = response.data.data
          if (response.data.code === 10000) {
            commit('SET_TOKEN', data.token)
            commit('SET_USERID', data.userId)
            commit('SET_USERNAME', data.userName)
            commit('SET_LOGINTIME', data.loginTime)
            Object.keys(data).forEach(key => {
              save(key, data[key])
              Cookies.set(key, data[key])
            })
          }
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    logout({ commit, state }) {
      return new Promise((resolve, reject) => {
        logoutApi(state.token).then(() => {
          Cookies.remove('token')
          Cookies.remove('userName')
          Cookies.remove('userId')
          Cookies.remove('furtherInfo')
          Cookies.remove('loginSuccess')
          Cookies.remove('loginTime')
          clearAll()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 设置保存用户基本信息
    setUserInfo({commit, state}, data) {
      commit('SET_ROLEID', data.roleId)
      commit('SET_ROLENAME', data.roleName)
      commit('SET_PHOTOID', data.photoId)
      commit('SET_EMAIL', data.email)
      commit('SET_PHONE', data.phone)
      commit('SET_FAX', data.fax)
      commit('SET_DEPARTMENTID', data.departmentId)
      commit('SET_DEPARTMENTNAME', data.departmentName)
      commit('SET_MAPCENTER', data.mapCenter)
    }
  }
}

export default user
