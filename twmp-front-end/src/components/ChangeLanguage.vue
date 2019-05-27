<template>
  <el-dropdown trigger="hover" class='set-language' @command="handleSetLanguage">
    <div>
      <i class="icon iconfont icon-duoyuyan" icon-class="language"></i>
    </div>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="zh" :disabled="globalConfig.language==='zh'">中文</el-dropdown-item>
      <el-dropdown-item command="en" :disabled="globalConfig.language==='en'">English</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
export default {
  name: 'changeLanugage',
  computed: {
    ...mapGetters(['globalConfig'])
  },
  methods: {
    ...mapActions(['setLanguage']),
    handleSetLanguage(lang) {
      this.setLanguage(lang)
    }
  }
}
</script>

<style lang="stylus">
.set-language
    color #fff
    float right
    cursor pointer
    &:hover
      background-color #1f2833
    i
      font-size 25px
</style>
