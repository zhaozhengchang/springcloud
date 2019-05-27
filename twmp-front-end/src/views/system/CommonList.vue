<template>
  <div class="table-page user-manage-page">
    <search-box
      ref="searchBox"
      :searchForm="searchForm"
      :searchBoxNum="6">
      <!-- 查询条件1 -->
      <el-form-item slot="search-1">
        <span slot="label" :title="$t('system.search1')">{{$t('system.search1')}}</span>
        <el-input placeholder="请输入内容" v-model="searchForm.input1"></el-input>
      </el-form-item>
      <!-- 查询条件2 -->
      <el-form-item slot="search-2">
        <span slot="label" :title="$t('system.search2')">{{$t('system.search2')}}</span>
        <el-input placeholder="请输入内容" v-model="searchForm.input2"></el-input>
      </el-form-item>
      <!-- 查询条件3 -->
      <el-form-item slot="search-3">
        <span slot="label" :title="$t('system.search3')">{{$t('system.search3')}}</span>
        <el-input placeholder="请输入内容" v-model="searchForm.input3"></el-input>
      </el-form-item>
      <!-- 查询条件4 -->
      <el-form-item slot="search-4">
        <span slot="label" :title="$t('system.search4')">{{$t('system.search4')}}</span>
        <el-input placeholder="请输入内容" v-model="searchForm.input4"></el-input>
      </el-form-item>
      <!-- 查询条件5 -->
      <el-form-item slot="search-5">
        <span slot="label" :title="$t('system.search5')">{{$t('system.search5')}}</span>
        <el-input placeholder="请输入内容" v-model="searchForm.input5"></el-input>
      </el-form-item>
      <!-- 查询条件6 -->
      <el-form-item slot="search-6">
        <el-button type="primary" icon="el-icon-search"></el-button>
      </el-form-item>
    </search-box>
    <!-- 在线用户数据表 -->
    <table-content
      ref="searchTable"
      :tableName="$t('system.userManage.tableName')"
      :url="tableDataUrl"
      :tableFormat="tableFormat"
      @tableDataChange="tableDataChange">
      <!-- 表格顶部按钮 -->
      <template slot="operation-buttons">
          <el-button type="primary" icon="iconfont icon-add" @click="openWindow"></el-button>
      </template>
    </table-content>
  </div>
</template>

<script>
import SearchBox from '@/components/SearchTablePage/SearchBox'
import TableContent from '@/components/SearchTablePage/TableContent'
import locales from '@/locales/system'
const tableFormatDefault = [
  {prop: 'date', label: '日期', minWidth: '15%'},
  {prop: 'name', label: '姓名姓名姓名姓名姓名姓名姓名姓名姓名姓名', minWidth: '15%'},
  {prop: 'province', label: '城市', minWidth: '15%'},
  {prop: 'city', label: '区域', minWidth: '15%'},
  {prop: 'address', label: '地址', minWidth: '25%'},
  {prop: 'zip', label: '邮编', minWidth: '15%'},
]
export default {
  name: 'commonList',
  locales,
  components: {
    SearchBox,
    TableContent
  },
  data: () => {
    return {
      searchForm: {
        input1: '',
        input2: '',
        input3: '',
        input4: '',
        input5: ''
      },
      tableDataUrl: '/user/userList',
      tableData: [],
      tableFormat: tableFormatDefault
    }
  },
  methods: {
    tableDataChange (data) {
      this.tableData = data
    },
    openWindow () {
      window.open('','_blank','location,width=200,heigh=200')
      // videoWindow.document.title = '视频播放'
      // videoWindow.focus()
    }
  },
  mounted () {
    this.$refs.searchTable.gotoPage(this.searchForm)
  }
}
</script>

<style lang="stylus">

</style>
