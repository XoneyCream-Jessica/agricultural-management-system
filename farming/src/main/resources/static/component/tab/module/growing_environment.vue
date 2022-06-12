<template>
    <!--生长环境-->
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
        name: "ProductExampleImg",
        props: {
            peid: String
        },
        data() {
            return {
                ok:false,
                url: "growing-environment",
                // 表单数据
                form: {
                    isPesticide: "",
                },
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
                    menuWidth: 200,
                    menuAlign: 'center',
                    menu: true,
                    addBtn: false,
                    delBtn: false,
                    editBtn: false,
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
                      prop: 'pestName',
                      label: '害虫名称',
                      type: 'input',
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
                      prop: 'temperature',
                      label: '温度',
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
                      prop: 'humidity',
                      label: '湿度',
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
                      prop: 'waterAmount',
                      label: '用水量/kg',
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
                      prop: 'illuminationTime',
                      label: '光照时长/h',
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
                      prop: 'fertilizationAmount',
                      label: '施肥量/kg',
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
                      prop: 'fertilizationDate',
                      label: '施肥时间',
                      type: 'time',
                      hide: true,
                      valueFormat:"HH:mm:ss",
                      format:"HH:mm:ss",
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
                      prop: 'isPesticide',
                      label: '是否使用农药',
                      type: 'radio',
                      overHidden: true,
                      minWidth: 60,
                      maxlength: 200,
                      dicUrl:'/sys-dict-item/findByDictCode/yes_or_no',
                      span: 12,
                      rules: [{
                        required: true,
                        message: '请选择',
                        trigger: 'blur'
                      }]
                    },
                    {
                      prop: 'isWeeding',
                      label: '是否除草',
                      type: 'radio',
                      overHidden: true,
                      minWidth: 60,
                      maxlength: 200,
                      dicUrl: '/sys-dict-item/findByDictCode/yes_or_no',
                      span: 12,
                      rules: [{
                        required: true,
                        message: '请选择',
                        trigger: 'blur'
                      }]
                    },
                    {
                      prop: 'pesticideName',
                      label: '农药名',
                      type: 'input',
                      hide: true,
                      addDisplay: false,
                      editDisplay: false,
                      viewDisplay: false,
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
                      prop: 'pesticideAmount',
                      label: '农药使用量/kg',
                      type: 'number',
                      hide: true,
                      addDisplay: false,
                      editDisplay: false,
                      viewDisplay: false,
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
                      prop: 'soilConditions',
                      label: '土壤检测情况',
                      type: 'select',
                      overHidden: true,
                      minWidth: 60,
                      maxlength: 200,
                      dicUrl: '/sys-dict-item/findByDictCode/soil_conditions',
                      span: 12,
                      rules: [{
                        required: true,
                        message: '请选择',
                        trigger: 'blur'
                      }]
                    },
                    {
                      prop: 'waterQuality',
                      label: '水质检测情况',
                      type: 'select',
                      overHidden: true,
                      minWidth: 60,
                      maxlength: 200,
                      dicUrl: '/sys-dict-item/findByDictCode/water_quality',
                      span: 12,
                      rules: [{
                        required: true,
                        message: '请选择',
                        trigger: 'blur'
                      }]
                    },
                    {
                      prop: 'collectDate',
                      label: '采集时间',
                      type: 'datetime',
                      valueFormat:"yyyy-MM-dd HH:mm:ss",
                      format:"yyyy-MM-dd HH:mm:ss",
                      overHidden: true,
                      minWidth: 60,
                      span: 12,
                      rules: [{
                        required: true,
                        message: '请选择',
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
                    peId: this.peid
                }, this.searchForm);
                get("/" + this.url + "/findProcGrowEnvListByPeId/" + this.peid, res => {
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
