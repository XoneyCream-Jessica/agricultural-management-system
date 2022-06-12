<template>
    <div class="box" id="box">
        <avue-crud ref="crud"
                   v-if="ok"
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
        name: "SpotCheck",
        props: {
            peid: String,
            edit: String,
        },

        data() {
            return {
                ok: false,
                url: "spot-check",
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
                    labelWidth: 140,
                    calcHeight: 400,
                    dialogWidth: 1200,
                    menuWidth: 200,
                    menuAlign: 'center',
                    viewBtn: true,
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
                            prop: 'sensory',
                            label: '感官指标检查结果',
                            type: 'select',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            dicUrl:"/sys-dict-item/findByDictCode/check_situation",
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'pesticide',
                            label: '农药最大残留限量',
                            type: 'select',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            dicUrl:"/sys-dict-item/findByDictCode/check_situation",
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'metal',
                            label: '重金属及有害物质限量检查结果',
                            type: 'select',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            dicUrl:"/sys-dict-item/findByDictCode/check_situation",
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'germs',
                            label: '病原菌检查情况',
                            type: 'select',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 12,
                            dicUrl:"/sys-dict-item/findByDictCode/check_situation",
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'comprehensive',
                            label: '综合评价',
                            type: 'select',
                            overHidden: true,
                            minWidth: 35,
                            maxlength: 200,
                            span: 12,
                            dicUrl:"/sys-dict-item/findByDictCode/check_situation",
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'checkUnit',
                            label: '检查单位',
                            type: 'input',
                            overHidden: true,
                            minWidth: 35,
                            maxlength: 200,
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'checkPeople',
                            label: '检查人',
                            type: 'input',
                            overHidden: true,
                            minWidth: 35,
                            maxlength: 200,
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'checkDate',
                            label: '检查日期',
                            type: 'date',
                            overHidden: true,
                            valueFormat: "yyyy-MM-dd",
                            format: "yyyy-MM-dd",
                            minWidth: 35,
                            maxlength: 200,
                            span: 12,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'note',
                            label: '备注',
                            type: 'textarea',
                            hide: true,
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 500,
                            span: 24,
                        },
                    ]
                },
                //当前选中
                obj: {},
            }
        },
        mounted() {
            setTimeout(res => {
                this.tableOption.height =document.getElementById("box").offsetHeight-100;
                this.ok=true;
                if(this.edit=="true"){
                    this.tableOption.addBtn=true;
                    this.tableOption.editBtn=true;
                    this.tableOption.delBtn=true;
                }else{
                    this.tableOption.addBtn=false;
                    this.tableOption.editBtn=false;
                    this.tableOption.delBtn=false;
                }
                this.tableOption.menuWidth=this.edit=="true"?200:80;
            }, 100)
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
