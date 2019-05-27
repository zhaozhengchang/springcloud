<template>
    <!-- 地图工具面板-功能按钮组 -->
    <div class="map-toolbar">
        <ul v-show="showMapToolBar">
            <el-tooltip class="item" effect="dark" content="漫游" placement="bottom">
                <li @click="pan">
                    <i class="iconfont icon-Roam"></i>
                </li>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="放大" placement="bottom">
                <li @click="zoomIn">
                    <i class="iconfont icon-enlarge"></i>
                </li>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="缩小" placement="bottom">
                <li @click="zoomOut">
                    <i class="iconfont icon-narrow"></i>
                </li>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="距离测量" placement="bottom">
                <li @click="measureLength">
                    <i class="iconfont icon-Distancemeasurement"></i>
                </li>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="面积测量" placement="bottom">
                <li @click="measureArea">
                    <i class="iconfont icon-Areameasurement"></i>
                </li>
            </el-tooltip>
            <el-dropdown
                    placement="bottom"
                    trigger="hover"
                    style="float:left"
                    @command="drawFuncs">
                <li>
            <span class="el-dropdown-link">
              <i class="iconfont icon-Paintbrush"></i>
            </span>
                </li>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="drawPoint">
                        <i class="iconfont icon-Tracingpoint" style="font-size: 20px; margin-right: 15px"></i>绘点
                    </el-dropdown-item>
                    <el-dropdown-item command="drawPolyline">
                        <i class="iconfont icon-line" style="font-size: 18px; margin-right: 10px"></i>绘线
                    </el-dropdown-item>
                    <el-dropdown-item command="drawCircle">
                        <i class="iconfont icon-circular" style="font-size: 18px; margin-right: 10px"></i>绘圆形
                    </el-dropdown-item>
                    <el-dropdown-item command="drawRectangle">
                        <i class="iconfont icon-Rectangle" style="font-size: 16px; margin-right: 10px"></i>绘矩形
                    </el-dropdown-item>
                    <el-dropdown-item command="drawPolygon">
                        <i class="iconfont icon-Polygon" style="font-size: 18px; margin-right: 10px"></i>绘多边形
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <el-tooltip class="item" effect="dark" content="清除所有" placement="bottom">
                <li @click="clearAll">
                    <i class="iconfont icon-Eliminate"></i>
                </li>
            </el-tooltip>
            <transition mode="out-in" enter-active-class="animated fadeIn" leave-active-class="animated fadeOut">
                <el-autocomplete
                        size="small"
                        class="maptoolbar-input"
                        placeholder="请输入内容"
                        v-show="showSearchInput"
                        :fetch-suggestions="mapToolBarSearch">
                </el-autocomplete>
            </transition>
            <el-tooltip class="item" effect="dark" content="地图查询" placement="bottom">
                <li @click="showSearchInput = !showSearchInput">
                    <i class="iconfont icon-search"></i>
                </li>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="隐藏" placement="bottom">
                <li @click="showMapToolBar = !showMapToolBar">
                    <i class="el-icon-d-arrow-right"></i>
                </li>
            </el-tooltip>
        </ul>
        <ul v-show="!showMapToolBar">
            <el-tooltip class="item" effect="dark" content="展开" placement="bottom">
                <li @click="showMapToolBar = !showMapToolBar">
                    <i class="el-icon-d-arrow-left"></i>
                </li>
            </el-tooltip>
        </ul>
    </div>
</template>

