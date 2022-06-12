<template>
  <div class="box">
    <avue-crud ref="crud"
               v-model="form"
               :data="tableData"
               :table-loading="tableLoading"
               :option="tableOption"
               @on-load="getList"
               @search-change="searchChange"
               @refresh-change="refreshChange"
               @size-change="sizeChange"
               @current-change="currentChange"
               @row-update="handleUpdate"
               @row-save="handleSave"
               @row-del="rowDel">
    </avue-crud>
  </div>
</template>
<script>
module.exports = {
  name: "set_sensor",
  props: {
    id: String,
    listenerId: {type: Number},
  },
  watch: {

  },
  data() {
    return {
      url: "product",
      // 表单数据
      form: {},
      // 搜索条件
      searchForm: {},
      // 表格加载标志
      tableLoading: false,
      // 表格数据
      tableData: [],
      // 表格参数
      tableOption: {
        border: true,
        index: true,
        indexLabel: '序号',
        indexWidth: 50,
        stripe: true,
        align: 'center',
        searchMenuSpan: 6,
        labelWidth: 150,
        height: '300px',
        calcHeight: 400,
        dialogWidth: 900,
        menuWidth: 200,
        menuAlign: 'center',
        viewBtn: true,
        column: [

          {
            prop: 'sensorId',
            label: '传感器',
            type: 'select',
            multiple: false,
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 12,
            dicUrl: '/sensor/findList',
            props: {
            	label: "name",
            	value: "id"
            },
            editDisabled:true,
            rules: [{
                 required: true,
                 message: '请选择',
                 trigger: 'blur'
            }]
          },
          {
            prop: 'higherBand',
            label: '阈值上限',
            type: 'number',
            overHidden: true,
            minWidth: 60,
            maxlength: 100,
            span: 12,
            rules: [{
              required: true,
              message: '请输入',
              trigger: 'blur'
            }]
          },
          {
            prop: 'lowerBand',
            label: '阈值下限',
            type: 'number',
            overHidden: true,
            minWidth: 60,
            maxlength: 100,
            span: 12,
            rules: [{
              required: true,
              message: '请输入',
              trigger: 'blur'
            }
            ]
          }
        ]
      },
      //当前选中
      obj: {},
    }
  },
  methods: {
    /**
     * 新增保存
     * @param row 行数据
     * @param done 完成回调
     * @param loading 加载回调
     */
    handleSave: function (row, done, loading) {
      row.listenerId = this.listenerId;

      postJson("/" + this.url + "/bindSingleSensor",
      {
          listenerId : this.listenerId,
          sensorId : row.sensorId,
          higherBand : row.higherBand,
          lowerBand : row.lowerBand
      }
      , res => {
        this.$message.success('新增成功');
        this.getList();
        done();
      })
      loading();
    },


    /**
     * 修改保存
     * @param row 行数据
     * @param index 行索引
     * @param done 完成回调
     * @param loading 加载回调
     */
    handleUpdate: function (row, index, done, loading) {
      row.listenerId = this.listenerId;
      postJson("/" + this.url + "/updateListenerBand",
      {
         listenerId : this.listenerId,
         sensorId : row.sensorId,
         higherBand : row.higherBand,
         lowerBand : row.lowerBand
      }, res => {
        this.$message.success('修改成功');
        this.getList();
        done();
      })
      loading();
    },

    /**
     * 删除
     * @param row 行数据
     * @param index 行索引
     */
    rowDel: function (row) {
      this.$confirm('是否确认删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        row.listenerId = this.listenerId;
        postJson("/" + this.url + "/unbindSensor/",
        {
            listenerId : this.listenerId,
            sensorId : row.sensorId,
        }
        , res => {
          this.$message.success('删除成功');
          this.getList();
        })
      })
    },

    /**
     * 获取列表数据
     * @param page 分页参数
     * @param params 查询条件
     */
    getList(page, params) {
      this.tableLoading = true;
      get("/" + this.url + "/findAllBindSensor/" + this.listenerId, res => {
        this.tableData = res;
        this.tableLoading = false;
      })
    },

    /**
     * 分页大小改变
     * @param pageSize 分页大小
     */
    sizeChange(pageSize) {
      this.page.pageSize = pageSize;
      this.page.currentPage = 1;
      this.getList();
    },
    /**
     * 当前页改变
     * @param current 当前页
     */
    currentChange(current) {
      this.page.currentPage = current;
      this.getList();
    },
    /**
     * 搜索
     * @param form 查询条件
     * @param done 完成回调
     */
    searchChange(form, done) {
      this.searchForm = form;
      this.page.currentPage = 1;
      this.getList();
      done();
    },
    /**
     * 刷新
     */
    refreshChange() {
      this.getList();
    },
  }
}
</script>

<style scoped>
.box {
  width: 100%;
  height: 100%;
}
</style>
