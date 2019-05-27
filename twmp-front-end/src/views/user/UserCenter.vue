<template>
  <div class="user-center-page">
    <el-tabs type="card" @tab-click="tabClick">
      <!-- 用户消息中心模块 -->
      <el-tab-pane label="消息中心" class="user-message-tab">
        <div class="user-message">
          <div class="message-header">
            <div class="switch">
              <el-switch
                v-model="readSwitch"
                active-color="#2DAC8F"
                inactive-color="#cccccc">
              </el-switch>
              <span class="switch-title">{{ readSwitch ? '未读' : '全部'}}</span>
            </div>
            <div class="message-search">
              <el-select v-model="messageType" placeholder="消息类型">
                <el-option
                  v-for="item in messageTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
              <el-select v-model="messageSubType" placeholder="消息子类型">
                <el-option
                  v-for="item in messageSubTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
              <el-date-picker
                v-model="timeRange"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :format="globalConfig.defaultDateFormat"
                :value-format="globalConfig.defaultDateFormat"
                :picker-options="globalConfig.pickerOptions">
              </el-date-picker>
              <el-button class="ceiec-query" @click="messageQuery(false)">
                <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                    <use xlink:href="#iconsearchicon"></use>
                </svg>
              </el-button>
            </div>
          </div>
          <div class="message-title">
            <el-button size="medium" class="ceiec-default" 
              @click="markMessageAllRead" 
              :disabled="messageList.length > 0 ? false : true">
              全部标记为已读
            </el-button>
          </div>
          <div class="no-data-tip" v-show="isEmpty || isLoading">
            <div class="empty" v-show="isEmpty">
              <img src="/images/empty.png" alt="loading">
              <p>{{$t('table_no_data')}}</p>
            </div>
            <div class="loading" v-show="isLoading">
              <img src="/images/loading.svg" alt="loading">
            </div>
          </div>
          <div class="message-list" v-show="!isEmpty && !isLoading" v-ceiecScrollbar>
            <div :class="['message-box', item.readerId == null ? '' : 'is-read', item.checked ? 'checked' : '']"  
              v-for="item in messageList" 
              :key="item.messageId"
              @click="markMessageRead(item)">
              <div :class="['message-icon', item.messageType == '1' ? 'alarm' : 'notice']">
                <div class="message-icon-inner">
                  <svg class="icon" aria-hidden="true" width="28" height="28" fill="#FFFFFF">
                    <use :xlink:href="item.messageType == '1' ? '#icongaojingicon': '#icontongzhi'"></use>
                  </svg>
                </div>
              </div>
              <div class="message-content">
                <div class="title">{{ item.messageComment }}</div>
                <div class="time">{{ item.messageTime }}</div>
                <div class="mark">{{ item.readerId == null ? '' : '于' + item.readerTime + '被 ' + item.reader + '查看' }}</div>
                <div class="checked" v-show="item.checked">
                  <svg class="icon" aria-hidden="true" width="20" height="20" fill="#FFFFFF">
                    <use xlink:href="#iconyidu"></use>
                  </svg>
                </div>
              </div> 
            </div>
          </div>
          <div class="message-page">
            <el-pagination
              @current-change="messageQuery(false)"
              :current-page.sync="pageInfo.currentPage"
              :page-size="pageInfo.pageSize"
              layout="prev, pager, next, total"
              :total="pageInfo.total">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
      <!-- 用户个人信息编辑 -->
      <el-tab-pane label="个人信息" class="user-info-tab">
        <div class="loading-info" v-show="isLoadingInfo">
          <img src="/images/loading.svg" alt="loading">
        </div>
        <div class="user-info-data">
          <div class="user-info-map">
            <div class="map-center-top">
              <div class="title">设置地图开始位置</div>
            </div>
            <div class="map-center-bottom">
              <div class="map-center-bottom-font">
              当前地图中心位置<br/>
              经度：<span>{{ userMapCenter.center.x }}</span> 
              纬度：<span>{{ userMapCenter.center.y }}</span>
              缩放层级：<span>{{ userMapCenter.zoom }}</span>
              </div>
            </div>
            <div class="map-container" id="mapContainer" ref="mapContainer">
              <ceiec-map mapContainer="mapContainer" v-on:showMapCenter="showMapCenter" ref="userInfoMap"></ceiec-map>
            </div>
          </div>
          <div class="user-info-form">
            <img-croppa v-model="headerPic"
              :width="94"
              :height="94"
              :initial-image="initialHeaderPic"
              placeholder="头像上传"
              placeholder-color="#9A9AC6"
              :placeholder-font-size="14"
              canvas-color="#191730"
              :show-remove-button="true"
              @image-remove="removeHeader"
              remove-button-color="black"
              :show-loading="true"
              :loading-size="50"
              :prevent-white-space="true"
              loading-color="#606060"
              :zoom-speed="10"
              :disable-scroll-to-zoom="true"
              :loading-end="endLoadingHeader"
              @new-image-drawn="showNewHeader">
            </img-croppa>
            <div class="header-slider">
              <svg class="icon" aria-hidden="true" width="20" height="20" fill="#666681" style="margin: 8px 10px 0px 0px">
                <use xlink:href="#iconxiaoren"></use>
              </svg>
              <el-slider
                v-model="headerPicSliderVal"
                :disabled="headerPicSliderDisabled"
                :show-tooltip="false"
                :min="0"
                :max="1"
                :step="0.1"
                height="26"
                @change="sliderZoomHeader">
              </el-slider>
              <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681" style="margin: 4px 0px 5px 0px">
                <use xlink:href="#iconxiaoren"></use>
              </svg>
            </div>
            <el-form ref="userInfoForm" 
              label-position="top" 
              :model="userInfoForm"
              :rules="userInfoRules">
              <el-form-item label="用户名">
                <el-input v-model="userInfo.userName" disabled></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="userInfoForm.email" :maxlength="100"></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input v-model="userInfoForm.phone" :maxlength="10"></el-input>
              </el-form-item>
              <el-form-item label="传真号" prop="phone">
                <el-input v-model="userInfoForm.fax" :maxlength="10"></el-input>
              </el-form-item>
              <el-form-item label="角色">
                <el-input v-model="userInfo.roleName" disabled></el-input>
              </el-form-item>
              <el-form-item label="组织机构">
                <el-input v-model="userInfo.departmentName" disabled></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
        <div class="footer">
          <el-tooltip class="item" effect="dark" content="恢复默认" placement="top-start">
            <el-button @click="resetUserInfo" class="ceiec-default">
              <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                <use xlink:href="#iconreset"></use>
              </svg>
            </el-button>
          </el-tooltip>
          <el-button class="ceiec-primary confirm" @click="submitUserInfo">确认</el-button>
        </div>
      </el-tab-pane>
      <!-- 用户密码修改 -->
      <el-tab-pane label="密码修改" class="user-password-tab">
        <div class="user-password">
          <div class="password-header">
            <div class="line"></div>
            <svg class="icon" aria-hidden="true" width="52" height="52">
              <use xlink:href="#iconpasswordicon"></use>
            </svg>
            <div class="line"></div>
          </div>
          <div class="password-title">密码修改</div>
          <el-form ref="passwordForm" 
            label-position="top" 
            :model="passwordForm"
            :rules="passwordRules">
            <el-form-item label="原始密码" prop="password">
              <el-input v-model="passwordForm.password" type="password"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="password">
              <el-input v-model="passwordForm.newPassword" type="password"></el-input>
            </el-form-item>
            <el-form-item label="重复新密码" prop="newPassword2">
              <el-input v-model="passwordForm.newPassword2" type="password"></el-input>
            </el-form-item>
          </el-form>
          <div class="footer">
          <el-tooltip class="item" effect="dark" content="恢复默认" placement="top-start">
            <el-button @click="resetUserInfo" class="ceiec-default">
              <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                <use xlink:href="#iconreset"></use>
              </svg>
            </el-button>
            </el-tooltip>
            <el-button class="ceiec-primary confirm" @click="submitPassword">确认</el-button>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import CeiecMap from '@/components/CeiecMap/Index'
