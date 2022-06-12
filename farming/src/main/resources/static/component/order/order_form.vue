<template>
    <!--订单管理-->
    <div class="box" id="orderBox">
        <avue-crud
                v-if="ok"
                ref="crud"
                :page="page"
                v-model="form"
                :data="tableData"
                :table-loading="tableLoading"
                :option="tableOption"
                @on-load="getList"
                @search-change="searchChange"
                @refresh-change="refreshChange"
                @size-change="sizeChange"
                @current-change="currentChange">
            <template slot-scope="scope" slot="menu">
                <el-button :size="scope.size" :type="scope.type" icon="el-icon-view" @click="openDia(scope.row)">查看</el-button>
                <el-button :size="scope.size" :type="scope.type" icon="el-icon-check" v-if="scope.row.state=='01' && is_me===false" @click="confirmDelivery(scope.row.id)">发货</el-button>
                <el-button :size="scope.size" :type="scope.type" icon="el-icon-check"  v-if="scope.row.state=='02' && is_me" @click="confirmGoods(scope.row.id)">确认收货</el-button>
            </template>
        </avue-crud>
        <el-dialog
                title="订单详细"
                :visible.sync="dialogVisible"
                width="70%">
            <order_info :oid="obj.id" :key="obj.id"></order_info>
        </el-dialog>
    </div>
</template>
<script>
    module.exports = {
        name: "order_list",
        components: {
            'order_info': httpVueLoader('/component/order/order_info.vue'),
        },
        props: {
            is_me: {
                type:Boolean,
                default:true,
            },
        },
        data() {
            return {
                ok: false,
                url: "order-form",
                // 搜索条件
                searchForm: {},
                // 表格加载标志
                tableLoading: false,
                //弹框表示
                dialogVisible:false,
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
                    menuWidth: 150,
                    menuAlign: 'center',
                    editBtn: false,
                    addBtn: false,
                    delBtn: false,
                    column: [
                        {
                            prop: 'id',
                            label: '订单号',
                            hide: false,
                            addDisplay: false,
                            editDisplay: false,
                            search: true,
                        },
                        {
                            prop: 'money',
                            label: '订单金额',
                            type: 'number',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'contact',
                            label: '联系人',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                            search: true,
                        },
                        {
                            prop: 'phone',
                            label: '联系电话',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                        }, {
                            prop: 'address',
                            label: '收货地址',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'note',
                            label: '备注',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'state',
                            label: '订单状态',
                            type: 'select',
                            dicData:[
                                {value:"01",label:"待发货"},
                                {value:"02",label:"已发货"},
                                {value:"03",label:"已收货"},
                            ],
                            overHidden: true,
                            minWidth: 60,
                        },
                    ]
                },
                // 表单数据
                form: {},
                //当前选中
                obj: {},
                // 分页参数
                page: {
                    total: 0,  // 总记录数
                    currentPage: 1,  // 当前页数
                    pageSize: 20,  // 每页显示多少条
                    sortdatafield: "create_date",  // 排序字段名
                    sortorder: "desc"  // 排序方式 asc/desc
                }
            }
        },
        mounted() {
            setTimeout(res => {
                this.tableOption.height = document.getElementById("orderBox").offsetHeight - 200;
                this.ok = true;
            }, 100)
        },
        methods: {


            //确认发货
            confirmDelivery(id){
                this.$confirm('是否确认发货？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    post("/" + this.url + "/confirmDelivery",{
                        id:id,
                    },res=>{
                        mes("操作成功！")
                        this.getList();
                    })
                })


            },

            //确认收货
            confirmGoods(id){
                this.$confirm('是否确认收货？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    post("/" + this.url + "/confirmGoods",{
                        id:id,
                    },res=>{
                        mes("操作成功！")
                        this.getList();
                    })
                })


            },


            //打开弹窗
            openDia(row){
                this.obj=row;
                this.dialogVisible=true;
            },

            /**
             * 获取列表数据
             * @param page 分页参数
             * @param params 查询条件
             */
            getList(page, params) {
                this.tableLoading = true;
                let query = Object.assign({
                        isMe:this.isMe,
                }, this.searchForm, this.page);
                get("/" + this.url + "/findPage", query, res => {
                    this.tableData = res.records;
                    this.page.total = res.total;
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