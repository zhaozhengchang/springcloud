<template>
  <div class="parameters-page">
    <div class="parameters-header">
      系统参数配置
    </div>
    <!-- 动态生成系统参数配置值 -->
    <div class="parameters-content">
      <div v-show="showTipIcon" class="tip-icon">
        <div class="empty" v-show="isEmpty">
          <img src="/images/empty.png" alt="loading">
          <p>{{$t('table_no_data')}}</p>
        </div>
        <div class="loading" v-show="isLoading">
          <img src="/images/loading.svg" alt="loading">
        </div>
      </div>
      <div class="parameters-list">
        <div class="parameter" v-for="item in parameters" :key="item.parameterId">
          <div class="title">{{ item.parameterName }}</div>
          <div class="parameter-slider">
            <el-slider
              v-model="item.parameterValue"
              :show-tooltip="false"
              :min="item.parameterMinRange"
              :max="item.parameterMaxRange"
              height="26">
            </el-slider>
            <el-input 
              v-model.number="item.parameterValue" 
              class="parameter-input" 
              @keyup.native="updateSlider(item)"
              :min="0"
              :max="item.parameterRange">
            </el-input>
            <div class="unit">{{item.parameterUnit}}</div>
          </div>
          <div class="parameter-range">
            <span class="start">0</span>
            <span class="end">{{item.parameterRange}}</span>
          </div>
        </div>
      </div>
      <div class="paramters-buttons" v-show="parameters.length > 0">
         <el-tooltip class="item" effect="dark" content="恢复默认" placement="top-start">
            <el-button @click="reset" class="ceiec-default">
              <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                <use xlink:href="#iconreset"></use>
              </svg>
            </el-button>
        </el-tooltip>
        <el-button class="ceiec-primary confirm" @click="submit">确认</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { systemParametersApi, systemParametersUpdateApi } from '@/http/api/system'
export default {
  data () {
    return {
      defalutParmeters: [],
      parameters: [],
      isLoading: true
    }
  },
  computed: {
    isEmpty () {
      return !this.parameters.length && !this.isLoading
    },
    showTipIcon () {
      return this.isLoading || this.isEmpty
    }
  },
  methods: {
    // 输入框值更新动态调整slider
    updateSlider(item) {
      item.parameterValue = parseInt(item.parameterValue)
    },
    // 提交更新参数至后端
    postData(parameters) {
      if (parameters.length > 0) {
        let updateParameters = []
        parameters.forEach((v) => {
          updateParameters.push({
            parameterId: v.parameterId,
            parameterValue: v.parameterValue
          })
        })
        systemParametersUpdateApi({parameters: updateParameters}).then((res) => {
          if (res.data.code === 10000) {
            this.$message({
              message: '更新成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '更新失败',
              type: 'error'
            })
          }
        })
      }
    },
    // 更新系统参数
    submit() {
      this.postData(this.parameters)
    },
    // 恢复默认
    reset() {
      this.postData(this.defalutParmeters)
    }
  },
  mounted () {
    systemParametersApi().then((res) => {
      this.isLoading = false
      if(res.data.code === 10000) {
        res.data.data.forEach((v) => {
           this.parameters.push({
            deleted: v.deleted,
            parameterId: v.parameterId,
            parameterName: v.parameterName,
            parameterMinRange: parseInt(v.parameterRange.split(',')[0]),
            parameterMaxRange: parseInt(v.parameterRange.split(',')[1]),
            parameterUnit: v.parameterUnit,
            parameterValue: parseInt(v.parameterValue),
            updateTime: v.updateTime,
            updater: v.updater,
            updaterId: v.updaterId
          })
        })
        this.defalutParmeters = this.parameters
      }
    })
  }
}
</script>

<style lang="stylus" scoped>
.parameters-page
  padding 105px 163px 0px 163px
  .parameters-header
    background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
    border-radius 6px
    width 100%
    height 67px
    font-family Arial-BoldMT
    font-size 20px
    color #FFFFFF
    letter-spacing 0
    text-align center
    line-height 67px
    margin-bottom 40px
  .parameters-content
    background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
    border-radius 6px
    padding 50px
    .tip-icon
      width 100%
      text-align center
      font-size 14px
      color #FFFFFF
    .parameters-list
      display flex
      justify-content space-between
      flex-flow wrap
      .parameter
        position relative
        width 48%
        margin-bottom 36px
        .title
          font-size 14px
          color #9A9AC6
          letter-spacing 0
          line-height 14px
          margin-bottom 18px
        .parameter-slider
          display flex
          justify-content space-between
          .parameter-input
            width 48px
            margin 3px 6px 0px 20px
          .unit
            font-size 14px
            color #666681
            letter-spacing 0
            line-height 36px
            width 70px
            margin-left 5px
        .parameter-range
          font-size 13px
          color #666681
          letter-spacing 0
          text-align center
          line-height 12px
          margin-top 7px
          .start
            position absolute
            left -3px
          .end
            position absolute
            right 116px
    .paramters-buttons
      margin-top 52px
      display flex
      justify-content center
</style>
<style lang="stylus">
.parameters-page
  .el-slider
    border-left 1px solid #313B5D
    border-right 1px solid #313B5D
    width 100%
  .parameter-input
    .el-input__inner
      padding 0px !important
      text-align center
      width 38px
</style>

