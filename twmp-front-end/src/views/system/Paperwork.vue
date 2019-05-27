<template>
    <div class="paperwork-page">
        <!-- 角度管理角色列表界面 -->
        <div>
          <!-- 搜索条件部分 -->
          <div id="paperwork-condition">
              <el-row type="flex" justify="center">
                  <el-col :md="24" :lg="24">
                      <span class="label-title">文书管理</span>
                  </el-col>
              </el-row>
              <el-row style="margin-top:22px;width:100%">
                  <el-col :span="7" class="col-paperwork-query">
                      <el-input class="paperwork-query-input" v-model="paperworkName" placeholder="文书名称"></el-input>
                  </el-col>
                  <el-col :span="7" class="col-paperwork-query">
                      <el-input class="paperwork-query-input" v-model="creator" placeholder="上传人员"></el-input>
                  </el-col>
                  <el-col :span="7" class="col-paperwork-query">
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
                  </el-col>
                  <el-col :md="24" :lg="1" class="col-paperwork-querybtn">
                      <el-button class="ceiec-query" @click="query">
                          <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                              <use xlink:href="#iconsearchicon"></use>
                          </svg>
                      </el-button>
                  </el-col>
                  <el-col :md="24" :lg="1">
                    <el-button class="ceiec-primary" @click="add">
                        <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                            <use xlink:href="#iconaddrolebutton1"></use>
                        </svg>
                    </el-button>
                  </el-col>
              </el-row>
          </div>
          <!-- 结果列表部分 -->
          <div class="paperwork-list">
              <el-row :gutter="20" v-for="(items,index) in paperworkDataSplice()" :key="index">
                  <el-col :lg="12" v-for="(item, index) in items.data" :key="index">
                      <div class="paperwork-info">
                        <el-row>
                          <el-col :span="2">
                            <svg class="icon" aria-hidden="true" width="50" height="50" fill="#474670">
                              <use xlink:href="#iconwenshuicon"></use>
                            </svg>
                          </el-col>
                          <el-col :span="18">
                            <el-row class="paperwork-name">{{item.paperworkName}}</el-row>
                            <el-row class="paperwork-creator">
                              <span>{{item.creator}}</span>
                              <span style="margin-left:15px">{{item.createTime}}</span>
                            </el-row>
                          </el-col>
                          <el-col :span="4" class="btn-area">
                            <div class="svg-button"  @click="download(item.paperworkId)">
                              <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                                <use xlink:href="#icondownloadicon"></use>
                              </svg>
                            </div>
                            <div class="svg-button"  @click="deletePaperwork(item.paperworkId)">
                                <svg class="icon" aria-hidden="true" width="24" height="24" fill="#666681">
                                    <use xlink:href="#icondeleteicon"></use>
                                </svg>
                            </div>
                          </el-col>
                        </el-row>
                      </div>
                  </el-col>
              </el-row>
          </div>  
          <!-- 结果分页 -->
          <div id="paperwork-pagination" v-show="paperworkData.length > 0">
              <el-row>
                  <el-pagination
                  @current-change="query"
                  :current-page.sync="currentPage"
                  :page-size="pageSize"
                  layout="prev, pager, next, total"
                  :total="totalPage">
                  </el-pagination>
              </el-row>
          </div>
        </div>
        <new-dialog ref="addPaperworkDialog" id="addPaperworkDialog">      
          <template slot="content">
            <el-row :gutter="36" class="user-info-row">
              <el-col :span="11" class="addPaperwork-form-col" style="padding-left:30px">
                <el-form :rule="rules" :label-position="top" id="paperworkAddForm">
                  <el-form-item label="Nombre del instrumento" id="paperworkName" class="is-required">
                    <el-input v-model="addForm.paperworkName"></el-input>
                  </el-form-item>
                  <el-form-item label="Cargado de papeles" class="is-required">
                    <NewUpload>

                    </NewUpload>
                  </el-form-item>
                  <el-form-item label="Comentar">
                    <el-input
                      type="textarea"
                      :rows="2"
                      placeholder="请输入内容"
                      v-model="addForm.comment"
                      resize="none">
                    </el-input>
                  </el-form-item>
                </el-form>
              </el-col>
              <el-col :span="12" class="edit-tree-col">
                <department-list @treeCurrentChange="treeCurrentChange"></department-list>
              </el-col>
            </el-row>
            <el-row class="paperwork-button-row">
              <el-button class="ceiec-primary confirm" @click="submit">确 定</el-button>
            </el-row>
          </template>
        </new-dialog>
    </div>
</template>


<script>
import { mapGetters } from 'vuex'
import { paperworkListApi, paperworkDataApi, fileDownloadApi, paperworkDeleteApi } from '@/http/api/system'
import NewDialog from '@/components/NewDialog1'
import DepartmentList from '@/components/DepartmentList'
import NewUpload from '@/components/NewUpload'


