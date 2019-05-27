<template>
  <div class="menu-wrapper" v-if="item.show === 1">
    <router-link :to="resolvePath(item)">
      <el-menu-item :index="resolvePath(item)" class="submenu-title-noDropdown">
        <!-- <i v-if="item.icon" :class="['icon iconfont', item.icon]"></i> -->
        <span v-if="item.title" slot="title" :title="$t(item.title)">{{$t(item.title)}}</span>
      </el-menu-item>
    </router-link>
      
    <!-- <el-submenu v-else :index="item.name||item.path">
      <template slot="title">
        <i v-if="item.meta&&item.icon" :class="['icon iconfont', item.icon]"></i>
        <span v-if="item.meta&&item.title" slot="title" :title="$t(item.title)">{{$t(item.title)}}</span>
      </template>

      <template v-for="child in item.children">
        <sidebar-item class="nest-menu"
          v-if="child.children&&child.children.length>0"
          :item="child"
          :key="child.path"
          :base-path="resolvePath(child)">
        </sidebar-item>

        <router-link v-else :to="resolvePath(child)" :key="child.path">
          <el-menu-item :index="resolvePath(child)">
            <i v-if="child.meta&&child.icon" :class="['icon iconfont', child.icon]"></i>
            <span v-if="child.meta&&child.title" slot="title" :title="$t(child.title)">{{$t(child.title)}}</span>
          </el-menu-item>
        </router-link>
      </template>
    </el-submenu> -->
  </div>
</template>

<script>
//import path from 'path'

export default {
  name: 'SidebarItem',
  props: {
    // route object
    item: {
      type: Object,
      required: true
    },
    basePath: {
      type: String,
      default: ''
    }
  },

  methods: {
    resolvePath(item) {
      let path = item.path
      // if (item.children && item.children.length > 0) {
      //   path = item.children[0].path
      // }
      return path
    }
  }
}
</script>

<style lang="stylus">
.menu-wrapper
  li 
    span
      display inline-block
      width 250px
      text-overflow ellipsis
      overflow hidden
      white-space nowrap
      padding-left 37px
    &.is-active > span
      border-left 4px solid #50e3c2
      height 38px
      line-height 38px
      padding-left 33px
    &:hover
      color #9A9AC6 !important
</style>
<style lang="stylus">
.menu-wrapper
  height 58px
  .el-menu-item,
  .el-submenu__title
    height 100%
    padding-left 0px !important
    &.is-active
      background-color #201F3D !important
</style>

