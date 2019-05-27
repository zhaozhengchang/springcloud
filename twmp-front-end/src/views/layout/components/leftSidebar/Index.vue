<template>
  <div class="sidebar-container">
    <!-- <div class="sidebar-collopse">
      <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff" @click="toggleSideBar" :class="{'is-opened':sidebar.opened}">
        <use xlink:href="#iconnotification"></use>
      </svg>
    </div> -->

    <el-menu
      mode="vertical"
      unique-opened
      :collapse="isCollapse"
      :show-timeout="400"
      background-color="#191730"
      text-color="#666681"
      active-text-color="#FBFBFB"
      :default-active="navSideActive"
      @select="setDefalutOpened">
      <sidebar-item v-for="route in sideRoutes"
        :key="route.path"
        :item="route"
        :base-path="route.path">
      </sidebar-item>
    </el-menu>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import SidebarItem from './SidebarItem'

export default {
  components: { SidebarItem },
  data: () => {
    return {
      defaultActive: '',
      defaultOpened: []
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'sideRoutes',
      'navSideActive'
    ]),
    isCollapse () {
      return !this.sidebar.opened
    }
  },
  methods: {
    ...mapActions(['toggleSideBar','setNavSideActive']),
    setDefalutOpened (index, indexPath) {
      // this.defaultActive = index
      this.setNavSideActive(index)
      this.defaultOpened = indexPath
    }
  },
  watch: {
    '$route' () {
      this.defaultActive = this.sideRoutes.length > 0 ? this.sideRoutes[0].path : ''
    }
  }
}
</script>
<style lang="stylus">
.sidebar-container
  position relative
  transition width 0.28s
  margin 15px 0px
  height calc(100% - 50px)
  .el-scrollbar
    margin-top 30px
    height 100%
    .scrollbar-wrapper
      overflow-x hidden !important
      .el-scrollbar__wrap
        overflow-x hidden
      .el-scrollbar__view
        height 100%
  .el-menu
    height 100%
    width 280px
    background #191730
    border-radius 0 6px 6px 0
    padding 10px 0px
    border-right 1px solid #191730
  .el-submenu 
    .el-menu-item
      line-height 45px
      background-color #1f2d3d !important
      &:hover
        background-color #001528 !important
  .sidebar-collopse
    position relative
    z-index 500
    svg
      float right
      font-size 22px
      cursor pointer
      display inline-block
      transform rotate(180deg)
      transition .28s
      transform-origin 50% 50%
    svg.is-opened
      transform rotate(0deg)
  a
    display inline-block
    width 100%
    overflow hidden
.hideSidebar
  .sidebar-container
    width 36px !important
    .submenu-title-noDropdown
      padding-left 10px !important
      position relative
      .el-tooltip
        padding 0 10px !important
    .el-submenu > .el-submenu__title 
      padding-left 10px !important
      .el-submenu__icon-arrow
        display none
</style>
