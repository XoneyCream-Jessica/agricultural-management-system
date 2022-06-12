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
        name: "Nutrition",

        props: {
            peid: String,
        },
        watch: {

        },
        data() {
            return {
                url: "nutrition",
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
                    viewBtn:true,
                    column: [
                        {
                            prop: 'id',
                            label: 'ID',
                            hide: true,
                            viewDisplay: false,
                            addDisplay: false,
                            editDisplay: false
                        },
                        {
                            prop: 'protein',
                            label: '蛋白质含量(g/100g)',
                            type: 'number',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'carbohydrate',
                            label: '碳水化合物',
                            type: 'checkbox',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            dicUrl: '/sys-dict-item/findByDictCode/carbohydrate',
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请选择',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'minerals',
                            label: '矿物质',
                            type: 'select',
                            multiple:true,
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            dicUrl: '/sys-dict-item/findByDictCode/minerals',
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请选择',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'moisture',
                            label: '水分含量(%)',
                            type: 'number',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'df',
                            label: '膳食纤维含量',
                            type: 'number',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'createDate',
                            label: '录入时间',
                            type: 'datetime',
                            hide: true,
                            overHidden: true,
                            minWidth: 60,
                            addDisplay: false,
                            editDisplay: false,
                            maxlength: 200,
                            span: 12,
                        },
                        {
                            prop: 'createUserName',
                            label: '录入人',
                            type: 'input',
                            overHidden: true,
                            hide: true,
                            minWidth: 60,
                            maxlength: 200,
                            addDisplay: false,
                            editDisplay: false,
                            span: 12,
                        },
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
                row.peId = this.peid;
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
                    peId: this.peid,
                }, this.searchForm);
                get("/" + this.url + "/findList", query, res => {
                    this.tableData = res.map(x=>{
                        x.minerals=x.minerals.split(",");
                        x.carbohydrate=x.carbohydrate.split(",");
                        return x;
                    });
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
