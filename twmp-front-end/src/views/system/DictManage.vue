<template>
  <!-- 数据字典管理页 -->
  <div class="dict-page">
    <div class="dict-content">
      <div class="dict-header">
        <el-button @click="addDict" class="ceiec-default dict-add-button">
          <svg class="icon" aria-hidden="true" width="24" height="24" fill="#9A9AC6">
            <use xlink:href="#icontianjiazidian"></use>
          </svg>
        </el-button>
        <el-select v-model="dictType" 
          placeholder="请选择" 
          size="medium"
          @change="dictTypeChange">
          <el-option
            v-for="type in dictTypes"
            :key="type"
            :label="type"
            :value="type">
          </el-option>
        </el-select>
      </div>
      <!-- 数据字典类型键值表格 -->
      <div class="dict-table">
        <el-table
          :data="tableData"
          stripe
          size="medium"
          tooltip-effect="dark"
          highlight-current-row
          :header-cell-style="{'font-size': '13px', 'font-weight': 'normal'}"
          style="width: 100%">
            <template slot="empty">
              <div v-show="showTipIcon" class="tip-icon">
                <div class="empty" v-show="isEmpty">
                  <img src="/images/empty.png" alt="loading">
                  <p>{{$t('table_no_data')}}</p>
                </div>
                <div class="loading" v-show="isLoading">
                  <img src="/images/loading.svg" alt="loading">
                </div>
              </div>
            </template>
            <el-table-column
              :prop="item.prop"
              show-overflow-tooltip
              :label="item.label"
              :min-width="item.minWidth" 
              v-for="(item, index) in tableFormat" 
              :key="index">
              <template slot="header" slot-scope="scope">
                <label style='white-space:nowrap; width: 100%; display:inline-block' :title="scope.column.label">{{scope.column.label}}</label>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              min-width="10%">
              <template slot-scope="scope">
                <span class="svg-button" v-if="scope.row.changeEnable === 1">
                  <!-- 1可以变更,2不可变更 -->
                  <svg aria-hidden="true" width="20" height="20" @click="edit(scope)">
                    <use xlink:href="#iconediticon"></use>
                  </svg>
                </span>
                <span class="svg-button" style="margin-left: 15px">
                  <svg aria-hidden="true" width="20" height="20" @click="remove(scope)">
                    <use xlink:href="#icondeleteicon"></use>
                  </svg>
                </span>
              </template>
            </el-table-column>
        </el-table>
      </div>
      <div class="dict-footer"></div>
      <new-dialog ref="dictDialog" title="添加数据字典类型中名称和值">
        <el-form 
          slot="content"
          ref="dictForm" 
          label-position="top" 
          :model="dictForm"
          :rules="dictRules">
          <div class="one-line">
            <el-form-item label="名称" prop="dictName" style="width:337px">
              <el-input v-model="dictForm.dictName"></el-input>
            </el-form-item>
            <el-form-item label="值" prop="dictValue" style="width:98px;margin-left:15px">
              <el-input v-model="dictForm.dictValue"></el-input>
            </el-form-item>
          </div>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="dictForm.comment" :rows="7"></el-input>
          </el-form-item>
        </el-form>
        <template slot="footer">
          <el-button class="ceiec-primary confirm" @click="submit">确 定</el-button>
        </template>
      </new-dialog>
    </div>
  </div>
</template>