<script>
    import {Ceiec} from "ceiec.maps"
    import {mapGetters} from "vuex"

    export default {
        name: "MapToolbar",
        props: {
            mapContainer: {
                required: true,
                type: String
            }
        },
        data: () => {
            return {
                showMapToolBar: false,
                showSearchInput: false,
                searchInputs: [
                    {value: "1000"},
                    {value: "2000"},
                    {value: "3000"},
                    {value: "4000"},
                    {value: "5000"}
                ],
                map: null,
                mapTool: null,
                measureTool: null,
                drawTool: null,
                toolManager: null
            }
        },
        computed: {
            ...mapGetters(["globalConfig"])
        },
        methods: {
            // 地图漫游
            pan() {
                this.mapTool.pan()
            },
            // 地图放大
            zoomIn() {
                this.mapTool.zoomIn()
            },
            // 地图缩小
            zoomOut() {
                this.mapTool.zoomOut()
            },
            // 地图距离测量
            measureLength() {
                this.measureTool.measureLength(true)
            },
            // 地图面积测量
            measureArea() {
                this.measureTool.measureArea(true)
            },
            // 地图绘点
            drawPoint() {
                this.drawTool.plotAction(Ceiec.Maps.GeometryTypes.POINT)
            },
            // 地图绘线
            drawPolyline() {
                this.drawTool.plotAction(Ceiec.Maps.GeometryTypes.POLYLINE)
            },
            // 地图绘圆形
            drawCircle() {
                this.drawTool.plotAction(Ceiec.Maps.GeometryTypes.CIRCLE)
            },
            // 地图绘矩形
            drawRectangle() {
                this.drawTool.plotAction(Ceiec.Maps.GeometryTypes.RECTANGLE)
            },
            // 地图绘多边形
            drawPolygon() {
                this.drawTool.plotAction(Ceiec.Maps.GeometryTypes.POLYGON)
            },
            // 地图清除所有
            clearAll() {
                this.measureTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.MEASURE)
                this.measureTool.clearAllMeasurement()
                let plotLayer = this.map.getLayerManager().getLayersByType(Ceiec.Maps.LayerTypes.PLOT)[0]
                plotLayer.clearAll()
            },
            // 绘图调用方法
            drawFuncs(command) {
                this.drawTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.PLOTDRAW)
                switch (command) {
                    case "drawPoint":
                        this.drawPoint()
                        break
                    case "drawPolyline":
                        this.drawPolyline()
                        break
                    case "drawCircle":
                        this.drawCircle()
                        break
                    case "drawRectangle":
                        this.drawRectangle()
                        break
                    case "drawPolygon":
                        this.drawPolygon()
                        break
                }
            },
            // 地图搜索方法查询
            mapToolBarSearch(queryString, cb) {
                cb(this.searchInputs)
            }
        },
        mounted() {
            this.map = new Ceiec.Maps.Map(
                this.mapContainer,
                this.globalConfig.ceiecMapOptions
            )
            const mapManager = new Ceiec.Maps.ControlManager(this.map)
            mapManager.createControl(Ceiec.Maps.ControlTypes.SCALE).setPosition(Ceiec.Maps.ControlAnchors.BOTTOMRIGHT)
            mapManager.createControl(Ceiec.Maps.ControlTypes.MOUSEPOSITION).setPosition(Ceiec.Maps.ControlAnchors.BOTTOMRIGHT)
            this.toolManager = new Ceiec.Maps.ToolManager(this.map)
            this.mapTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.MAP)
            this.measureTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.MEASURE)
            this.drawTool = this.toolManager.getTool(Ceiec.Maps.ToolTypes.PLOTDRAW)
        }
    }
</script>

<style lang="stylus" scoped>
    .map-toolbar
        position absolute
        top 20px
        right 10px
        padding 3px
        border 1px solid #e4e6e9
        border-radius 5px
        height 35px
        background-color rgba(255, 255, 255, 0.6)
        z-index 500
        box-shadow 0 0 5px 0 rgba(0, 0, 0, 0.6)
        ul
            margin 0px
            padding 0px
            li
                float left
                display inline
                width 36px
                height 35px
                line-height 35px
                text-align center
                list-style none
                border-right 1px solid #e4e6e9
                cursor pointer
                &:first-child
                    border-left 1px solid #e4e6e9
                i
                    font-size 18px
                    color #397ABA
                &:hover
                    background #397aba
                    border-radius 2px
                    i
                        color #ffffff
</style>
<style lang="stylus">
    .maptoolbar-input
        position absolute
        right 76px
        top 4px
        .el-input--medium
            .el-input__inner
                height 45px
                line-height 45px
</style>

