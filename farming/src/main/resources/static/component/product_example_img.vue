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
    module.exports={
        name: "ProductExampleImg",

        props: {
            peid: String,
        },
        data() {
           return{
               url:"product-example-img",
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
                   labelWidth: 120,
                   height: 'auth',
                   calcHeight: 400,
                   dialogWidth: 900,
                   menuWidth: 80,
                   menuAlign: 'center',
                   editBtn:false,
                   column: [
                       {
                           prop: 'id',
                           label: 'ID',
                           hide: true,
                           addDisplay: false,
                           editDisplay: false
                       }, {
                           prop: 'path',
                           label: '图片',
                           type: 'upload',
                           action:"/upload",
                           listType: 'picture-img',
                           limit:1,
                           propsHttp: {
                               res: "data",
                               url:"path"
                           },
                           overHidden: true,
                           minWidth: 60,
                           maxlength: 200,
                           span: 24,
                           rules: [{
                               required: true,
                               message: '请输入',
                               trigger: 'blur'
                           }]
                       },
                       {
                           prop: 'createDate',
                           label: '上传时间',
                           type: 'datetime',
                           overHidden: true,
                           minWidth: 60,
                           addDisplay: false,
                           editDisplay: false,
                           maxlength: 200,
                           span: 24,
                           rules: [{
                               required: true,
                               message: '请输入',
                               trigger: 'blur'
                           }]
                       },
                       {
                           prop: 'createUserName',
                           label: '上传人',
                           type: 'datetime',
                           overHidden: true,
                           minWidth: 60,
                           maxlength: 200,
                           addDisplay: false,
                           editDisplay: false,
                           span: 24,
                           rules: [{
                               required: true,
                               message: '请输入',
                               trigger: 'blur'
                           }]
                       },
                   ]
               },
               // 表单数据
               form: {},
               //当前选中
               obj: {},
           }
        },
        methods:{
            /**
             * 新增保存
             * @param row 行数据
             * @param done 完成回调
             * @param loading 加载回调
             */
            handleSave: function (row, done, loading) {
                row.peId=this.peid;
                post("/" + this.url + "/add", row, res => {
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
                put("/" + this.url + "/update", row, res => {
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
                    del("/" + this.url + "/delete-by-id/" + row.id, res => {
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
                let query = Object.assign({
                    peId:this.peid,
                }, this.searchForm);
                get("/" + this.url + "/findList", query, res => {
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
    .box{
        width: 100%;
        height: 100%;
    }
</style>
