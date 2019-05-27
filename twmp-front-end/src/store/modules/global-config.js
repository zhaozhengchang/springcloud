import Vue from 'vue'
import { save, read } from '@/utils/storage'
import { Ceiec } from 'ceiec.maps'
import router from '@/router/index'
import { generateRoutes } from '@/utils/generateRoutes'
import { getUserInfoApi } from '@/http/api/user'
const config = {
  state: {
    defaultDateFormat: 'yyyy-MM-dd',       // 系统默认日期格式
    defaultDateTimeFormat: 'yyyy-MM-dd HH:mm:ss', //系统默认日期时间格式
    pickerOptions: { // element日期快速选择配置项
      shortcuts: [{
        text: '最近一周',
        onClick(picker) {
          const end = new Date();
          const start = new Date();
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
          picker.$emit('pick', [start, end]);
        }
      }, {
        text: '最近一个月',
        onClick(picker) {
          const end = new Date();
          const start = new Date();
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
          picker.$emit('pick', [start, end]);
        }
      }, {
        text: '最近三个月',
        onClick(picker) {
          const end = new Date();
          const start = new Date();
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
          picker.$emit('pick', [start, end]);
        }
      }]
    },
    pageLimit: 20,                         // 默认表格每页数据
    language: read('language') || 'zh-CN',    // 默认语言
    skin: read('skin') || 'black',         // 默认系统皮肤
    ceiecMapOptions: {
      center: new Ceiec.Maps.Point(11584948.880957885, 3592846.0775164245, Ceiec.Maps.ProjectionTypes.EPSG3857), // 默认地图中心点：x经度 y纬度
      zoom: 10,                            // 地图缩放级别
      minZoom: 1,                          // 地图最小缩放级别
      maxZoom: 19                          // 地图最大缩放级别
    },
    authority: [],                         // 用户权限
    department: [],                        // 用户组织机构
    showMonitorPanel: false,               // 显示监控详情面板
    pictureTypes: ['image/png','image/jpeg','image/bmp','image/gif'], // 系统默认上传图片支持类型
    pictureSize: 1024 * 1024 * 2,          // 系统默认上传图片支持最大值2M
    fileTypes: [''],
    fileSize: 1024 * 1024 * 5
  },

  mutations: {
    UPDATE_PAGELIMIT (state, pageLimit) {
      state.pageLimit = pageLimit
    },
    SET_LANGUAGE: (state, language) => {
      Vue.config.lang = language
      state.language = language
      save('language', language)
    },
    SET_SKIN: (state, skin) => {
      state.skin = skin
      save('skin', skin)
    },
    SET_AUTHORITY: (state, authority) => {
      state.authority = authority
    },
    SET_DEPARTMENT: (state, department) => {
      state.department = department
    },
    SET_SHOWMONITORPANEL: (state, show) => {
      state.showMonitorPanel = show
    }
  },

  getters: {
    globalConfig (state) {
      return state
    }
  },

  actions: {
    updatePageLimit ({commit}, pageLimit) {
      commit('UPDATE_PAGELIMIT', pageLimit)
    },
    setLanguage({ commit }, language) {
      commit('SET_LANGUAGE', language)
    },
    setSkin({ commit }, skin) {
      commit('SET_SKIN', skin)
    },
    updateDepartment({commit}, department) {
      commit('SET_DEPARTMENT', department)
    },
    //获取用户配置信息
    initUserAuthority({ commit }) {
      return new Promise((resolve, reject) => {
        getUserInfoApi().then((res) => {
          if(res.data.code === 10000) {
            // 存储组织机构数据
            commit('SET_DEPARTMENT', res.data.data.departmentTree)
            // 后端返回权限列表生成动态路由结构
            let dynamicRoutes = generateRoutes(res.data.data.authorityTree)
            router.options.routes = [...router.options.routes, ...dynamicRoutes]
            commit('SET_AUTHORITY', router.options.routes)
            router.addRoutes(dynamicRoutes)
            resolve(res)
          } else {
            reject()
          }
        }).catch( err => {
          reject(err)
        })
      })
    },
    updateShowMonitorPanel ({commit}, show) {
      commit('SET_SHOWMONITORPANEL', show)
    }
  }
}
export default config
