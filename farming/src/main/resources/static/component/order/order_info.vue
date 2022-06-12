<template>
    <div class="box" id="box_order_info">
        <avue-crud
                v-if="ok"
                :data="tableData"
                :table-loading="tableLoading"
                :option="tableOption"
                @on-load="getList"
                @refresh-change="refreshChange">
        </avue-crud>
    </div>
</template>
<script>
    module.exports = {
        name: "order_info",
        props: {
            oid: String,
        },
        data() {
            return {
                ok: false,
                url: "order-info",
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
                    menuWidth: 120,
                    menuAlign: 'center',
                    menu:false,
                    editBtn: false,
                    addBtn: false,
                    delBtn: false,
                    column: [
                        {
                            prop: 'id',
                            label: 'ID',
                            hide: true,
                            addDisplay: false,
                            editDisplay: false
                        },
                        {
                            prop: 'peId',
                            label: '产品批号',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'title',
                            label: '产品标题',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'name',
                            label: '产品名',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'price',
                            label: '产品单价',
                            type: 'input',
                            overHidden: true,
                            minWidth: 40,
                        },
                        {
                            prop: 'number',
                            label: '购买数量',
                            type: 'number',
                            overHidden: true,
                            minWidth: 40,
                        },
                    ]
                },
            }
        },
        mounted() {
            setTimeout(res => {
                this.tableOption.height = document.getElementById("box_order_info").offsetHeight - 150;
                this.ok = true;
            }, 100)
        },
        methods: {
            /**
             * 获取列表数据
             * @param page 分页参数
             * @param params 查询条件
             */
            getList(page, params) {
                this.tableLoading = true;
                let query = Object.assign({
                    orderId: this.oid,
                }, this.searchForm);
                get("/" + this.url + "/findList", query, res => {
                    this.tableData = res;
                    this.tableLoading = false;
                })
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