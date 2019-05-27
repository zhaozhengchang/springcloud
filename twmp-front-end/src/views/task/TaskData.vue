<template>
  <div class="task-data-page">
    <div class="task-data-content" v-ceiecScrollbar>
      <!-- 选择被监控人 -->
      <div class="person-block">
        <div class="header">
          <div class="title">选择被监控人</div>
          <div class="search">
            <label></label>
            <el-input size="normal" style="width: 360px" v-model="personInfoSeachName"></el-input>
          </div>
        </div>
        <el-row class="person-info" :gutter="20">
          <el-col :lg="13">
            <el-form ref="personForm" 
              label-position="top"
              v-model="personForm"
              disabled>
              <el-row :gutter="20">
                <el-col :lg="12">
                  <el-form-item label="被监控人头像">
                    <div class="photo-list">
                      <template v-for="(item, idx) in personHeader">
                        <img :src="item" v-if="item" :key="idx"/>
                        <div class="no-photo" v-else :key="idx">
                          <svg aria-hidden="true" width="44" height="44" fill="#666681">
                            <use xlink:href="#iconphotoavatar"></use>
                          </svg>
                          <div class="photo-info">无头像</div>
                        </div>
                      </template>
                    </div>
                  </el-form-item>
                  <el-form-item label="姓名">
                    <el-input v-model="personForm.personName"></el-input>
                  </el-form-item>
                  <el-form-item label="人员编号">
                    <el-input v-model="personForm.formerName"></el-input>
                  </el-form-item>
                  <el-form-item label="身份证编号" prop="personIdCard">
                    <el-input v-model="personForm.personIdCard"></el-input>
                  </el-form-item>
                  <el-form-item label="组织机构">
                    <el-input v-model="personForm.departmentName"></el-input>
                  </el-form-item>
                  <el-form-item label="联系电话">
                    <el-input v-model="personForm.phone"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :lg="12">
                  <el-form-item label="邮箱">
                    <el-input v-model="personForm.email"></el-input>
                  </el-form-item>
                  <el-form-item label="职业">
                    <el-input v-model="personForm.career"></el-input>
                  </el-form-item>
                  <el-row>
                    <el-col :lg="12">
                      <el-form-item label="性别" class="item-two">
                        <el-input v-model="personForm.gender"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :lg="12">
                      <el-form-item label="婚姻状态" class="item-two">
                        <el-input v-model="personForm.maritalStatus"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :lg="12">
                      <el-form-item label="年龄" class="item-two">
                        <el-input v-model="personForm.age"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :lg="12">
                      <el-form-item label="出生日期" class="item-two">
                        <el-input v-model="personForm.birthDate"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-form-item label="曾用名">
                    <el-input v-model="personForm.formerName"></el-input>
                  </el-form-item>
                  <el-form-item label="家庭住址">
                    <el-input v-model="personForm.address"></el-input>
                  </el-form-item>
                  <el-form-item label="备注">
                    <el-input v-model="personForm.comment" type="textarea" 
                      :rows="4" 
                      :autosize="{ minRows: 4, maxRows: 4}" 
                      resize="none">
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-col>
          <el-col :lg="11">
            <!-- 犯罪记录管理模块 -->
            <div class="criminal-info">
              <div class="criminal-header">
                <div class="title">犯罪记录</div> 
              </div>
              <div class="no-data-tip" v-show="personForm.criminalList.length === 0">
                <img src="/images/empty.png" alt="loading">
                <p>{{$t('table_no_data')}}</p>
              </div>
              <div class="criminal-list" v-ceiecScrollbar v-show="personForm.criminalList.length !== 0">
                <div class="criminal-box" v-for="(item, idx) in personForm.criminalList" :key="idx">
                  <div class="title-bar">
                    <div class="title"><span class="name">{{ item.criminalTypeName }}</span>{{ item.disposeTime }}</div>
                    <div class="operation">
                      <el-checkbox v-model="item.checked"></el-checkbox>
                    </div>
                  </div>
                  <div class="content">
                    <div class="line"><label>判决机构</label>{{ item.lawEnforcementAgency}}</div>
                    <div class="line"><label>判决结果</label>{{ item.disposeResult }}</div>
                    <div><label>违法行为</label></div>
                    <div>{{ item.criminalAct }}</div>
                  </div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <!-- 设置监控任务参数 -->
      <div class="task-block">
        <div class="header">
          <div class="title">设置监控任务参数</div>
        </div>
        <el-form ref="taskForm"
          label-position="top"
          v-model="taskForm"
          :rules="taskFormRules">
          <el-row>
            <el-col :lg="6">
              <!-- 监控起止时间 -->
              <el-form-item label="监控起止时间" required prop="taskTime">
                <el-date-picker
                  v-model="taskTimeRange"
                  type="daterange"
                  align="right"
                  unlink-panels
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  :format="globalConfig.defaultDateTimeFormat"
                  :value-format="globalConfig.defaultDateTimeFormat"
                  :picker-options="globalConfig.pickerOptions">
                </el-date-picker>
              </el-form-item>
              <!-- 监控等级 -->
              <el-form-item label="监控等级" prop="taskLevel">
                <el-select v-model="taskForm.taskLevel" placeholder="请选择" size="small">
                  <el-option
                    v-for="item in taskLevelTypes"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <!-- 监控原因 -->
              <el-form-item label="监控原因">
                <el-input type="textarea" 
                  :rows="6" 
                  :autosize="{ minRows: 6, maxRows: 6}" 
                  resize="none" style="height:132px"
                  v-model="taskForm.taskReason">
                </el-input>
              </el-form-item>
              <!-- 监控文书 -->
              <el-form-item label="监控文书(单个文件不超过5M)">
                <el-upload
                  class="upload-document"
                  :action="webBaseUrl + '/system/fileUpload'"
                  :before-upload="documentBeforeUpload"
                  multiple
                  :limit="5"
                  :file-list="documentList">
                  <el-button class="ceiec-primary">上传监控文书</el-button>
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :lg="18">
              <div class="fence-block">
                <div class="fence-list">
                  <div class="fence-add">
                    <el-form-item label="围栏类型">
                      <el-select v-model="taskForm.fenceList[0].fenceTypeId">
                        <el-option label="禁止入内" value="1">禁止入内</el-option>
                        <el-option label="禁止外出" value="2">禁止外出</el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="监控时间段">
                      <el-time-picker
                        is-range
                        arrow-control
                        v-model="taskForm.fenceList[0].timeRange"
                        range-separator="至"
                        start-placeholder="开始时间"
                        end-placeholder="结束时间"
                        placeholder="选择时间范围">
                      </el-time-picker>
                    </el-form-item>
                    <el-button class="ceiec-primary">添加</el-button>
                  </div>
                </div>
                <div class="map-container" id="mapContainer" ref="mapContainer">
                  <ceiec-map mapContainer="mapContainer" ref="fenceMap"></ceiec-map>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <!-- 添加监护成员 -->
      <div class="guardian-block">
        <div class="header">
          <div class="title">添加监护成员<div class="sub-tip">至少添加一位，最多可添加三位</div></div>
        </div>
        <div class="guardian">
          <div class="photo">
            <svg aria-hidden="true" width="44" height="44" fill="#666681">
              <use xlink:href="#iconphotoavatar"></use>
            </svg>
            <div class="photo-info">添加头像</div>
          </div>
          <div class="info">
            <el-form label-position="top">
              <el-form-item label="姓名">
                <el-input></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <el-input></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input></el-input>
              </el-form-item>
              <el-form-item label="关系">
                <el-select size="small"></el-select>
              </el-form-item>
              <el-form-item label="地址">
                <el-input></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div class="operation">
            <span class="svg-button">
              <svg aria-hidden="true" width="32" height="32" fill="#666681">
                <use xlink:href="#icondeleteicon"></use>
              </svg>
            </span>
          </div>
        </div>
      </div>
      <!-- 设置定期汇报 -->
      <div class="report-block">
        <div class="header">
          <div class="title">设置定期汇报</div>
        </div>
        <el-form>
          <el-form-item label="汇报地址">
            <el-input></el-input>
          </el-form-item>
          <el-form-item label="汇报日期范围">
            <el-date-picker
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="globalConfig.pickerOptions"
              :format="globalConfig.defaultDateFormat"
              :value-format="globalConfig.defalutDateFormat">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="汇报时间">
            <el-time-picker
              arrow-control
              placeholder="任意时间点">
            </el-time-picker>
          </el-form-item>
          <el-form-item label="汇报频率">
            <el-input></el-input>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="footer">
      <el-button class="ceiec-default confirm" @click="cancelAndBack">取消</el-button>
      <el-button class="ceiec-primary confirm">提交</el-button>
    </div>
  </div>
