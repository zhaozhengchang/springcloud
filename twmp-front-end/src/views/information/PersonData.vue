<template>
  <div class="person-data-page" v-ceiecScrollbar>
    <div class="person-header">
      <div class="title">被监控人信息</div>
      <div>
        <el-button v-show="!personFormInputFlag" class="ceiec-primary" style="margin-right: 27px" @click="submit">
          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#FFFFFF">
            <use xlink:href="#iconconfirm"></use>
          </svg>
        </el-button>
        <div class="svg-button" @click="navBack">
          <svg aria-hidden="true" width="23" height="23">
            <use xlink:href="#iconcancelicon"></use>
          </svg>
        </div>
      </div>
    </div>
    <div class="loading" v-show="isLoading">
      <img src="/images/loading.svg" alt="loading">
    </div>
    <el-row>
      <el-col :lg="13">
        <!-- 被监控人基础信息块 -->
        <div class="person-info">
          <div class="form-title">基础信息</div>
          <el-form ref="personForm" 
            label-position="top"
            :model="personForm"
            :disabled="personFormInputFlag"
            :rules="personRules">
            <el-row :gutter="20">
              <el-col :lg="12">
                <el-form-item label="被监控人头像(最多3个)" prop="headerPhoto">
                  <div class="photo-list">
                    <el-upload
                      class="avatar-uploader"
                      :show-file-list="false"
                      :action="webBaseUrl + '/system/fileUpload'"
                      :on-success="handleHeader1"
                      :before-upload="beforeHeader1"
                      :headers="{token: userInfo.token}"
                      :data="userHeader[0].meta">
                      <img v-if="userHeader[0].imageUrl" :src="userHeader[0].imageUrl" class="avatar">
                      <div v-else class="pic-text">
                        <svg aria-hidden="true" width="44" height="44" fill="#666681">
                          <use xlink:href="#iconphotoavatar"></use>
                        </svg>
                        <div>上传头像</div>
                      </div>
                      <div v-if="userHeader[0].imageUrl" class="avatar-remove" @click.stop="removeHeader(0)">
                        <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                          <use xlink:href="#icondeleteicon"></use>
                        </svg>
                      </div>
                    </el-upload>
                    <el-upload
                      class="avatar-uploader"
                      :show-file-list="false"
                      :action="webBaseUrl + '/system/fileUpload'"
                      :on-success="handleHeader2"
                      :before-upload="beforeHeader2"
                      :headers="{token: userInfo.token}"
                      :data="userHeader[1].meta">
                      <img v-if="userHeader[1].imageUrl" :src="userHeader[1].imageUrl" class="avatar">
                      <div v-else class="pic-text">
                        <svg aria-hidden="true" width="44" height="44" fill="#666681">
                          <use xlink:href="#iconphotoavatar"></use>
                        </svg>
                        <div>上传头像</div>
                      </div>
                      <div v-if="userHeader[1].imageUrl" class="avatar-remove" @click.stop="removeHeader(1)">
                        <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                          <use xlink:href="#icondeleteicon"></use>
                        </svg>
                      </div>
                    </el-upload>
                    <el-upload
                      class="avatar-uploader"
                      :show-file-list="false"
                      :action="webBaseUrl + '/system/fileUpload'"
                      :on-success="handleHeader3"
                      :before-upload="beforeHeader3"
                      :headers="{token: userInfo.token}"
                      :data="userHeader[2].meta">
                      <img v-if="userHeader[2].imageUrl" :src="userHeader[2].imageUrl" class="avatar">
                      <div v-else class="pic-text">
                        <svg aria-hidden="true" width="44" height="44" fill="#666681">
                          <use xlink:href="#iconphotoavatar"></use>
                        </svg>
                        <div>上传头像</div>
                      </div>
                      <div v-if="userHeader[2].imageUrl" class="avatar-remove" @click.stop="removeHeader(2)">
                        <svg class="icon" aria-hidden="true" width="28" height="28" fill="#666681">
                          <use xlink:href="#icondeleteicon"></use>
                        </svg>
                      </div>
                    </el-upload>
                  </div>
                </el-form-item>
                <el-form-item label="姓名" prop="personName">
                  <el-input v-model="personForm.personName" :maxlength="100"></el-input>
                </el-form-item>
                <el-form-item label="曾用名">
                  <el-input v-model="personForm.formerName" :maxlength="100"></el-input>
                </el-form-item>
                <el-form-item label="组织机构" required>
                  <popup-department @treeCurrentChange="treeCurrentChange" :popupWidth="379" :popupSize="'small'"></popup-department>
                </el-form-item>
                <el-form-item label="身份证编号" prop="personIdCard">
                  <el-input v-model="personForm.personIdCard"></el-input>
                </el-form-item>
                <el-form-item label="出生日期">
                  <el-date-picker
                    v-model="personForm.birthDate"
                    type="date"
                    :format="globalConfig.defaultDateFormat"
                    :value-format="globalConfig.defaultDateFormat"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="职业">
                  <el-input v-model="personForm.career"></el-input>
                </el-form-item>
              </el-col>
              <el-col :lg="12">
                <el-form-item label="性别" required>
                  <el-radio-group v-model="personForm.gender">
                    <el-radio-button :label="1">男性</el-radio-button>
                    <el-radio-button :label="2">女性</el-radio-button>
                    <el-radio-button :label="3">其它</el-radio-button>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="婚姻状态" required>
                  <el-radio-group v-model="personForm.maritalStatus">
                    <el-radio-button :label="1">未婚</el-radio-button>
                    <el-radio-button :label="2">已婚</el-radio-button>
                    <el-radio-button :label="3">离异</el-radio-button>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="联系电话">
                  <el-input v-model="personForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input v-model="personForm.email"></el-input>
                </el-form-item>
                <el-form-item label="家庭住址">
                  <el-input v-model="personForm.address" type="textarea" 
                    :rows="4" 
                    :autosize="{ minRows: 4, maxRows: 4}" 
                    resize="none" style="height:105px">
                  </el-input>
                </el-form-item>
                <el-form-item label="备注">
                  <el-input v-model="personForm.comment" type="textarea" 
                    :rows="8" 
                    :autosize="{ minRows: 8, maxRows: 8}" 
                    resize="none">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-col>
      <el-col :lg="11">
        <!-- 犯罪记录管理模块 -->
        <div class="criminal-info">
          <div class="criminal-header">
            <div class="title">犯罪记录</div> 
            <div class="add-criminal-button" @click="() => {
              this.criminalInputFlag = false
              this.showCriminalDialog(true)
              }">
              <svg class="icon" aria-hidden="true" width="18" height="18" fill="#D8D8D8">
                  <use xlink:href="#iconaddicon"></use>
              </svg>
              <div class="font">添加犯罪记录</div>
            </div>
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
                  <span class="svg-button">
                    <svg aria-hidden="true" width="20" height="20" @click.stop="detailCriminal(item)">
                      <use xlink:href="#iconlistdetailicon"></use>
                    </svg>
                  </span>
                  <span class="svg-button" v-show="!personFormInputFlag">
                    <svg aria-hidden="true" width="20" height="20" @click.stop="editCriminal(item, idx)">
                      <use xlink:href="#iconediticon"></use>
                    </svg>
                  </span>
                  <span class="svg-button" v-show="!personFormInputFlag">
                    <svg aria-hidden="true" width="20" height="20" @click.stop="delCriminal(item)">
                      <use xlink:href="#icondeleteicon"></use>
                    </svg>
                  </span>
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
    <!-- 犯罪记录添加/编辑/详情Dialog -->
    <new-dialog ref="criminalDialog" title="添加违法记录" @closed-dialog="closedDialog">
      <el-form 
        slot="content"
        ref="criminalForm" 
        label-position="top" 
        :model="criminalForm"
        :disabled="criminalInputFlag"
        :rules="criminalRules">
        <el-row :gutter="20">
          <el-col :lg="16">
            <el-form-item label="罪名" prop="criminalType">
              <el-select v-model="criminalForm.criminalType" placeholder="请选择罪名">
                <el-option
                  v-for="item in criminalTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :lg="8">
            <el-form-item label="判决时间" prop="disposeTime">
              <el-date-picker
                v-model="criminalForm.disposeTime"
                type="date"
                :format="globalConfig.defaultDateFormat"
                :value-format="globalConfig.defaultDateFormat"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :lg="24">
            <el-form-item label="判决机构" prop="lawEnforcementAgency">
              <el-input v-model="criminalForm.lawEnforcementAgency"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :lg="24">
            <el-form-item label="违法行为" prop="criminalAct">
              <el-input v-model="criminalForm.criminalAct"
                :autosize="{ minRows: 5, maxRows: 5}"
                type="textarea" resize="none" :rows="5" style="height:124px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-bottom: 10px">
          <el-col :lg="24">
            <el-form-item label="判决结果" prop="disposeResult">
              <el-input v-model="criminalForm.disposeResult" 
                :autosize="{ minRows: 4, maxRows: 4}"
                type="textarea" resize="none" :rows="4"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" v-show="!criminalInputFlag">
        <el-button class="ceiec-primary confirm" @click.stop="addCriminal">确 定</el-button>
      </div>
    </new-dialog>
  </div>
