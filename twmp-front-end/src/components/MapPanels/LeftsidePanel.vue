<template>
  <transition mode="out-in" enter-active-class="animated fadeIn" leave-active-class="animated fadeOut">
    <div class="map-function-panel" v-show="globalConfig.showMonitorPanel">
      <slot name="content"></slot>
    </div>
  </transition>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'LeftsidePanel',
  computed: {
    ...mapGetters(['globalConfig'])
  },
  methods: {
    ...mapActions(['updateShowMonitorPanel']),
    closeShowMonitorPanel() {
      this.updateShowMonitorPanel(false)
    }
  }
}
</script>

<style lang="stylus" scoped>
.map-function-panel
  position absolute
  left 10px
  top 10px
  width 500px
  height calc(100% - 60px)
  padding 3px
  border 1px solid #e4e6e9
  background-color rgba(255,255,255,.8)
  z-index 501
</style>
