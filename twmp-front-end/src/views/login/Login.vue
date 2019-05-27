<template>
  <div class="login-container">

    <el-form class="login-form" autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
      <div class="login-logo">
        <svg class="icon" aria-hidden="true" width="320" height="60">
          <use xlink:href="#iconlogo"></use>
        </svg>
      </div>
      <div class="title-container">
        <h3 class="title">{{ $t('login_title') }}</h3>
      </div>

      <el-form-item prop="userName">
        <span class="svg-container svg-container_login">
          <i class="iconfont icon-yonghu"></i>
        </span>
        <el-input name="userName" type="text" v-model="loginForm.userName" autoComplete="on" :placeholder="$t('userName')"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <i class="iconfont icon-mima"></i>
        </span>
        <el-input name="password" :type="passwordType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on"
          :placeholder="$t('password')" />
        <span class="show-pwd" @click="showPwd">
          <i class="iconfont icon-chakan1"></i>
        </span>
      </el-form-item>
      <el-button type="primary" style="width:100%margin-bottom:30px" :loading="loading" 
      @click.native.prevent="handleLogin">{{$t('login')}}
      </el-button>
      <el-checkbox v-model="rememberPass">记住密码</el-checkbox>
      <change-language></change-language>
    </el-form>
  </div>
</template>

<script>
import { isValidUserName } from '@/utils/validateFuncs'
import ChangeLanguage from '@/components/ChangeLanguage'
import { mapActions } from 'vuex'
export default {
  name: "login",
  components: {
    ChangeLanguage
  },
  data() {
    const validateUserName = (rule, value, callback) => {
      if (!isValidUserName(value)) {
        callback(new Error(this.$t('login_validate_userName')))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error(this.$t('login_validate_password')))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        userName: "",
        password: ""
      },
      loginRules: {
        userName: [
          { required: true, trigger: "blur", validator: validateUserName }
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword }
        ]
      },
      passwordType: "password",
      loading: false,
      showDialog: false,
      rememberPass: false
    }
  },
  methods: {
    ...mapActions(['login']),
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = ""
      } else {
        this.passwordType = "password"
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.login(this.loginForm)
            .then((res) => {
              this.loading = false
              if (res.data.code === 10000) {
                this.$router.push({ path: "/monitor" })
              } else {
                this.$message({
                  showClose: true,
                  message: this.$t('login_error'),
                  type: 'error'
                })
              }
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="stylus" scoped>
.login-container
  .el-input
    width 80%
    input
      background transparent
      border 0px
      -webkit-appearance none
      border-radius 0px
      padding 12px 5px 12px 15px
      color #eee
      &-webkit-autofill
        -webkit-box-shadow 0 0 0px 1000px #2d3a4b inset !important
        -webkit-text-fill-color #fff !important
  .el-form-item
    border 1px solid rgba(255, 255, 255, 0.1)
    background rgba(0, 0, 0, 0.1)
    border-radius 5px
    color #454545
    .el-form-item__content
      padding-top 3px

.login-container
  position fixed
  height 100%
  width 100%
  background: url('/images/login-bg.png') no-repeat
  background-color #001b35
  .login-form
    position absolute
    left 0
    right 0
    width 420px
    padding 35px 35px 15px 35px
    margin 120px auto
    height 620px
    background-color #0f2c67
    box-shadow 0 0 30px #041b31
    .login-logo
      text-align center
      margin-top 100px
      margin-bottom 50px
    button
      width 10rem
    label.el-checkbox
      margin-left 50px
  .tips
    font-size 14px
    color #fff
    margin-bottom 10px
    span
      &first-of-type
        margin-right 16px
  .svg-container
    padding 6px 5px 6px 15px
    color #889aa4
    vertical-align middle
    width 30px
    display inline-block
    i
      font-size 20px
  .title-container
    position relative
    margin-bottom 60px
    .title
      font-size 26px
      font-weight 400
      color #eee
      margin 0px auto 40px auto
      text-align center
      font-weight bold
  .show-pwd
    position absolute
    right 10px
    top 7px
    font-size 16px
    color #889aa4
    cursor pointer
    user-select none
</style>
