<template>
    <div class="log-page">
        <el-row class="query-row" :gutter="20" style="width:100%">
            <el-col :span="1">
                <el-button class="log-export">
                    <svg class="icon" aria-hidden="true" width="24" height="24" fill="#9A9AC6">
                        <use xlink:href="#iconexporticon"></use>
                    </svg>
                </el-button>
            </el-col>
            <el-col :span="12"></el-col>
            <el-col :span="5" class="col-log-input">
                <el-input class="user-name-input" v-model="userName" placeholder="用户名">
                </el-input>
            </el-col>
            <el-col :span="5" class="col-log-input">
                <el-date-picker
                  id="paperworkTime"
                  v-model="timeRange"
                  type="datetimerange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  align="right">
                </el-date-picker>
            </el-col>
            <el-col :span="1" style="text-align: right" class="col-query-btn">
                <el-button class="ceiec-query" @click="query">
                  <svg class="icon" aria-hidden="true" width="28" height="28" fill="#ffffff">
                      <use xlink:href="#iconsearchicon"></use>
                  </svg>
                </el-button>
            </el-col>
        </el-row>
        <el-row class="table-row">
          <el-table
            :data="loginLogData"
            style="width: 100%"
            stripe
            size="medium">
            <el-table-column
              label="用户名"
              prop="userName">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              prop="loginTime"
              label="登录时间">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              prop="logoutTime"
              label="退出时间">
            </el-table-column>
            <el-table-column
              prop="roleName"
              label="角色名">
            </el-table-column>
            <el-table-column
              prop="departmentName"
              label="所属组织机构">
            </el-table-column>
          </el-table>
        </el-row>
        <el-row class="pagination-row">
          <el-pagination
            @current-change="query"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            :page-sizes="[20, 40, 80]"
            layout="prev, pager, next, total, sizes"
            :total="totalPage">
          </el-pagination>
        </el-row>
    </div>
</template>


<script>
import { mapGetters } from 'vuex'
import { loginLogQueryApi, loginLogExportApi } from '@/http/api/system'


export default {
  components: {
  },
  data () {
    return {
      userName:"",
      startTime:"",
      endTime:"",
      timeRange : [new Date(this.startTime),new Date(this.endTime)],
      loginLogData:[],
      pageSize: "",
      currentPage: 1
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  watch: {
    
  },
  methods: {
    query(){
      loginLogQueryApi({
        userName:this.userName,
        startTime:this.startTime,
        endTime:this.endTime,
        pageNo:this.currentPage,
        pageSize:this.pageSize
      }).then((res)=>{
          let data = res.data.data
          this.totalPage = Number.parseInt(data.total);
          this.loginLogData = [];
          if(data.items.length !== 0){
            data.items.forEach(item => {
              this.loginLogData.push(item)
          });   
        }   
      })
    },
    export(){
      loginLogExportApi({
        userName: this.userName,
        startTime: this.startTime,
        endTime: this.endTime
      })
    }
  },
  mounted() {
    this.query()
  }
}
</script>
<style lang="stylus">
</style>
<style lang="stylus" scoped>
</style>

