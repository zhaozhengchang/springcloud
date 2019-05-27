<template>
  <el-dialog
    :visible.sync="dialogVisible"
    :modal-append-to-body="false"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    custom-class="new-dialog"
    @closed="closedDialog">
    <template slot="title">
      <div class="title">{{title}}</div>
      <div class="svg-button">
        <svg aria-hidden="true" width="23" height="23" @click="dialogVisible = false">
          <use xlink:href="#iconcancelicon"></use>
        </svg>
      </div>
    </template>
    <slot name="content"></slot>
    <span slot="footer" class="dialog-footer">
      <slot name="footer"></slot>
    </span>
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
    title: {
      type: String,
      required: true
    }
  },
  methods: {
    showDialog () {
      this.dialogVisible = true
    },

    closeDialog () {
      this.dialogVisible = false
    },

    closedDialog () {
      this.$emit('closed-dialog')
    }
  }
}
</script>

<style lang="stylus">
.new-dialog
  padding 0px 30px
  background-image linear-gradient(-135deg, #222139 0%, #252442 100%)
  box-shadow 0 0 43px 0 rgba(16,15,35,0.59)
  border-radius 6px
  background #23223c
  .el-dialog__header
    padding 20px 0px 14px 0px
    border-bottom 1px solid rgba(255,255,255,0.10)
    display flex
    justify-content space-between
    .title
      font-family Arial-BoldMT
      font-size 14px
      color #9A9AC6
      letter-spacing 0
  .el-dialog__body
    padding 0
    .el-form
      .one-line
        display flex
  .el-dialog__footer
    text-align center
</style>