</template>

<script>
import NewDialog from '@/components/NewDialog'
import PopupDepartment from '@/components/PopupDepartment'
import { personDataApi } from '@/http/api/information'
import { personBasicDetailApi } from '@/http/api/monitor'
import { mapGetters } from 'vuex'
import baseUrl from '@/utils/baseUrl'
export default {
  data () {
    return {
      webBaseUrl: baseUrl,
      isLoading: false,
      personFormInputFlag: false,
      userHeader: [
        {
          imageUrl: '',
          photoId: '',
          meta: {
            fileName: '',
            fileType: 1
          }
        },
        {
          imageUrl: '',
          photoId: '',
          meta: {
            fileName: '',
            fileType: 1
          }
        },
        {
          imageUrl: '',
          photoId: '',
          meta: {
            fileName: '',
            fileType: 1
          }
        }
      ],
      personForm: {
        personId: null,
        personName: '',
        formerName: '',
        departmentId: null,
        personIdCard: null,
        birthDate: '',
        career: '',
        gender: 1,
        maritalStatus: 1,
        phone: '',
        email: '',
        address: '',
        comment: '',
        personUrl: '',
        criminalId: null,
        criminalList: []
      },
      criminalTypes: [
        {label: '危害公共安全罪', value: 1},
        {label: '破坏经济罪', value: 2},
        {label: '侵犯公民人身权利', value: 3},
        {label: '侵犯财产罪', value: 4},
        {label: '妨害社会管理秩序罪', value: 5}
      ],
      criminalForm: {
        index: null,
        criminalId: null,
        criminalType: null,
        criminalTypeName: '',
        criminalAct: '',
        lawEnforcementAgency: '',
        disposeTime: '',
        disposeResult: ''
      },
      criminalRules: {
        criminalType: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        disposeTime: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        lawEnforcementAgency: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        criminalAct: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        disposeResult: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}]
      },
      criminalInputFlag: false,
      deleteCriminalList: [],
      personRules: {
        headerPhoto: [{required: true, message: '请上传活动图片', trigger: 'blur'}],
        personName: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        formerName: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}],
        personIdCard: [{required: true, message: this.$t('validator_not_empty'), trigger: 'blur'}]
      }
    }
  },
  computed: {
    ...mapGetters(['globalConfig','userInfo'])
  },

  components: {
    NewDialog,
    PopupDepartment
  },

  watch: {
    'criminalForm.criminalType' (val) {
      if (val) {
        let opt = this.criminalTypes.find((v) => {
          return v.value === val
        })
        this.criminalForm.criminalTypeName = opt.label
      }
    }
  },

  methods: {
    navBack () {
      this.$router.push({ path: '/information/person' })
    },
    treeCurrentChange (e) {
      this.personForm.departmentId = e.departmentId
    },
    showPersonDetail (flag) {
      this.personFormInputFlag = flag
      this.criminalInputFlag = flag
    },
    handleHeader1 (res, file) {
      this.handleHeaderSuccess(0, res, file)
    },
    handleHeader2 (res, file) {
      this.handleHeaderSuccess(1, res, file)
    },
    handleHeader3 (res, file) {
      this.handleHeaderSuccess(2, res, file)
    },
    handleHeaderSuccess (headerIdx, res, file) {
      if (res.code === 10000) {
        this.userHeader[headerIdx].imageUrl = URL.createObjectURL(file.raw)
        this.userHeader[headerIdx].photoId = res.data
      } else {
        this.headerError()
      }
    },
    headerError () {
      this.$message.error('图片上传失败')
    },
    beforeHeader1 (file) {
      this.beforeHeaderUpload(0, file)
    },
    beforeHeader2 (file) {
      this.beforeHeaderUpload(1, file)
    },
    beforeHeader3 (file) {
      this.beforeHeaderUpload(2, file)
    },
    beforeHeaderUpload (headerIdx, file) {
      const inType = this.globalConfig.pictureTypes.some((v)=>{
        return v === file.type
      })
      const isLt2M = file.size < this.globalConfig.pictureSize
      if (!inType) {
        this.$message.error('上传图片只支持jpg/jpeg/png/gif/bmp格式')
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过2MB')
      }
      this.userHeader[headerIdx].meta.fileName = file.name
      return inType && isLt2M
    },
    removeHeader (headerIdx) {
      this.userHeader[headerIdx].imageUrl = ''
      this.userHeader[headerIdx].photoId = ''
      this.userHeader[headerIdx].meta.fileName = ''
    },
    // handleHeaderRemove (file, fileList) {
    //   if (fileList.length === 2) {
    //     this.$refs.headerPhoto.$el.lastChild.style.display = ""
    //   }
    // },
    // 提交数据
    submit () {
      let photoIds = []
      this.userHeader.forEach((v) => {
        if (v.photoId) photoIds.push(v.photoId) // 获取上传后的头像的Id值
      })
      if (photoIds.length === 0) {
        this.$message.warning('请上传头像')
      }
      this.personForm.personUrl = photoIds.join(',')
      personDataApi(this.personForm).then((res) => {
        if (res.data.code === 10000) {
          
          if (this.$route.params.type == 'add') {
            this.$message.success('添加成功')
            this.clearAllPersonInfo()
          } else {
            this.$message.success('修改成功')
          }
        } else {
          this.$message.error('添加失败:' + res.data.error)
        }
      })
    },
    // 清空被监控人所有信息
    clearAllPersonInfo () {
      this.personForm.personId = null
      this.personForm.personName = ''
      this.personForm.formerName = ''
      this.personForm.departmentId = null
      this.personForm.personIdCard = null
      this.personForm.birthDate = ''
      this.personForm.career = ''
      this.personForm.gender = 1
      this.personForm.maritalStatus = 1
      this.personForm.phone = ''
      this.personForm.email = ''
      this.personForm.address = ''
      this.personForm.comment = ''
      this.personForm.personUrl = ''
      this.personForm.criminalId = null
      this.personForm.criminalList = []
      this.userHeader.forEach((v) => {
        v.imageUrl = ''
        v.photoId = ''
        v.meta.fileName = ''
      })
      // this.$refs.headerPhoto.clearFiles()
    },
    // 清空犯罪记录Dialog
    clearCriminalDialog () {
      this.criminalForm.index = null
      this.criminalForm.criminalId = null
      this.criminalForm.criminalType = null
      this.criminalForm.criminalAct = ''
      this.criminalForm.lawEnforcementAgency = ''
      this.criminalForm.disposeTime = ''
      this.criminalForm.disposeResult = ''
      this.$refs.criminalForm.resetFields()
    },
    closedDialog () {
      this.clearCriminalDialog()
    },
    // 显示/关闭犯罪Dialog
    showCriminalDialog (show) {
      if (show) {
        this.$refs.criminalDialog.showDialog()
      } else {
        this.$refs.criminalDialog.closeDialog()
      }
    },
    // 添加犯罪记录
    addCriminal () {
      this.$refs.criminalForm.validate((valid) => {
        if (valid) {
          if (this.criminalForm.index) {
            this.personForm.criminalList[this.criminalForm.index] = Object.assign({},this.criminalForm)
          } else {
            this.personForm.criminalList.push(Object.assign({},this.criminalForm))
          }
          this.showCriminalDialog(false)
        } else {
          return false
        }
      })
    },
    // 获取被监控人详情信息
    getPersonDetail (personId) {
      this.isLoading = true
      personBasicDetailApi(personId).then((res) => {
        setTimeout(() => {
          this.isLoading = false
        }, 3000)
        if (res.data.code === 10000) {
          let data = res.data.data
          Object.keys(this.personForm).forEach((key) => {
            this.personForm[key] = data[key]
          })
          if (data.personUrl) {
            let photoList = data.personUrl.split(',')
            photoList.forEach((v, i) => {
              this.userHeader[i].photoId = v
              this.userHeader[i].imageUrl = baseUrl + '/downloadFile/false/' + v
            })
          }
        }
      })
    },
    // 犯罪记录详情
    detailCriminal (item) {
      this.showCriminalDialog(true)
      this.criminalInputFlag = true
      this.criminalForm.index = null
      Object.keys(this.criminalForm).forEach((key) => {
        this.criminalForm[key] = item[key]
      })
    },
    // 犯罪记录编辑
    editCriminal (item, idx) {
      this.showCriminalDialog(true)
      this.criminalInputFlag = false
      this.criminalForm.index = idx
      Object.keys(this.criminalForm).forEach((key) => {
        this.criminalForm[key] = item[key]
      })
    },
    // 犯罪记录删除
    delCriminal (item, idx) {
      this.$confirm('确定要删除吗', '提示', {
        type: 'warning'
      }).then(() => {
        this.personForm.criminalList.splice(idx, 1)
        this.deleteCriminalList.push(item.criminalId)
        this.personForm.criminalId = this.deleteCriminalList.join(',')
        personDataApi(this.personForm).then((res) => {
          if (res.data.code === 10000) {
            this.getPersonDetail(this.personForm.personId)
          }
        })
      }).catch(()=>{})
    }
  },

  mounted () {
    this.clearAllPersonInfo()
    let params = this.$route.params
    if (params.type == 'detail') {
      this.showPersonDetail(true)
    } else if (params.type == 'edit') {
      this.showPersonDetail(false)
    }
    let query = this.$route.query
    if (query.id) {
      this.getPersonDetail(query.id)
    }
  }
}
</script>
<style lang="stylus" scoped>
.person-data-page
  padding 0px 30px
  position absolute
  background #201f3d
  border-radius 6px
  width calc(100% - 90px)
  height calc(100% - 30px)
  .person-header
    width 100%
    height 69px
    border-bottom 1px solid #424168
    display flex
    align-items center
    justify-content space-between
    .title
      font-family Arial-BoldMT
      font-size 20px
      color #FFFFFF
      letter-spacing 0
  .person-info
    padding 0px 10px
    float left
    margin-right 35px
    .form-title
      background #7E68FF
      border-radius 4px
      margin 32px 0px 22px 0px
      width 212px
      height 22px
      text-align center
      font-size 13px
      color #FFFFFF
      line-height 22px
    .el-form-item
      width 379px
      .photo-list
        display flex
        justify-content space-between
        margin-bottom 17px
  .criminal-info
    width 665px
    position absolute
    right 0px
    height calc(100% - 30px)
    .criminal-header
      margin-top 32px
      display flex
      align-items center
      justify-content space-between
      .title
        width 212px
        height 22px
        line-height 22px
        background #F94837
        border-radius 4px
        font-size 13px
        color #FFFFFF
        text-align center
      .add-criminal-button
        width 150px
        height 22px
        background #37365D
        border-radius 4px
        font-size 13px
        color #9A9AC6
        display flex
        align-items center
        cursor pointer
        justify-content center
        &:hover
          background #4A4976
          color #FFFFFF
          svg
            fill #FFFFFF
        .font
          margin-left 10px
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
  .footer
    position absolute
    bottom 10px
    clear both
    width 100%
    text-align center
