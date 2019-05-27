import Vue from 'vue'
import App from './App.vue'
import router, { routerHook } from './router'
import store from './store'
import './assets/theme/index.css'
import Element from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
// import './assets/iconfont/iconfont.css'
import './assets/iconfont/iconfont.js'
import './assets/iconfontmap/iconfontmap.css'
import './assets/Ceiec.css'
import './locales'
import './http'
import 'animate.css'
import PerfectScrollbar from 'perfect-scrollbar'
import "perfect-scrollbar/css/perfect-scrollbar.css"
Vue.use(Element, {
  size: 'small'
})

/**
 * @description 为自定义滚动条全局注入自定义指令自动判断该更新PerfectScrollbar还是创建它
 * @param {Element} el - 必填dom元素
 */
const el_scrollBar = (el) => {
  //在元素上加名称，确保不会和已有属性重复此处取名_ceiec_
  if (el._ceiec_ instanceof PerfectScrollbar) {
    el._ceiec_.update()
  } else {
    //el上挂一份属性
    el._ceiec_ = new PerfectScrollbar(el, { suppressScrollX: true })
  }
}

Vue.directive("ceiecScrollbar",{
  inserted(el, binding){
    const { arg } = binding
    if(arg === "table"){ // 如果有该值表明是表格上的滚动条
      el = el.querySelector(".el-table__body-wrapper")
    }
    el_scrollBar(el)
  },
  componentUpdated(el, binding, vnode) {
    const { arg } = binding
    if (arg === "table") {
      el = el.querySelector(".el-table__body-wrapper")
    }
    vnode.context.$nextTick(
      () => {
        el_scrollBar(el)
      }
    )
  }
})

Vue.config.productionTip = false
routerHook()
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