</template>

<script>
import { personBasicDetailApi } from '@/http/api/monitor'
import baseUrl from '@/utils/baseUrl'
import { mapGetters } from 'vuex'
import CeiecMap from '@/components/CeiecMap/Index'
export default {
  data () {
    return {
      webBaseUrl: baseUrl,
      personInfoSeachName: '',
      personForm: {
        personId: null,
        personName: '',
        formerName: '',
        departmentId: null,
        personIdCard: null,
        birthDate: '',
        career: '',
        gender: '',
        maritalStatus: '',
        phone: '',
        email: '',
        address: '',
        comment: '',
        personUrl: '',
        criminalId: null,
        criminalList: [{
          criminalTypeName: '测试信息1',
          disposeTime: '2019-04-22 12:01:55',
          lawEnforcementAgency: '测试机构',
          disposeResult: '判决结果测试',
          criminalAct: '违法行为测试',
          checked: false
        },
        {
          criminalTypeName: '测试信息1',
          disposeTime: '2019-04-22 12:01:55',
          lawEnforcementAgency: '测试机构',
          disposeResult: '判决结果测试',
          criminalAct: '违法行为测试',
          checked: false
        },
        {
          criminalTypeName: '测试信息1',
          disposeTime: '2019-04-22 12:01:55',
          lawEnforcementAgency: '测试机构',
          disposeResult: '判决结果测试',
          criminalAct: '违法行为测试',
          checked: false
        },
        {
          criminalTypeName: '测试信息1',
          disposeTime: '2019-04-22 12:01:55',
          lawEnforcementAgency: '测试机构',
          disposeResult: '判决结果测试',
          criminalAct: '违法行为测试',
          checked: false
        },
        {
          criminalTypeName: '测试信息1',
          disposeTime: '2019-04-22 12:01:55',
          lawEnforcementAgency: '测试机构',
          disposeResult: '判决结果测试',
          criminalAct: '违法行为测试',
          checked: false
        }]
      },
      personHeader: ['','',''],
      taskFormRules: {
        taskTime: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        taskLevel: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}]
      },
      taskLevelTypes: [
        {
          label: '1级-非常重要',
          value: 1
        },
        {
          label: '2级-重要',
          value: 2
        },
        {
          label: '3级-普通',
          value: 3
        }
      ],
      taskTimeRange: null,
      documentList: [],
      taskForm: {
        startTime: null,
        endTime: null,
        taskLevel: null,
        taskReason: '',
        documentUrl: '',
        fenceList: [
          {
            fenceTypeId: null,
            timeRange: null
          }
        ]
      }
    }
  },
  
  components: {
    CeiecMap
  },

  computed: {
    ...mapGetters(['globalConfig'])
  },

  methods: {
    // 获取被监控人详情信息
    getPersonDetail (personId) {
      personBasicDetailApi(personId).then((res) => {
        if (res.data.code === 10000) {
          let data = res.data.data
          Object.keys(this.personForm).forEach((key) => {
            this.personForm[key] = data[key]
          })
          if (data.personUrl) {
            let photoList = data.personUrl.split(',')
            photoList.forEach((v, i) => {
              if (v) {
                this.personHeader[i] = baseUrl + '/downloadFile/false/' + v
              }
            })
          }
        }
      })
    },
    cancelAndBack () {
      this.$router.push({ path: '/task/manage' })
    },
    documentBeforeUpload (file) {
      // const inType = this.globalConfig.fileTypes.some((v)=>{
      //   return v === file.type
      // })
      const isLt5M = file.size < this.globalConfig.fileSize
      // if (!inType) {
      //   this.$message.error('上传格式')
      // }
      if (!isLt5M) {
        this.$message.error('上传文件大小不能超过5MB')
      }
      console.dir(file)
      return true
      // return inType && isLt5M
    }
  },
  
}
</script>

