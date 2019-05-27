<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item v-for="item in navList" :key="item.path">
      {{ $t('route.' + item.meta.title) }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
export default {
  name: 'navBreadcrumn',
  data: () => {
    return {
      navList: ''
    }
  },
  mounted () {
    this.navList = this.$route.matched
  }
}
</script>

<style lang="stylus" scoped>
.el-breadcrumb
  line-height 36px
  border-bottom 1px solid #bfcbd9
  padding 0px 5px
</style>
