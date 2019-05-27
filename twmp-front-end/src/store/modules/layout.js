import Cookies from 'js-cookie'

const layout = {
  state: {
    sidebar: {                                    // 左侧导航展开隐藏
      opened: !+Cookies.get('sidebarStatus'),
      withoutAnimation: false
    },
    sideRoutes: [],            // 左侧导航路由数据
    navTopActive: '',                             // 顶部导航默认选中项
    navSideActive: ''                            // 左侧导航默认选中项
  },
  mutations: {
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        Cookies.set('sidebarStatus', 1)
      } else {
        Cookies.set('sidebarStatus', 0)
      }
      state.sidebar.opened = !state.sidebar.opened
      state.sidebar.withoutAnimation = false
    },
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
      Cookies.set('sidebarStatus', 1)
      state.sidebar.opened = false
      state.sidebar.withoutAnimation = withoutAnimation
    },
    SET_SIDEROUTES: (state, routes) => {
      state.sideRoutes = routes
    },
    SET_NAVTOPACTIVE: (state, path) => {
      state.navTopActive = path
    },
    SET_NAVSIDEACTIVE: (state, path) => {
      state.navSideActive = path
    }
  },
  getters: {
    sidebar (state) {
      return state.sidebar
    },
    sideRoutes (state) {
      return state.sideRoutes
    },
    navTopActive (state) {
      return state.navTopActive
    },
    navSideActive (state) {
      return state.navSideActive
    }
  },
  actions: {
    toggleSideBar({ commit }) {
      commit('TOGGLE_SIDEBAR')
    },
    closeSideBar({ commit }, { withoutAnimation }) {
      commit('CLOSE_SIDEBAR', withoutAnimation)
    },
    setSideRoutes({ commit }, routes) {
      commit('SET_SIDEROUTES', routes)
    },
    setNavTopActive({ commit }, path) {
      commit('SET_NAVTOPACTIVE', path)
    },
    setNavSideActive({ commit }, path) {
      commit('SET_NAVSIDEACTIVE', path)
    }
  }
}

export default layout