<style lang="stylus">
.task-data-page
  padding 0px 30px
  background #201f3d
  border-radius 6px
  position absolute
  width calc(100% - 90px)
  height calc(100% - 30px)
  .task-data-content
    position absolute
    height 100%
    padding-right 30px
    .header
      width 100%
      height 69px
      border-bottom 1px solid #424168
      display flex
      align-items center
      justify-content space-between
      .title
        font-family Arial-BoldMT
        font-size 16px
        color #FFFFFF
        letter-spacing 0
    .person-block
      .person-info
        padding 0px 10px
        margin-top 15px
        .el-form-item
          width 380px
          &.item-two
            width 180px !important
          .photo-list
            display flex
            justify-content space-between
            margin-bottom 17px
            .no-photo
              width 118px
              height 149px
              border 1px dashed #424168
              text-align center
              svg
                margin-top 32px
              .photo-info
                font-size 12px
                color #666681
        .criminal-info
          width 665px
          position absolute
          right 0px
          height calc(100% - 60px)
          margin-top 20px
          .criminal-header > .title
            font-family Arial-BoldMT
            font-size 14px
            color #F94837
          .criminal-list
            position absolute
            width 100%
            height 100%
            margin-top 30px
            .criminal-box
              margin-bottom 30px
              .title-bar
                padding 0px 15px
                background-image linear-gradient(135deg, #2A2949 0%, #252446 100%)
                font-size 14px
                color #9A9AC6
                border-left 2px solid #F94837
                display flex
                justify-content space-between
                height 28px
                align-items center
                &:hover
                  background-image linear-gradient(135deg, #373560 0%, #2A294E 100%)
                  color #FFFFFF
                  svg
                    fill #FFFFFF
                .name
                  margin-right 15px
                .svg-button
                  margin-right 5px
              .content
                padding-top 14px
                font-size 13px
                color #9A9AC6
                .line
                  margin-bottom 10px
                label
                  color #666681
                  margin-right 15px
    .task-block
      margin-top 40px
      .el-form
        padding-left 10px
        margin-top 30px
        .el-form-item
          width 380px
          .el-date-editor,.el-select
            width 100%
            .el-input__inner
              height 32px
        .fence-block
          display flex
          margin-left 30px
          .fence-list
            width 350px
            background rgba(25,23,48,0.98)
            border-radius 6px 0 0 6px
            padding 15px
            .fence-add
              opacity 0.9
              border 1px dashed #424168
              border-radius 4px
              padding 0 15px 15px 15px
            .el-form-item
              width 320px
            .el-button
              width 320px
              margin-top 30px
          .map-container
            position relative
            width 100%
            height 566px
            border-radius 0px 6px 6px 0px
    .guardian-block
      margin-top 40px
      .header
        margin-bottom 42px
        .sub-tip
          font-size 12px
          color #666681
      .guardian
        padding 30px 0px 30px 30px
        display flex
        background #272541
        border-radius 4px
        margin-bottom 20px
        .sub-title
          font-size 12px
          color #666681
        .photo
          width 118px
          height 149px
          border 1px dashed #424168
          text-align center
          flex-shrink 0
          margin-right 40px
          svg
            margin-top 32px
          .photo-info
            font-size 12px
            color #666681
        .info > .el-form
          display flex
          justify-content flex-start
          align-items center
          flex-wrap wrap
          .el-form-item,.el-select
            width 380px
            margin-right 40px
        .operation
          width 90px
          text-align center
          border-left 1px solid rgba(255,255,255,0.10)
          flex-shrink 0
          padding-top 55px
    .report-block
      margin 40px 0px 120px 0px
      .el-form
        display flex
        justify-content space-between
        .el-form-item, .el-date-editor
          width 380px
  .footer
    height 68px
    line-height 68px
    background #272645
    border-radius 0 0 6px 6px
    position absolute
    bottom 0
    left 0
    right 0
    z-index 600
    text-align center
</style>