<script>
import {dictTypesApi, dictDataApi, dictDelApi} from '@/http/api/system'
import NewDialog from '@/components/NewDialog'
export default {
  components: {
    NewDialog
  },
  data () {
    return {
      allDicts: [],
      tableData: [],
      dictTypes: [],
      dictType: '',
      tableFormat: [
        {prop: 'dictId', label: 'ID', minWidth: '20%'},
        {prop: 'dictName', label: '名称', minWidth: '20%'},
        {prop: 'dictValue', label: '值', minWidth: '10%'},
        {prop: 'comment', label: '备注', minWidth: '40%'}
      ],
      isLoading: true,
      dictForm: {
        dictId: null,
        dictName: '',
        dictValue: null,
        comment: '',
        dictType: '',
        dictNameCode: ''
      },
      dictRules: {
        dictName: [{required: true, message: '请输入名称', trigger: 'blur'}],
        dictValue: [{required: true, message: '请输入值0~127', trigger: 'blur'}]
      }
    }
  },

  computed: {
    isEmpty () {
      return !this.tableData.length && !this.isLoading
    },
    showTipIcon () {
      return this.isLoading || this.isEmpty
    }
  },

  methods: {
    // 新增数据字典键值
    addDict () {
      this.dictForm.dictId = null
      this.dictForm.dictName = ''
      this.dictForm.dictValue = null
      this.dictForm.comment = ''
      this.dictForm.dictType = this.dictType
      this.$refs.dictDialog.showDialog()
    },
    
    closeDictDialog() {
      this.$refs.dictDialog.closeDialog()
    },

    // 数据字典类型下拉更新数据表数据
    dictTypeChange (val) {
      this.tableData = this.allDicts[val]
    },

    // 打开数据字典编辑框
    edit (item) {
      this.dictForm.dictId = item.row.dictId
      this.dictForm.dictName = item.row.dictName
      this.dictForm.dictValue = item.row.dictValue
      this.dictForm.comment = item.row.comment
      this.dictForm.dictType = item.row.dictType
      this.$refs.dictDialog.showDialog()
    },

    // 提交添加/编辑结果
    submit () {
      let isAdd = this.dictForm.dictId == null ? true : false
      dictDataApi(this.dictForm).then((res) => {
        if (res.data.code === 10000) {
          this.$message({
            message: isAdd ? '添加成功' : '更新成功',
            type: 'success'
          })
          let idx = this.dictTypes.findIndex((v)=>{ return v == this.dictForm.dictType})
          this.getDictTypes(idx)
          this.$refs.dictDialog.closeDialog()
        } else {
          this.$message({
            message: (isAdd ? '添加失败' : '更新失败') + '[' + res.data.data + ']',
            type: 'error'
          })
        }
      })
    },
    // 删除数据字典键值
    remove (item) {
      this.$confirm('确定要删除吗', '提示', {
        type: 'warning'
      }).then(() => {
        dictDelApi(item.row.dictId).then((res) => {
          if (res.data.code === 10000) {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            let idx = this.dictTypes.findIndex((v)=>{ return v == item.row.dictType})
            this.getDictTypes(idx)
          } else {
            this.$message({
              message: '删除失败',
              type: 'error'
            })
          }
        })
      }).catch(()=>{})
      
    },

    // 获取所有数据字典
    getDictTypes (val) {
      dictTypesApi().then((res) => {
        if (res.data.code === 10000) {
          let mapTypes = {}
          let types = []
          res.data.data.forEach((v) => {
            if (mapTypes[v.dictType]) {
              mapTypes[v.dictType].push(v)
            } else {
              mapTypes[v.dictType] = []
              mapTypes[v.dictType].push(v)
            }
            types.push(v.dictType)
          })
          let tempTypes = new Set(types)
          this.dictTypes = [...tempTypes]
          this.dictType = this.dictTypes[val]
          this.allDicts = mapTypes
          this.tableData = this.allDicts[this.dictType]
        }
      })
    }
  },

  mounted () {
    this.getDictTypes(0)
  }
}
</script>

<style lang="stylus" scoped>
.dict-page
  padding 76px 260px
  .dict-content
    background #201F3D
    border-radius 6px
    .dict-header
      padding 17px 30px
      display flex
      justify-content space-between
      border-bottom 1px solid rgba(255,255,255,0.10)
      .dict-add-button
        width 30px
        height 30px
        border-radius 4px
        padding 2px 0px 0px 0px
      .el-select
        width 360px
    .dict-footer
      height 47px
      background #2F2E55
      border-radius 0 0 5px 5px
</style>
<style lang="stylus">
.dict-page
  .new-dialog
    width 510px
    height 410px
    .el-dialog__footer
      padding 20px
</style>



