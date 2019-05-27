<template>
  <div class="table-content">
    <div class="table-content-header">
      <!-- <div class="title" v-show="showTableName"> {{ tableName }} </div> -->
      <div v-show="showButtons" class="table-buttons">
        <slot name="operation-buttons"></slot>
        <table-column-custom :columnList="columnList" v-on:update-table-format="updateTableFormat"></table-column-custom>
      </div>
    </div>
    
    <div class="table-content-list">
      <el-table
        :data="tableData"
        stripe
        highlight-current-row
        style="width: 100%">
          <template slot="empty">
            <div v-show="showTipIcon" class="tip-icon">
              <div class="empty" v-show="isEmpty">
                <img src="/images/empty.png" alt="loading">
                <p>{{$t('table.noData')}}</p>
              </div>
              <div class="loading" v-show="isLoading">
                <img src="/images/loading.svg" alt="loading">
              </div>
            </div>
          </template>
          <el-table-column
            :prop="item.prop"
            show-overflow-tooltip
            :label="item.label"
            :min-width="item.minWidth" 
            v-for="(item, index) in tableFormatHandle" 
            :key="index"
            header-cell-class-name="ceiec-table-header">
            <template slot="header" slot-scope="scope">
              <label style='white-space:nowrap; width: 100%; display:inline-block' :title="scope.column.label">{{scope.column.label}}</label>
            </template>
          </el-table-column>
      </el-table>
    </div>

    <div class="table-content-pagination">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="globalConfig.pageLimit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import TableColumnCustom from '@/components/TableColumnCustom' 
export default {
  name: 'tableContent',
  components: {
    TableColumnCustom
  },
  data () {
    return {
      total: 1,         // 总页数
      currentPage: 1,   // 当前第几页
      tableData: [],    // 表格数据
      isLoading: true, // 表格数据载入效果控制
      params: {},       // 查询参数
      columnList: this.tableFormat,
      tableFormatHandle: this.tableFormat
    }
  },
  props: {
    tableName: {
      type: String
    },
    tableFormat: {
      type: Array,
      required: true
    },
    url: {
      type: String,
      required: true
    },
    pageSize: {
      type: Number,
      default: 9
    },
    layout: {
      type: String,
      default: 'prev, pager, next'
    }
  },

  computed: {
    ...mapGetters(['globalConfig']),
    isEmpty () {
      return !this.tableData.length && !this.isLoading
    },
    showTipIcon () {
      return this.isLoading || this.isEmpty
    },
    showButtons () {
      return this.$slots['operation-buttons']
    }
    // showTableName () {
    //   return this.tableName
    // }
  },

  methods: {
    ...mapActions(['updatePageLimit']),
    // 表格翻页功能 发起新请求
    gotoPage(param, pageIndex) {
      if (pageIndex) {
        this.currentPage = pageIndex
      }
      this.params = param
      this.params.pageNo = this.currentPage
      this.params.pageSize = this.pageSize
      this.isLoading = true
      this.fetch(this.params)
    },
    // 发起请求获取表格所需数据
    fetch(params) {
      this.$http.post(this.url, params)
        .then((response) => {
          this.isLoading = false
          this.total = response.data.length
          this.tableData = response.data
          this.$emit('tableDataChange', this.tableData)
        }).catch(() => {
          this.isLoading = false
      })
    },
    updateLoading(flag) {
      this.isLoading = flag
    },
    handleCurrentChange (pageIndex) {
      if (pageIndex) {
        this.currentPage = pageIndex
        this.params.pageNo = pageIndex
      }
      this.isLoading = true
      this.fetch(this.params)
    },
    handleSizeChange (pageSize) {
      this.updatePageLimit(pageSize)
    },
    updateTableFormat (format) {
      this.tableFormatHandle = format
    }
  }
}
</script>

<style lang="stylus" scoped>
.table-content
  border 1px solid #bfcbd9
  // box-shadow 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04)
  .table-content-header
    .title
      background-color #bfcbd9
      line-height 50px
      margin 0
      color #ffffff
      padding-left 20px
      font-size 14px
    .table-buttons
      padding 0px 15px
      height 36px
      line-height 38px
  .table-content-list
    padding 0 15px
    border-top 1px solid #bfcbd9
    margin-bottom 40px
  .table-content-pagination
    padding 6px 15px 3px 15px
    position fixed
    bottom 0
    border: 1px solid #bfcbd9
    background-color #ffffff
    height 34px
    right 10px
    left 295px
    transition margin-left .28s
    z-index 120
  .tip-icon
    margin-top 30px
    margin-bottom 30px
.hideSidebar
  .table-content-pagination
    left 52px
</style>
