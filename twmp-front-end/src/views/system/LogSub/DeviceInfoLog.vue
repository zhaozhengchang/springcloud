<template>
    <div class="log-page">
        <el-row class="query-row" :gutter="20">
            <el-col :span="1">
                <el-button class="log-export">
                    <svg class="icon" aria-hidden="true" width="24" height="24" fill="#9A9AC6">
                        <use xlink:href="#iconexporticon"></use>
                    </svg>
                </el-button>
            </el-col>
            <el-col :span="2"></el-col>
            <el-col :span="5" class="col-log-input">
                <el-input class="user-name-input" v-model="operateName" placeholder="设备编号">
                </el-input>
            </el-col>
            <el-col :span="5" class="col-log-input">
                <el-input class="user-name-input" v-model="operator" placeholder="操作人">
                </el-input>
            </el-col>
            <el-col :span="5" class="col-log-input">
                <el-select v-model="operateType" placeholder="操作类型">
                    <el-option
                    v-for="item in operateTypeList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
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
            <el-col :span="1" class="col-query-btn">
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
              label="设备编号"
              prop="operateName">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              prop="operateType"
              label="操作类型">
            </el-table-column>
            <el-table-column
              align="center"
              header-align="center"
              prop="operator"
              label="操作人">
            </el-table-column>
            <el-table-column
              prop="operateTime"
              label="操作时间">
            </el-table-column>
            <el-table-column
              prop="content"
              label="操作内容">
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
import { logQueryApi, deviceLogExportApi } from '@/http/api/system'

export default {
  components: {
  },
  data () {
    return {
      operateName: "",
      operateType: "",
      operateObjectType: 3,
      operator: "",
      startTime: "",
      endTime: "",
      timeRange : [new Date(this.startTime),new Date(this.endTime)],
      logData: [],
      pageSize: "",
      currentPage: 1,
      operateTypeList: [
          {
              value: 0,
              label: '全部'
          },{
              value: 1,
              label: '新增'
          },{
              value: 2,
              label: '编辑'
          },{
              value: 3,
              label: '删除'
          },{
              value: 4,
              label: '导入'
          },{
              value: 5,
              label: '变更状态'
          }
      ]
    }
  },
  computed: {
  },
  watch: {
    
  },
  methods: {
    query(){
      logQueryApi({
        operateName: this.operateName,
        operateType: this.operateType,
        operateObjectType: this.operateObjectType,
        operator: this.operator,
        startTime: this.startTime,
        endTime: this.endTime,
        pageNo: this.currentPage,
        pageSize: this.pageSize
      }).then((res)=>{
          let data = res.data.data
          this.totalPage = Number.parseInt(data.total);
          this.logData = [];
          if(data.items.length !== 0){
            data.items.forEach(item => {
              this.logData.push(item)
          });   
        }   
      })
    },
    export(){
      deviceLogExportApi({
        operator: this.operator,
        operateType: this.operateType,
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

