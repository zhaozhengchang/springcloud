<template>
  <div class="search-box">
    <el-collapse v-model="defaultOpen" accordion>
      <el-collapse-item name="defaultOpen">
        <template slot="title">
          <span v-for="(item, index) in $route.matched" :key="index">
            <template v-if="item.meta.title !== ''">
              <i class="el-icon-arrow-right" v-if="index !== 0"></i>
              {{$t(item.meta.title)}}
            </template>
          </span>
         </template>
        <el-form :model="searchForm" label-position="right" label-width="7rem">
          <ul>
            <li v-for="n in searchBoxNum" :key="n">
              <slot :name="'search-' + n"></slot>
            </li>
          </ul>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
export default {
  name: 'searchBox',
  data: () => {
    return {
      defaultOpen: 'defaultOpen'
    }
  },
  props: {
    searchForm: {
      type: Object,
      required: true
    },
    searchBoxNum: {
      type: Number,
      required: true
    }
  }
}
</script>

<style lang="stylus">
.search-box
  border 1px solid #bfcbd9
  border-bottom none
  .el-collapse
    border none
    .el-collapse-item__header
      height 36px
      line-height 36px
      background-color #bfcbd9
      color #ffffff
      padding 0px 15px
      font-size 14px
      .el-collapse-item__arrow
        line-height 36px
    .el-collapse-item__wrap
      padding 10px 0px 0px 0px
      .el-collapse-item__content
        padding-bottom 0px
  ul
    list-style none
    display flex
    justify-content space-between
    flex-wrap wrap
    padding 10px 20px 0px 20px
    margin 0px
    li
      list-style none
      margin-right 2rem
      width 20rem
      label
        max-width 7rem
        display inline-block
        float left
</style>
