<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default{
  name: 'App',
  created () {
    document.title = this.$t('title')
  }
}
</script>

<style lang="stylus">
body
  margin: 0
  height: 100%
  -moz-osx-font-smoothing: grayscale
  -webkit-font-smoothing: antialiased
  text-rendering: optimizeLegibility
  font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif
a, a:focus, a:hover
  cursor pointer
  color inherit
  text-decoration none
.el-menu--vertical
  .el-menu-item
    .icon
      margin-right 16px
.search-box
  .el-form-item__label
    text-overflow ellipsis
    white-space nowrap
    overflow hidden
button.el-button--primary,
button.el-button--default
  padding: 8px 14px
</style>