import { validateEmail, validatePassword, validatePhone } from '@/utils/validateFuncs'
import Croppa from 'vue-croppa'
import { mapGetters, mapActions } from 'vuex'
import { messageQueryApi, messageReadApi, userInfoEditApi, usePasswordEditApi} from '@/http/api/user'
import { fileUploadApi, fileDownloadApi } from '@/http/api/system'
import webBaseUrl from '@/utils/baseUrl'
export default {
  components: {
    'img-croppa': Croppa.component,
    CeiecMap
  },
  data() {
    return {
      messageCheckedList: [],
      messageList: [],
      readSwitch: true,
      messageType: "",
      messageSubType: "",
      timeRange: '',
      messageTypes: [
        { label: '告警消息', value: 1},
        { label: '通知消息', value: 2}
      ],
      messageSubTypes: [
        { label: '暴力拆卸', value: 1},
        { label: '低电量', value: 2},
        { label: '越界', value: 3},
        { label: '设备离线', value: 4},
      ],
      pageInfo: {
        currentPage: 1,
        pageSize: 18,
        total: 0
      },
      headerPic: {},
      initialHeaderPic: null,
      headerPicSliderDisabled: false,
      headerPicSliderMin: 0,
      headerPicSliderMax: 0,
      headerPicSliderVal: 0,
      userMapCenter: {
        center: {
          x: 0,
          y: 0
        },
        zoom: 0
      },
      userInfoForm: { // 用户信息Form对象
        userId: null,
        email: '',
        phone: '',
        fax: '',
        photoUrl: null
      },
      userInfoRules: { // 用户信息校验对象
        email: [{validator: validateEmail, message: this.$t('validator_email'), trigger: 'blur'}],
        phone: [{validator: validatePhone, message: '联系电话格式错误', trigger: 'blur'}]
      },
      passwordForm: { // 密码更新Form对象
        userId: null,
        password: '',
        newPassword: '',
        newPassword2: ''
      },
      passwordRules: { // 密码更新校验对象
        password: [
          {required: true, message: this.$t('validator_not_empty'), trigger: 'blur'},
          {validator: validatePassword, message: '密码格式错误', trigger: 'blur'}
        ],
        newPassword2: [
          {required: true, message: this.$t('validator_not_empty'), trigger: 'blur'},
          {validator: validatePassword, message: '密码格式错误', trigger: 'blur'},
          {validator: this.passwordRepeat, message: '两次新密码不一致', trigger: 'blur'}
        ]
      },
      isLoading: true,
      isLoadingInfo: true,
      headerImage: null
    }
  },

  computed: {
    ...mapGetters(['userInfo', 'globalConfig']),
    isEmpty () {
      return !this.messageList.length && !this.isLoading
    }
  },
  
  methods: {
    ...mapActions(['initUserAuthority']),
    tabClick(tab) {
      let tabIdx = tab.index
      if (tab.index === "1") {
        this.initUserInfo()
      }
    },
    // 消息列表查询
    messageQuery (isDefault) {
      this.messageList = []
      this.pageInfo.total = 0
      let postData = {
        messageType: this.messageType,
        messageSubType: this.messageSubType,
        messageStatus: this.readSwitch ? "1" : "2",
        startTime: this.timeRange[0],
        endTime: this.timeRange[1],
        pageNo: isDefault ? 1 : this.pageInfo.currentPage,
        pageSize: this.pageInfo.pageSize
      }
      this.isLoading = true
      messageQueryApi(postData).then((res) => {
        this.isLoading = false
        if (res.data.code === 10000) {
          this.pageInfo.total = res.data.data.total
          res.data.data.items.forEach((v) => {
            let m = {
              messageComment: v.messageComment,
              messageId: v.messageId,
              messageTime: v.messageTime,
              messageType: v.messageType,
              reader: v.reader,
              readerId: v.readerId,
              readerTime: v.readerTime,
              checked: false
            }
            this.messageList.push(m)
          })
        }
      })
    },
    // 全部消息已读
    markMessageAllRead () {
      if (this.messageList.length === 0) {
        this.$message({
          message: '请选择消息',
          type: 'warning'
        })
        return
      }
      let readList = []
      this.messageList.forEach((v) => {
        if (!v.readerId) {
          readList.push(v.messageId)
        }
      })
      if (readList.length === 0) {
        this.$message({
          message: '请选择消息',
          type: 'warning'
        })
        return
      }
      messageReadApi({messageIds: readList.join(',')}).then((res) => {
        if (res.data.code === 10000) {
          this.$message({
            message: '所选消息已读',
            type: 'success'
          })
        } else {
          this.$message({
            message: '标注已读失败',
            type: 'error'
          })
        }
      })
    },
    // 标记消息为已读
    markMessageRead (item) {
      if(!item.readerId) {
        messageReadApi({messageIds: JSON.stringify(item.messageId) }).then((res) => {
          if (res.data.code === 10000) {
            item.checked = true
            this.$message({
              message: '所选消息已读',
              type: 'success'
            })
          } else {
            this.$message({
              message: '标注已读失败',
              type: 'error'
            })
          }
        })
      }
    },
    // 动态更改地图中心点显示
    showMapCenter (data) {
      this.userMapCenter = data
    },
    // 缩放头像
    zoomHeader () {
      this.headerPicSliderVal = this.headerPic.scaleRatio
    },
    // 拖动缩放头像
    sliderZoomHeader (evt) {
      //let increment = this.headerPicSliderVal
      this.headerPic.scaleRatio = this.headerPicSliderVal
    },
    // 重绘制新图像
    showNewHeader () {
      this.isLoadingInfo = false
      this.headerPicSliderVal = this.headerPic.scaleRatio
      this.headerPicSliderMin = this.headerPic.scaleRatio / 2
      this.headerPicSliderMax = this.headerPic.scaleRatio * 2
    },
    // 重置用户信息
    resetUserInfo () {
      this.userInfoForm.userId = this.userInfo.userId
      this.userInfoForm.email = this.userInfo.email
      this.userInfoForm.phone = this.userInfo.phone
      this.userInfoForm.fax = this.userInfo.fax
      this.userInfoForm.photoUrl = this.userInfo.photoId
    },
    // 更新用户信息
    submitUserInfo () {
      this.$refs['userInfoForm'].validate((valid) => {
        if(valid){
          this.$refs['userInfoForm'].clearValidate()
          let fileObj = this.headerPic.getChosenFile()
          // 先上传图片再传数据
          if (fileObj) {
            this.headerPic.generateBlob((blob) => {
              let fd = new FormData()
              fd.append('file', blob, fileObj.name)
              fd.append('fileName', fileObj.name)
              fd.append('fileType', 1)
              fileUploadApi(fd).then((res) => {
                if (res.data.code === 10000) {
                  this.userInfoForm.photoUrl = res.data.data
                  userInfoEditApi(this.userInfoForm).then((res) => {
                    if (res.data.code === 10000) {
                      this.$message({
                        message: '更新成功',
                        type: 'success'
                      })
                      this.initUserAuthority()
                    } else {
                      this.$message({
                        message: '更新失败',
                        type: 'error'
                      })
                    }
                  })
                } else {
                  this.$message({
                    message: '头像上传失败',
                    type: 'error'
                  })
                }
              })
            })
          // 直接传数据
          } else {
            this.userInfoForm.photoUrl = null
            userInfoEditApi(this.userInfoForm).then((res) => {
              if (res.data.code === 10000) {
                this.$message({
                  message: '更新成功',
                  type: 'success'
                })
                this.initUserAuthority()
              } else {
                this.$message({
                  message: '更新失败',
                  type: 'error'
                })
              }
            })
          }
        } else {
          return false
        }
      })
    },
    // 更新用户密码
    submitPassword() {
      this.$refs['passwordForm'].validate((valid) => {
        if (valid) {
          let postData = {
            userId: this.userInfo.userId,
            password: this.passwordForm.password,
            newPassword: this.passwordForm.newPassword
          }
          usePasswordEditApi(postData).then((res) => {
            if (res.data.code === 10000) {
              this.$message({
                message: '密码修改成功',
                type: 'success'
              })
              this.$refs['passwordForm'].resetFields()
              this.passwordForm.password = ''
              this.passwordForm.newPassword = ''
            } else {
              this.$message({
                message: '密码修改失败',
                type: 'error'
              })
            }
          })
        } else {
          return false
        }
      })
    },
    // 密码重复校验
    passwordRepeat(rule, value, cb) {
      if (value != this.passwordForm.newPassword) {
        cb(new Error(rule.message))
      } else {
        cb()
      }
    },
    // 初始用户头像
    initUserInfo() {
      this.isLoadingInfo = true
      // 设置地图位置
      if (this.userInfo.mapCenter !== "") {
        let mapInfo = JSON.parse(this.userInfo.mapCenter)
        this.userMapCenter.center.x = mapInfo.center.x
        this.userMapCenter.center.y = mapInfo.center.y
        this.userMapCenter.zoom = mapInfo.zoom
        this.$refs.userInfoMap.setDefalutMapCenter(this.userMapCenter)
      }
      this.resetUserInfo()
      if (this.userInfo.photoId !== null) {
        if (this.headerImage.width > 0) {
          this.initialHeaderPic = this.headerImage
          this.headerPicSliderDisabled = true
          this.headerPic.refresh()
        } else {
          this.isLoadingInfo = false
          this.$message.error('用户头像载入出错')
        }
      }
    },

    removeHeader() {
      this.headerPicSliderDisabled = false
    },

    endLoadingHeader() {
      this.isLoadingInfo = false
    }
  },

  mounted() {
    this.messageQuery(true)
    this.headerImage = new Image()
    this.headerImage.setAttribute('crossorigin', 'anonymous')
    this.headerImage.src = webBaseUrl + '/downloadFile/false/' + this.userInfo.photoId
  }
}
</script>