</style>

<style lang="stylus">
.person-data-page
  .avatar-uploader
    position relative
    .el-upload,.el-upload-list__item
      border 1px dashed #424168
      border-radius 4px
      cursor pointer
      position relative
      overflow hidden
      font-size 12px
      color #666681
      width 118px
      height 149px
      text-align center
      background-color unset
      margin 0px 10px 0px 0px
      .el-upload-list__item-status-label
        display none
      .el-progress > .el-progress-circle
        width 110px
        height 110px
    .pic-text
      margin-top 40px
      line-height 20px
    .avatar-remove
      display none
    &:hover
      .avatar-remove
        display block
        position absolute
        top 2px
        right 0px
        &:hover
          svg
            fill #9A9AC6
  .avatar-uploader .el-upload:hover
    border-color #409EFF
  .avatar
    width 118px
    height 149px
    display block
  .new-dialog
    width 545px
    // height 588px
  .el-date-editor.el-input
    width 100%
  .el-select
    width 100%
    .el-input__inner
      height 32px
  .loading
    position absolute
    text-align center
    z-index 99
    top 70px
    left 0
    bottom 0
    right 0
    padding-top 360px
    .ceiec-department > .el-input__inner
      height 32px !important
  .el-form-item__content
    line-height 32px !important
</style>
