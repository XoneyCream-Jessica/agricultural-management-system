<template>
    <div class="box" id="box">
        <avue-crud
                v-if="ok"
                ref="crud"
                v-model="form"
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
        name: "Nutrition",

        props: {
            peid: String,
        },
        watch: {},
        data() {
            return {
                ok: false,
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
                    height: '500px',
                    calcHeight: 400,
                    dialogWidth: 900,
                    menuWidth: 200,
                    menuAlign: 'center',
                    viewBtn: true,
                    addBtn: false,
                    delBtn: false,
                    editBtn: false,
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
                            label: '蛋白自含量(g/100g)',
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
                            multiple: true,
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
                        {
                            prop: 'createUserPhone',
                            label: '联系电话',
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
        mounted() {
            setTimeout(res => {
                this.tableOption.height =document.getElementById("box").offsetHeight-100;
                this.tableOption.menuWidth=this.edit=="true"?200:80;
                this.ok=true;
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
                    peId: this.peid,
                }, this.searchForm);
                get("/" + this.url + "/findList", query, res => {
                    this.tableData = res.map(x => {
                        x.minerals = x.minerals.split(",");
                        x.carbohydrate = x.carbohydrate.split(",");
                        return x;
                    });
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
