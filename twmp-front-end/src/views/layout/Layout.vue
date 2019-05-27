<template>
  <div class="layout-wrapper" :class="hideSideBar ? classObj : ''">
    <el-header height="67px">
      <!--顶部导航栏-->
      <headerbar></headerbar>
    </el-header>
    <el-container class="layout-container">
      <!--左侧导航栏-->
      <sidebar v-if="!hideSideBar"></sidebar>
      <!--主页面内容-->
      <el-scrollbar>
        <section class="layout-main">
          <transition name="fade" mode="out-in">
              <router-view :key="key"></router-view>
          </transition>
        </section>
      </el-scrollbar>
    </el-container>
  </div>
</template>

<script>
import Headerbar from './components/header/Index'
import Sidebar from './components/leftSidebar/Index'
import { mapGetters } from 'vuex'
export default {
  name: 'layout',
  components: {
    Headerbar,
    Sidebar
  },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    key() {
      return this.$route.fullPath
    },
    hideSideBar () {
      let noSideBar = [
        '/monitor',
        '/user/userCenter',
        '/analysis'
      ]
      return noSideBar.some((v) => {
        return this.$route.path.indexOf(v) > -1
      })
    },
    classObj() {
      return {
        showSideBar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation
      }
    }
  }
}
</script>
<style lang="stylus">
@import 'element-ceiec.styl'
// svg.icon
//   fill #666681
//   &:hover
//     fill #9A9AC6
.el-header
  background #0D0C1E !important
.layout-container
  .el-scrollbar
    width 100%
    .el-scrollbar__wrap
      overflow-x hidden
    .el-scrollbar__bar.is-horizontal
      display none
.layout-main
  height 100%
  transition margin-left .28s
  margin 15px
  background #14142c
  border-radius 6px
  overflow hidden
/* 开始过渡阶段,动画出去阶段 */
.fade-enter-active
.fade-leave-active
  transition all 0.3s ease-out
/* 进入开始 */
.fade-enter
  transform translateX(100px)
  opacity 0

/* 出去终点 */
.fade-leave-active
  transform translateX(-100px)
  opacity 0

.search-table-page
  position absolute
  background #201f3d
  border-radius 6px
  width calc(100% - 30px)
  height calc(100% - 30px)
  .tool-bar
    display flex
    justify-content space-between
    align-items center
    padding 0px 30px
    height 64px
    .button-bar > button
      width 30px
      height 30px
      border-radius 4px
      padding 0px
    .search-bar
      display flex
      justify-content flex-end
      align-items center
      position relative
      .el-input,.el-select,.el-date-editor
        width 360px
        margin-right 15px
      .ceiec-department > .el-input
        width 360px
        margin-right 15px
      .el-input-group__append, .el-input-group__prepend
        border unset
        background-color #191730
        color #666681
        padding-right 0px
      .ceiec-select-input
        .el-select
          width 65px
          margin-right 0px
          & > .el-input
            width 65px
          .el-input__prefix
            svg
              margin-top 7px
          .el-input__suffix
            .el-input__suffix-inner
              border-right 1px solid #3D3D64
              padding 4px 5px 4px 0px
        .el-input__inner
          padding 0px 10px
  .el-table
    position inherit
    .el-table__body-wrapper
      position absolute
  .pagination
    position absolute
    bottom 0px
    width 100%
    height 47px
    background #282747
    border-radius 0 0 6px 6px
    display flex
    align-items center
    justify-content center
.ceiec-select-input-popper
  .el-select-dropdown__item
    display flex
    align-items center
    padding 0px 7px
    & > svg
      margin-right 10px
</style>

<style lang="stylus" scoped>
.layout-container
  position fixed
  height calc(100% - 67px)
  width 100%
  top 67px
  background-color #14142c
.el-header
  position fixed
  top 0px
  width 100%
  padding: 0
  color #ffffff
  background-color #004384
</style>