<template>
  <el-dropdown trigger="hover" class='set-skin' @command="handleSetSkin">
    <div>
      <i class="icon iconfont icon-skin"></i>
    </div>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="black" :disabled="globalConfig.skin==='black'">黑色</el-dropdown-item>
      <el-dropdown-item command="white" :disabled="globalConfig.skin==='white'">白色</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
export default {
  name: 'changeSkin',
  computed: {
    ...mapGetters(['globalConfig'])
  },
  methods: {
    ...mapActions(['setSkin']),
    handleSetSkin(skin) {
      this.setSkin(skin)
    }
  }
}
</script>

<style lang="stylus">
.set-skin
    color #fff
    float right
    cursor pointer
    &:hover
      background-color #1f2833
    i
      font-size 22px
</style>
