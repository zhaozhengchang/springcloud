<template>
    <el-upload
        v-bind:class="{ 'upload-nofile':!fileUrl, 'upload-oneFile':!!fileUrl}"
        drag
        action="/ef/system/fileUpload"
        multiple
        :limit="1"
        :on-success="success"
        list-type="">
        <svg class="icon" aria-hidden="true" width="38" height="38" fill="#666681" style="margin-top:41px">
            <use xlink:href="#iconaddicon1"></use>
        </svg>
        <div class="el-upload__text">Allow PDF / Word</div>
    </el-upload>
</template>

<script>
export default {
  data() {
    return {
        fileId:""
    }
  },
  props: {
    
  },
  methods: {
    success(res){
        this.fileId = res.fileId
    }
  }
}
</script>

<style lang="stylus">

.upload-nofile
  .el-upload-dragger
    width 335px
    height 132px
    border 1px dashed #424168
    border-radius 4px
    background transparent
    use
      xlink:href "#iconaddicon1"
  .el-upload__text
    margin-top 12px
    font-family ArialMT
    font-size 12px
    color #666681
    letter-spacing 0
    text-align center
.upload-oneFile
  .el-upload-dragger
    width 335px
    height 132px
    border 1px dashed #424168
    border-radius 4px
    background transparent
    use
      xlink:href "#iconwenshuicon"
  .el-upload__text
    margin-top 12px
    font-family ArialMT
    font-size 12px
    color #666681
    letter-spacing 0
    text-align center
.el-upload:focus
    border-color #424168
</style>
