import Vue from 'vue'
import VueRouter from 'vue-router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { read } from '@/utils/storage'
import store from '../store'
NProgress.configure({ showSpinner: false })

const defaultRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/Login')
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404')
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401')
  }
]

Vue.use(VueRouter)
const router = new VueRouter({
  mode: 'history',
  routes: defaultRoutes,
  scrollBehavior: () => ({ y: 0 })
})
export default router

export function routerHook () {
  const whiteList = ['/login']
  router.beforeEach((to, from, next) => {
    NProgress.start()
    if (read('token')) {
      /* has token*/
      if (to.path != '/' && store.state.globalConfig.authority.length === 0) {
        store.dispatch('initUserAuthority').then((res) => {
          store.dispatch('setUserInfo', res.data.data)
          next({ path: to.path })
        })
      } else {
        if (to.path === '/login') {
          next({ path: '/' })
          NProgress.done()
        } else if (to.path === '/logout') {
          store.dispatch('LogOut').then(() => {
            next({ path: '/login' })
            NProgress.done()
          })
        } else if (to.path === '/') {
          next( {path: '/monitor'})
          NProgress.done()
        } else {
          if (to.matched.length === 0) {
            next({ path: '/404'})
          }
          let newRouter = []
          router.options.routes.forEach(arr => {
            if (to.matched.some(record => record.path === arr.path)) {
              newRouter = arr.children
            }
          })
          store.commit('SET_SIDEROUTES', newRouter)
          store.commit('SET_NAVTOPACTIVE', to.matched[0].path === '' ? '/' : to.matched[0].path)
          store.commit('SET_NAVSIDEACTIVE', to.path)
          next()
        }
      }
    } else {
      /* has no token*/
      if (whiteList.indexOf(to.path) !== -1) {
        next()
      } else {
        next('/login')
        NProgress.done()
      }
    }
  })

  router.afterEach(() => {
    NProgress.done() // finish progress bar
  })
}
