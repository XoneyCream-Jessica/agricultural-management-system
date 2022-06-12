<template>
    <!--产品列表模块-->
    <div class="box">
        <div class="left">
            <div class="title">
                <span>分类菜单</span>
            </div>
            <el-menu
                    class="el-menu-vertical-demo"
                    @select="menuClick">
                <label v-for="item in productTree" :key="item.value">
                    <el-submenu :index="item.id" v-if="item.children!=null && item.children.length!=0">
                        <template slot="title">
                            <span>{{item.label}}</span>
                        </template>
                        <el-menu-item-group >
                            <el-menu-item :index="row.id" v-for="row in item.children">
                                <el-avatar size="large" :src="row.img"></el-avatar>
                                <span style="margin-left: 10px;">{{row.label}}</span>
                            </el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item v-else :index="item.id">
                        <span slot="title">{{item.label}}</span>
                    </el-menu-item>
                </label>
            </el-menu>
        </div>
        <div class="right" v-loading="tableLoading" v-if="!tableLoading">
            <el-row :gutter="20" >
                <el-col :span="8" v-for="item in tableData" style="margin-bottom: 20px">
                    <el-card class="box-card" style="height: 400px" >
                        <div slot="header" class="clearfix">
                            <span>{{item.title}}</span>
                            <el-button style="float: right; padding: 3px 0" type="text">
                                {{item.price}}元/{{unitList |getDictLableByList(item.unit)}}
                            </el-button>
                        </div>
                        <div  class="text item">
                            <el-carousel height="300px">
                                <el-carousel-item v-for="path in item.productExampleImgs" :key="path">
                                    <img :src="path" @click.stop="openInfo(item)" style="width: 100%;height: 100%" alt="">
                                </el-carousel-item>
                            </el-carousel>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <el-pagination
                    style="text-align: center"
                    @current-change="handleCurrentChange"
                    :current-page.sync="page.currentPage"
                    :page-size="page.pageSize"
                    layout="total, prev, pager, next"
                    :total="page.total">
            </el-pagination>
        </div>
        <el-dialog
                title="产品详细"
                :visible.sync="dialogVisible"
                width="80%">
            <tab_index :data="data"></tab_index>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="addCart(data)"> 加入购物车</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>

    module.exports = {
        name: "productList",
        components: {
            'tab_index': httpVueLoader('/component/tab/index.vue'),
        },
        data() {
            return {
                activeName:"1",
                dialogVisible:false,
                //单个商品信息
                data:null,
                //单位集合
                unitList:[],
                //加载
                tableLoading:true,
                //构建分类树的数据
                productTree:[],
                //产品实例数据
                tableData:[],
                page: {
                    total: 0,  // 总记录数
                    currentPage: 1,  // 当前页数
                    pageSize: 6,  // 每页显示多少条
                    sortdatafield: "create_date",  // 排序字段名
                    sortorder: "desc"  // 排序方式 asc/desc
                }
            }
        },
        created() {
            this.init();
        },
        methods: {

            /**
             * 加入购物车
             * @param data
             */
            addCart(data){
                post("/cart/add",{
                    peId:data.id,
                },res=>{
                    mes("加入购物车成功！")
                    this.dialogVisible=false;
                })
            },

            menuClick(id){
                this.getList(id);
            },
            //查看详细
            openInfo(row){
                get("/product-example/get-by-id/"+row.id,res=>{
                    this.data=res;
                    this.dialogVisible=true;
                })
            },

            //页数改变
            handleCurrentChange(val){
                this.page.currentPage = val;
                this.getList();
            },

            //查询列表
            getList(id){
                let query = Object.assign({
                    productId:id,
                }, this.searchForm, this.page);
                get("/product-example/findPage", query, res => {
                    this.tableData=[];
                    this.tableData = res.records.map(x=>{
                        x.productExampleImgs=x.productExampleImgs.map(y=>{
                            return y.path;
                        })
                        return x;
                    });
                    this.page.total = res.total;
                })
            },


            init(){
                this.tableLoading = true;
                //获取分类码表
                let typeList=dic('/sys-dict-item/findByDictCode/product_type').map((x,i)=>{
                    x.id="$n"+i;
                    x["children"]=[]
                    return x;
                });
                typeList.unshift({
                    label:"全部",
                    id:null,
                })

                //获取所有产品
                get("/product/findList",res=>{
                    res.forEach(x=>{
                        x.label=x.name;
                        x.node="Y";
                        typeList.filter(y=>y.value==x.type)[0].children.push(x);
                    })
                    this.productTree=typeList;
                    this.getList();
                })
                this.unitList=dic('/sys-dict-item/findByDictCode/price_unit');
                this.tableLoading = false;
            },
        }
    }
</script>

<style scoped>
    .box {
        width: 100%;
        height: 100%;
        display: flex;
    }
    .left{
        width: 20%;
        height: 100%;
        border: 1px solid #d9593f;
    }
    .left>.title{
        width: 100%;
        padding-top: 10px;
        padding-bottom: 10px;
        text-align: center;
        font-size: 20px;
        font-weight: bolder;
        line-height: 20px;
        background: #d9593f;
        color: white;
    }
    .right{
        margin-left: 10px;
        flex: 1;
        height: 100%;
        display: flex;
        flex-direction: column;
        border: 1px solid #d9593f;
    }

    .right>.el-row{
        flex: 1 !important;
    }

    .el-carousel__indicators{
        display:none!important;
    }
</style>