import Vue from 'vue'
import Vuex from 'vuex'
import globalConfig from './modules/global-config'
import layout from './modules/layout'
import user from './modules/user'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    globalConfig,
    layout,
    user
  }
})

export default store