export default {
  components: {
    NewDialog,
    DepartmentList,
    NewUpload
  },
  data () {
    return {
      paperworkName:"",
      creator:"",
      startTime: null,
      endTime: null,
      currentPage : 1,
      pageSize : 9,
      totalPage : 0,
      timeRange : '',
      paperworkData : [],
      addForm:{
        paperworkName:"",
        fileUrl:"",
        departmentId:"",
        comment:""
      }
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  watch: {
    
  },
  methods: {
    query(){
      paperworkListApi({
          paperworkName:this.paperworkName,
          creator:this.creator,
          startTime:this.timeRange[0],
          endTime: this.timeRange[1],
          pageNo:this.currentPage,
          pageSize:this.pageSize
        }).then((res)=>{
          let data = res.data.data
          this.totalPage = Number.parseInt(data.total);
          this.paperworkData = [];
          data.items.forEach(item => {
              this.paperworkData.push(item)
          });         
        })
    },
    add(){
      this.$refs.addPaperworkDialog.showDialog()
    },
    download(paperworkId){
      fileDownloadApi('true/' + paperworkId)
    },
    deletePaperwork(roleId){
      this.$confirm('确定要删除吗', '提示',{type: 'warning'}).then(()=>{
          paperworkDeleteApi(roleId).then((res)=>{
            if (res.data.code === 10000){
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.query()
            } else {
              this.$message({
                message: '删除失败',
                type: 'error'
              })
            }
          })
      })
    },
    paperworkDataSplice(){
        let result = [];
        for(let i=0,len=this.paperworkData.length;i<len;i+=2){
            result.push({
                data:this.paperworkData.slice(i,i+2)
                });
         }
         return result
    },
    treeCurrentChange(e){
      this.addForm.departmentId = e.departmentId
    },
    submit(){
      paperworkDataApi(this.addForm).then((res) => {
        if (res.data.code === 10000){
          this.$message({
            message: '新增成功',
            type: 'success'
          })
          this.query()
        } else {
          this.$message({
            message: '添加失败',
            type: 'error'
          })
        }
      })
    }
  },
  mounted() {
    this.query()
  }
}
</script>
<style lang="stylus">
.paperwork-query-input
  .el-input__inner
    width 344px  
    height 40px
#paperwork-condition
  .el-date-editor--datetimerange.el-input__inner
    width 344px
  .ceiec-query
    margin-left 20px
  .ceiec-primary
    margin-left 40px
    width 50px
    height 40px

#addPaperworkDialog
  .el-dialog__header
    display none
  .new-dialog
    width 822px
    height 551px
  .paperwork-button-row
      margin-top 40px
      height 70px
      width 762px
      margin-left 8px
      display flex
      align-items center
      justify-content center
      border-top 1px solid rgba(255,255,255,0.10)

#paperworkAddForm
  input 
    width 335px
  textarea
    width 335px
    height 132px

#paperworkName
  .el-form-item__label
    margin-top 0

#paperworkUpload
  .el-upload-dragger
    width 335px
    height 132px
    border 1px dashed #424168
    border-radius 4px
    background transparent
  .el-upload__text
    margin-top 12px
    font-family ArialMT
    font-size 12px
    color #666681
    letter-spacing 0
    text-align center
</style>

<style lang="stylus" scoped>
.col-paperwork-query
  width 27.3%
  margin-left 20px
.col-paperwork-querybtn
  width 5.1%
.addPaperwork-form-col
  width 46.88%
.paperwork-page
  position absolute 
  width calc(100% - 30px)
  height calc(100% - 30px)
  text-align center
  display flex
  justify-content center
  align-items center

#paperwork-condition
  background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
  border-radius: 6px;
  width 1261px
  height 86px
  padding 25px 10px

.paperwork-list
  width 1281px
  height 495px
  .el-row
    margin-top 20px

.paperwork-info
  background-image: linear-gradient(-135deg, #222139 0%, #252442 100%);
  border-radius: 6px;
  padding 20px 0 30px 20px
  height 40px
  &:hover
    background-image: linear-gradient(135deg, #3C3B5A 0%, #242340 100%);
  .el-row
    margin-top 0

.split-line
  border-bottom 1px solid rgba(255,255,255,0.10);
  height 20px

.label-title
  font-family Arial-BoldMT
  font-size 20px
  color #FFFFFF
  letter-spacing 0
  line-height 22px

.paperwork-name
  color #ffffff
  font-family ArialMT
  font-size 15px
  letter-spacing 0
  text-align left 
  margin-left 20px

.paperwork-creator
  color #666681
  font-family ArialMT
  font-size 13px
  letter-spacing 0
  margin-top 13px !important
  text-align left 
  margin-left 20px

.btn-area
  border-left 1px solid rgba(255,255,255,0.10)
  height 50px
  padding 0 20px
  display flex 
  align-items center
  justify-content space-between

</style>
