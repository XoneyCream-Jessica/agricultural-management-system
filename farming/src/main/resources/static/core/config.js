/*配置js*/


//部署环境ip
var ip="192.168.2.47";

/*首页页面*/
var HOME_PAGE = {
    id: "-1",
    name: "首页",
    path: "/sy.html"
}


/*定义图标*/
var iconList = [
    {
        label: 'ElementUI',
        list: [
            "el-icon-edit", "el-icon-delete", "el-icon-plus",
            'el-icon-platform-eleme', 'el-icon-eleme', 'el-icon-delete-solid',
            'el-icon-delete', 'el-icon-s-tools', 'el-icon-setting', "el-icon-user-solid",
            "el-icon-user", "el-icon-phone", "el-icon-phone-outline", "el-icon-more",
            "el-icon-more-outline", "el-icon-star-on", "el-icon-star-off", "el-icon-warning",
            "el-icon-warning-outline", "el-icon-circle-plus-outline", "el-icon-remove-outline", "el-icon-circle-check",
            "el-icon-circle-close", "el-icon-minus", "el-icon-plus", "el-icon-check", "el-icon-close",
            "el-icon-picture", "el-icon-picture-outline", "el-icon-upload", "el-icon-upload2", "el-icon-download",
            "el-icon-s-promotion", "el-icon-s-home", "el-icon-s-grid", "el-icon-menu", "el-icon-s-data",
            "el-icon-s-custom", "el-icon-share", "el-icon-caret-left", "el-icon-caret-right", "el-icon-caret-bottom",
            "el-icon-caret-top", "el-icon-back", "el-icon-right", "el-icon-bottom", "el-icon-top",
            "el-icon-folder", "el-icon-folder-opened", "el-icon-chat-line-round", "el-icon-chat-dot-round", "el-icon-map-location",
            "el-icon-view", "el-icon-refresh-right","el-icon-suitcase"
        ]
    },
]


/**
 * 构建vue-定义统一的vue
 * @param obj
 */
function createVue(obj) {
    let a = deepMerge({
        data: {
            //钮权限检查显示方法
            buttonPermissionsCheck: buttonPermissionsCheck,
            // 搜索条件
            searchForm: {},
            // 表格加载标志
            tableLoading: false,
            // 表格数据
            tableData: [],
            // 表格参数
            tableOption: tableOption,
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
        },
        methods: {


            /**
             * 删除数据中不不在option的参数
             * @param data
             */
            delNotField(data){
                for(let key in data){
                   if( this.tableOption.column.filter(x=>x.prop==key).length==0){
                       //删除字段
                       delete data[key];
                   }
                }
                return data;
            },

            /**
             * 新增保存
             * @param row 行数据
             * @param done 完成回调
             * @param loading 加载回调
             */
            handleSave: function (row, done, loading) {
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

                put("/" + this.url + "/update", this.delNotField(delNullField(del$(row))) , res => {
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
                let query = Object.assign({}, this.searchForm, this.page);
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
    }, obj)
    return new Vue(a)
}



