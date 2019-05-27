<template>
  <div class="layout-header">
    <div class="header-left">
      <!--系统LOGO-->
      <div class="logo">
        <svg class="icon" aria-hidden="true" width="160" height="30">
          <use xlink:href="#iconlogo"></use>
        </svg>
      </div>
      <!--系统一级导航-->
      <header-nav ref="headerNav"></header-nav>
    </div>
    <div class="header-right">
      <!--系统顶部右侧信息-->
      <header-info v-on:clear-nav-active="clearNavActive"></header-info>
    </div>
  </div>
</template>

<script>
import HeaderInfo from './HeaderInfo'
import HeaderNav from './HeaderNav'
export default {
  name: 'layoutHeader',
  components: {
    HeaderInfo,
    HeaderNav
  },
  methods: {
    clearNavActive() {
      this.$refs.headerNav.clearNavActive()
    }
  }
}
</script>

<style lang="stylus">
.layout-header
  display flex
  justify-content space-between
  .header-left
    .logo
      float left
      width 280px
      svg
        margin 19px 0px 18px 30px
    .header-nav
      float left
</style>
