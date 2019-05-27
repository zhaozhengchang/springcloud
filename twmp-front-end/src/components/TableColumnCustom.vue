<template>
  <!-- 数据表列定制按钮组件 -->
  <el-dropdown
    trigger="click"
    :hide-on-click="false"
    placement="bottom-start" class="table-column-button">
    <el-tooltip class="item" effect="dark" :content="$t('buttons.table.columnCustom')" placement="top">
      <el-button type="primary"><i class="icon iconfont icon-table-columns"></i></el-button>
    </el-tooltip>
    <el-dropdown-menu slot="dropdown">
      <el-checkbox-group v-model="checkboxSelected" class="table-column-custom">
        <el-dropdown-item v-for="item in columnList" :key="item.prop">
          <el-checkbox :label="item.prop">{{item.label}}</el-checkbox>
        </el-dropdown-item>
      </el-checkbox-group>
    </el-dropdown-menu>
  </el-dropdown>
  <!-- 数据表列定制按钮组件 -->
</template>

<script>
export default {
  name: 'tableColumnCustom',
  props: {
    columnList: {
      required: true,
      type: Array
    }
  },
  data: () => {
    return {
      checkboxSelected: []
    }
  },
  watch: {
    checkboxSelected (valArr) {
      let format = this.columnList.filter(i => valArr.indexOf(i.prop) >= 0)
      this.$emit('update-table-format', format)
    }
  },
  mounted () {
    this.columnList.forEach((item) => {
      this.checkboxSelected.push(item.prop)
    })
  }
}
</script>

<style lang="stylus">
.table-column-button
  margin-left 10px
  .el-button
    i
      font-size 16px
</style>
