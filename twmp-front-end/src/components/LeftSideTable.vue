<template>
  <div class="leftside-table">
    <div class="leftside-table-header">
      <div class="title">
        <span v-for="(item, index) in $route.matched" :key="index">
          {{$t('route.' + item.meta.title)}} <i class="el-icon-arrow-right" v-if="!(index === $route.matched.length - 1)"></i>
        </span>
      </div>
      <div class="search">
        <slot name="search"></slot>
      </div>
    </div>
    <div class="leftside-table-list">
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        height="100%">
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
            v-for="(item, index) in tableFormat" 
            :key="index"
            :render-header="renderHeader">
          </el-table-column>
      </el-table>
    </div>
    <div class="leftside-table-pagination">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="globalConfig.pageLimit"
        layout="prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'leftSideTable',
  data () {
    return {
      total: 1,         // 总页数
      currentPage: 1,   // 当前第几页
      tableData: [],    // 表格数据
      isLoading: true, // 表格数据载入效果控制
      params: {}       // 查询参数
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
    },
    showTableName () {
      return this.tableName
    }
  },

  methods: {
    ...mapActions(['updatePageLimit']),
    // 表格Header过长隐藏并tooltip提示
    renderHeader (h, {column}) {
      return (
        <label style='white-space:nowrap; width: 100%; display:inline-block' title={column.label}>{column.label}</label>
      )
    },
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
    }
  }
}
</script>

<style lang="stylus" scoped>
.leftside-table
  width 320px
  position fixed
  background-color #001528
  height 100%
  border 1px solid #001528
  .leftside-table-header
    color #ffffff
    padding 0px 5px
    .title
      font-size 14px
      height 35px
      line-height 35px
    .search
      margin-bottom 10px
  .leftside-table-list
    height calc(100% - 180px)
    overflow-y auto
  .leftside-table-pagination
    position fixed
    bottom 0px
.el-table__empty-text
  width 100%
</style>
<style lang="stylus">
.leftside-table
  .el-table
    &::before
      background-color transparent
    .el-table__header
      th,tr
        background-color #2b3138
        color #dfdfdf
    th,tr
      background-color #393d44
      color #bfcce6
    &__row
      background-color #393d44
    tr
      td
        border 1px solid transparent
  .el-table--striped
    .el-table__body
      tr.el-table__row--striped
        td
          background-color #2b3138
  .el-table
    td,th.is-leaf
      border none
  .el-table--enable-row-hover
    .el-table__body 
      tr      
        &:hover>td,&.current-row>td
          background-color #393d44
      tr.el-table__row--striped
        &:hover>td
          background-color #2b3138
</style>
