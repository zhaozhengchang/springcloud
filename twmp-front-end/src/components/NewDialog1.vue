<template>
  <el-dialog
    :visible.sync="dialogVisible"
    :modal-append-to-body="false"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    custom-class="new-dialog">
    <div class="svg-button newDialog-close">
        <svg aria-hidden="true" width="23" height="23" @click="dialogVisible = false">
            <use xlink:href="#iconcancelicon"></use>
        </svg>
    </div>
    <slot name="content"></slot>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false
    }
  },
  props: {
  },
  methods: {
    showDialog() {
      this.dialogVisible = true
    },

    closeDialog() {
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="stylus">
.new-dialog
.el-dialog
  background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
  box-shadow 0 0 43px 0 rgba(16,15,35,0.59)
  border-radius 6px
  .newDialog-close
    position absolute
    right 20px
    top 20px
    z-index 1000
  .svg-button
    width 23px
    height 23px
</style>
