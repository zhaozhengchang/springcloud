<template>
  <div class="header-right-menu">
    <!-- 语言国际化切换 -->
      <el-dropdown placement="bottom" @command="handleSetLanguage">
        <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
          <use xlink:href="#iconlanguage"></use>
        </svg>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="zh-CN">中文</el-dropdown-item>
          <el-dropdown-item divided command="en">英文</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    <!-- 消息提示 -->
      <el-dropdown placement="bottom">
        <el-badge :value="1000" :max="999" class="item">
          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
            <use xlink:href="#iconnotification"></use>
          </svg>
        </el-badge>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item divided :command="item.id" v-for="(item, index) in informations" :key="index">
            {{ item.text }}
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    
    <!-- 用户头像 -->
    <el-tooltip :content="userInfo.userName" placement="bottom" effect="dark">
      <div class="header-avatar" @click="navToUserCenter">
        <img :src="userHeaderUrl" width="36" height="36">
      </div>
    </el-tooltip>
    <div class="header-split"></div>
    <!-- 退出登录 -->
    <el-tooltip class="header-logout" :content="$t('header_logout')" placement="bottom" effect="dark">
      <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681" @click="handleLogout">
        <use xlink:href="#iconlogout"></use>
      </svg>
    </el-tooltip>

  </div>
</template>

<script>
import screenfull from 'screenfull'
import {mapGetters,mapActions} from 'vuex'
import webBaseUrl from '@/utils/baseUrl'
export default {
  name: 'headerInfo',
  data: () => {
    return {
      userHeaderUrl: '/images/user/default.png',
      informations: [
        {id: 1, text: '测试信息1'},
        {id: 2, text: '测试信息2'},
        {id: 3, text: '测试信息3'},
        {id: 4, text: '测试信息4'},
        {id: 5, text: '测试信息5'}
      ]
    }
  },
  computed: {
    ...mapGetters(['globalConfig', 'userInfo'])
  },
  methods: {
    ...mapActions(['setLanguage', 'logout', 'setNavTopActive']),
    // 更新语言
    handleSetLanguage(lang) {
      // this.$i18n.locale = lang
      this.setLanguage(lang)
    },
    // 退出登录
    handleLogout () {
      this.$confirm('确定要退出登录吗', '提示', {
        type: 'warning'
      }).then(() => {
        const loading = this.$loading({
          lock: true,
          text: '正在退出...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        this.logout().then(() => {
          loading.close()
          window.location.reload()
          //this.$router.push({ path: "/login" })
        })
      }).catch(()=>{})
    },
    navToUserCenter() {
      this.$emit('clear-nav-active')
      this.$router.push({ path: '/user/userCenter'})
    }
    // // 全屏显示
    // handleFullScreen () {
    //   if (!screenfull.enabled) {
    //     this.$message({
    //       message: 'you browser can not work',
    //       type: 'warning'
    //     })
    //     return false
    //   }
    //   screenfull.toggle()
    // }
  },

  mounted() {
    this.userHeaderUrl = webBaseUrl + '/downloadFile/false/' + this.userInfo.photoId
  }
}
</script>

<style lang="stylus" scoped>
.header-right-menu
  height 67px
  line-height 67px
  display flex
  .el-dropdown
    cursor pointer
    width 44px
    text-align center
    &:hover
      background-color #020031
    svg
      margin 20px 0px 19px 0px
      &:hover
        fill #9A9AC6
  .header-avatar
    width 42px
    height 42px
    margin 13px 20px 12px 24px
    border 1px solid #53526D
    border-radius 50%
    text-align center
    img
      border-radius 50%
      cursor pointer
      margin-top 1px
  .header-split
    border-right 1px solid #484867
    height 23px
    margin 22px 0px 22px 0px
  .header-logout
    width 28px
    height 28px
    margin 20px 24px 19px 20px
    cursor pointer
    &:hover
      fill #9A9AC6
</style>
<style lang="stylus">
.el-badge
  .el-badge__content
    border none
    &.is-fixed
      top 18px
      right 15px
      z-index 120
</style>