<style lang="stylus" scoped>
.user-center-page
  margin 5px 133px
  .el-tabs__item
    width 258px !important
  .croppa-container
    position absolute
    top -52px
    left 143px
    width 96px
    height 96px
    border 1px solid #53526D
    border-radius 50%
    text-align center
    cursor pointer
    &:hover
      background-color #191730
  .user-info-tab
    padding 80px 0px 0px 0px
    border-radius 0 0 6px 6px
    .user-info-data
      width 1031px
      height 586px
      position relative
      display flex
      margin 0 auto
      .user-info-map
        width 648px
        .map-disabled
          position absolute
          z-index 401
          width calc(100% - 383px)
          height calc(100% - 72px)
          background rgba(0, 0, 0, 0)
        .map-center-top
          position absolute
          z-index 401
          width calc(100% - 383px)
          background rgba(56,55,86,0.70)
          height 44px
          line-height 44px
          text-align center
          .title
            font-size 16px
            color #FFFFFF
            letter-spacing: 0
        .map-center-bottom
          position absolute
          z-index 401
          background rgba(56,55,86,0.7)
          height 72px
          bottom 0px
          width calc(100% - 383px)
          text-align center
          color #FFFFFF
          line-height 26px
          font-weight 100
          font-size 14px
          .map-center-bottom-font
            margin-top 10px
            > span
              font-weight bold
              margin-right 15px
              font-family Arial-BoldMT
        .map-container
          position relative
          width 648px
          height 586px
          border-radius 6px 0px 0px 6px
      .user-info-form
        position relative
        right 0px
        z-index 400
        width 383px
        background #191730
        border-radius 0 6px 6px 0
        padding 0px 24px
        height 100%
        .header-slider
          margin-top 60px
          display flex
          justify-content center
          .el-slider
            width 203px
            display inline-block
        .el-form
          margin-top 15px
          &.user-info-form-detail
            .el-form-item
              border-bottom 1px solid rgba(255,255,255,0.10)
              padding-bottom 15px
              .el-form-item__content > span
                margin-left 10px !important
              &:last-child
                border none
          .user-info-edit,.user-info-submit
            position absolute
            bottom 40px
            right 24px
    .footer
      background #272645
      border-radius 0 0 6px 6px
      margin-top 50px
      display flex
      justify-content center
      padding 14px
    .loading-info
      position absolute
      margin 0 auto
      top 300px
      left 47%
      z-index 99
  .user-password-tab
    padding 150px 0px 200px 0px
    border-radius 0 0 6px 6px
    .user-password
      width 335px
      margin 0 auto
      .password-header
        display flex
        justify-content space-between
        margin-bottom 10px
        .line
          width 96px
          height 1px
          border-bottom 1px solid rgba(255,255,255,0.10)
          margin-top 25px
      .password-title
        line-height 16px
        font-size 14px
        color #FFFFFF
        letter-spacing 0
        text-align center
        margin-bottom 40px
      .footer
        display flex
        justify-content center
        margin-top 71px
  .user-message-tab
    border-radius 0 0 6px 6px
    .user-message
      padding-top 12px
      .message-header
        display flex
        justify-content space-between
        padding 0px 30px 0px 30px
        align-items center
        .switch
          .switch-title
            font-size 13px
            color #9A9AC6
            margin-left 20px
        .message-search
          display flex
          .el-select,.el-date-editor
            width 344px
            margin-right 20px
      .message-title
        margin-top 12px
        height 50px
        background-image linear-gradient(-135deg, #2A2A4B 0%, #272645 100%)
        font-size 13px
        color #FFFFFF
        line-height 50px
        padding-left 30px
        button
          padding 10px 20px
          margin-right 20px
      .message-list
        display flex
        background #201f3d
        padding 10px 20px
        justify-content flex-start
        flex-flow wrap
        position relative
        height 623px
        .message-box
          position relative
          width 100%
          height 93px
          max-width 511px
          background-image linear-gradient(135deg, #403F6A 0%, #272645 100%)
          border-radius 6px
          margin 0px 8px 12px 8px
          display flex
          align-items center
          cursor pointer
          &:hover,&.checked
            background-image linear-gradient(135deg, #272645 0%, #403F6A 100%)
            > .message-content > .title
              color #FFFFFF
          &.is-read
            background-image  linear-gradient(135deg, #2A2A4B 0%, #272645 100%)
            > .message-content > .title
              color #9A9AC6
            &:hover
              background-image linear-gradient(135deg, #272645 0%, #403F6A 100%)
          .message-icon
            position absolute
            width 58px
            height 58px
            border-radius 50%
            margin 0 18px 0 20px
            &.alarm
              background rgba(134,101,255,0.23)
              > .message-icon-inner
                background-image linear-gradient(-180deg, #7052EC 1%, #463782 100%)
            &.notice
              background rgba(39,255,206,0.13)
              > .message-icon-inner
                background-image linear-gradient(-180deg, #50E3C2 1%, #138269 100%)
            .message-icon-inner
              width 44px
              height 44px
              border-radius 50%
              text-align center
              position relative
              top 7px
              left 7px
              svg
                margin-top 8px
          .message-content
            margin-left 96px
            width 100%
            height 100%
            .title
              font-family Arial-BoldMT
              font-size 16px
              color #FFFFFF
              overflow hidden
              text-overflow ellipsis
              white-space nowrap
              max-width 405px
              margin-top 23px
            .time
              font-family ArialMT
              font-size 13px
              color #666681
              margin-top 15px
            .mark
              font-family ArialMT
              font-size 13px
              color #666681
              letter-spacing 0
              position: absolute
              right 20px
              bottom 10px
            .checked
              position absolute
              top 10px
              right 14px
      .message-page
        width 100%
        height 47px
        background #282747
        border-radius 0 0 6px 6px
        display flex
        align-items center
        justify-content center
</style>
<style lang="stylus">
.user-center-page
  .el-tabs__item
    width 258px
.croppa-container
  svg.icon-remove
    position absolute
    background #ffffff
    border-radius 50%
    cursor pointer
  canvas
    border-radius 50%
    margin-top 1px
</style>
