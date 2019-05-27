<template>
  <el-menu
    ref="navList"
    class="header-nav" 
    mode="horizontal"
    background-color="#0D0C1E"
    text-color="#666681"
    active-text-color="#50E3C2"
    :default-active="navTopActive">
    <template v-for="item in topNavItems">
      <el-menu-item @click="clickNav(item)" :index="item.path" v-if="item.show === 1" :key="item.path">
        <svg class="icon" aria-hidden="true" width="18" height="18" fill="#666681">
          <use :xlink:href="'#' + item.icon"></use>
        </svg>
        <label>{{$t(item.title)}}</label>
      </el-menu-item>
    </template>
    <el-menu-item index="/user" style="display:none"></el-menu-item>
  </el-menu>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'headerNav',
  computed: {
    ...mapGetters(['navTopActive']),
    topNavItems () {
      return this.$router.options.routes
    }
  },
  methods: {
    ...mapActions(['setSideRoutes','setNavTopActive', 'setNavSideActive']),
    clickNav (item) {
      this.$router.push({ path: item.children[0].path })
      // this.$router.options.routes.forEach(el => {
      //   if (el.path && el.path === item.path) {
      //     debugger
      //     this.setSideRoutes(el.children)
      //     this.setNavTopActive(item.path)
      //     this.setNavSideActive(el.children.length > 0 ? el.children[0].children[0].path : '')
      //   }
      // })
    },
    clearNavActive() {
      this.$refs.navList.updateActiveIndex('/user')
    }
  }
}
</script>

<style lang="stylus">
ul.header-nav
  border-bottom none
  label
    font-family ArialMT
    font-size 14px
    color #666681
    letter-spacing 0
    margin-left 18px
  .el-menu-item
    height 67px
    line-height 67px
    &.is-active
      border-bottom-color #0D0C1E !important
    &:hover
      >svg,label
        cursor pointer
        fill #9A9AC6
        color #9A9AC6
  .el-menu-item.is-active
    svg,label
      fill #50E3C2
      color #50E3C2
</style>
