<template>
  <div class="ceiec-map"></div>
</template>

<script>
import {Ceiec} from "ceiec.maps"
import {mapGetters} from 'vuex'
export default {
  name: 'CeiecMap',
  props: {
    mapContainer: {
        required: true,
        type: String
    }
  },
  data: () => {
    return {
      map: null,
      mapManager: null
      // toolManager: null,
      // mapTool: null,
      // measureTool: null,
      // drawTool: null
    }
  },
  computed: {
    ...mapGetters(['globalConfig'])
  },
  mounted () {
    this.map = new Ceiec.Maps.Map(this.mapContainer, this.globalConfig.ceiecMapOptions)
    this.mapManager = new Ceiec.Maps.ControlManager(this.map)
    // this.mapManager.createControl(Ceiec.Maps.ControlTypes.SCALE).setPosition(Ceiec.Maps.ControlAnchors.BOTTOMRIGHT)
    // this.mapManager.createControl(Ceiec.Maps.ControlTypes.MOUSEPOSITION).setPosition(Ceiec.Maps.ControlAnchors.BOTTOMRIGHT)

    // this.toolManager = new Ceiec.Maps.ToolManager(this.map)
    // this.mapTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.MAP)
    // this.measureTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.MEASURE)
    // this.drawTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.PLOTDRAW)

    this.map.getLeafletMap().on('moveend',() => {
      let mapCenter = {}
      mapCenter.center = this.map.getCenter()
      mapCenter.zoom = this.map.getZoom()
      this.$emit('showMapCenter', mapCenter)
    })
  },
  methods: {
    setDefalutMapCenter(data) {
      this.map.setCenter(new Ceiec.Maps.Point(data.center.x, data.center.y, Ceiec.Maps.ProjectionTypes.EPSG3857))
      this.map.setZoom(data.zoom)
    }
  }
}
</script>
