<template>
    <!--购物车模块-->
    <div class="box" id="cartbox">
        <avue-crud
                v-if="ok"
                ref="crud"
                :data="tableData"
                :table-loading="tableLoading"
                :option="tableOption"
                @on-load="getList"
                @refresh-change="refreshChange">
            <template slot-scope="scope" slot="menuLeft">
                <el-button type="danger" size="small" @click="empty()">清空</el-button>
            </template>
            <template slot="price" slot-scope="scope">
                <el-tag v-if="scope.row.price!=null ">{{scope.row.price}}元/{{scope.row.priceUnit}}</el-tag>
            </template>
            <template slot="shelfLifeNum" slot-scope="scope">
                <el-tag v-if="scope.row.shelfLifeNum!=null">{{scope.row.shelfLifeNum}}{{scope.row.shelfLifeUnit}}</el-tag>
            </template>
            <template slot-scope="scope" slot="menu">
                <div v-if="scope.row.type=='S'">
                    <el-button type="text" icon="el-icon-circle-plus" @click="add(scope.row)">增加</el-button>
                    <el-button type="text" icon="el-icon-remove" v-if="scope.row.number>1" @click="del(scope.row)">减少</el-button>
                    <el-button type="text" icon="el-icon-circle-close" v-else @click="del(scope.row)">移除</el-button>
                </div>
            </template>
        </avue-crud>
        <div style="width: 100%;text-align: right;height: 50px">
            <span style="font-size: 25px"> 总价：<span style="color: #00a680">{{totalPrice}}元</span></span>
            <el-button type="primary" style="margin-left: 50px;" @click="dialogVisible=true">购买</el-button>
        </div>
        <el-dialog
                title="购买"
                :visible.sync="dialogVisible"
                :modal="false"
                width="30%">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="订单总金额:">
                    {{totalPrice}}
                </el-form-item>
                <el-form-item label="收货人:" prop="contact">
                    <el-input v-model="ruleForm.contact" maxlength="20"></el-input>
                </el-form-item>
                <el-form-item label="联系电话:" prop="phone">
                    <el-input v-model="ruleForm.phone" maxlength="11"></el-input>
                </el-form-item>
                <el-form-item label="收货地址:" prop="address">
                    <el-input v-model="ruleForm.address" maxlength="200"></el-input>
                </el-form-item>
                <el-form-item label="备注:" prop="note">
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 4, maxRows: 4}"
                            resize="none"
                            maxlength="500"
                            placeholder="请输入内容"
                            v-model="ruleForm.note">
                    </el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="buy()">确 定</el-button>
          </span>
        </el-dialog>


    </div>
</template>

<script>
    module.exports = {
        name: "cart",
        data() {
            return {
                //确认表单可以加载
                ok: false,
                // 表格加载标志
                tableLoading: false,
                //弹窗
                dialogVisible: false,
                //表单
                ruleForm:{},
                //校验
                rules:{
                    contact: [
                        { required: true, message: '请输入', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '请输入', trigger: 'blur' }
                    ],
                    address: [
                        { required: true, message: '请输入', trigger: 'blur' }
                    ],
                },
                //表格树
                tableData: [],
                //总价
                totalPrice:0,
                // 表格参数
                tableOption: {
                    border: true,
                    index: false,
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
                    viewBtn: false,
                    addBtn:false,
                    delBtn:false,
                    editBtn:false,
                    defaultExpandAll:true,
                    rowKey:"id",
                    column: [
                        {
                            prop: 'id',
                            label: 'id',
                            hide: true,
                            viewDisplay: false,
                            addDisplay: false,
                        },
                        {
                            prop: 'productId',
                            label: '产品id',
                            hide: true,
                            viewDisplay: false,
                            addDisplay: false,
                            editDisplay: false
                        },
                        {
                            prop: 'productName',
                            label: '产品名',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'shelfLifeNum',
                            label: '保质期',
                            overHidden: true,
                            slot:true,
                            minWidth: 40,
                        },
                        {
                            prop: 'title',
                            label: '标题',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'exampleId',
                            label: '产品批号',
                            overHidden: true,
                            minWidth: 60,
                        },
                        {
                            prop: 'price',
                            label: '产品售价',
                            overHidden: true,
                            slot:true,
                            minWidth: 49,
                        },

                        {
                            prop: 'number',
                            label: '购买数量',
                            overHidden: true,
                            minWidth: 40,
                        },
                    ]
                },
            }
        },
        mounted() {
            setTimeout(res => {
                this.tableOption.height = document.getElementById("cartbox").offsetHeight - 130;
                this.ok = true;
            }, 100)
        },
        methods: {
            //确认购买
            buy(){
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        post("/cart/buy",this.ruleForm,res=>{
                            mes("购买成功，产品属于货到付款，请收到货后将余款交于送货员后在订单管理中确认收货！")
                            this.dialogVisible=false;
                            this.getList();
                        })
                    }
                });
            },
            //清空
            empty(){
                post("/cart/empty",res=>{
                    mes("已成功清空购物车！");
                    this.getList();
                })
            },

            //加一个
            add(row){
                this.tableLoading=true;
                put("/cart/update",{
                    id:row.id,
                    number:row.number+1,
                },res=>{
                    this.getList();
                    mes("已成功增加购买数量！");
                    this.tableLoading=false;
                })
            },

            //减一个
            del(row){
                this.tableLoading=true;
                if(row.number==1){
                    //删除
                    del("/cart/delete-by-id/"+row.id,res=>{
                        mes("已成功移除商品！");
                        this.getList();
                        this.tableLoading=false;
                    })
                }

                put("/cart/update",{
                    id:row.id,
                    number:row.number-1,
                },res=>{
                    mes("已成功减少购买数量！");
                    this.getList();
                    this.tableLoading=false;
                })
            },

            /**
             * 获取列表数据
             */
            getList() {
                this.tableLoading = true;
                get("/cart/findList", res => {
                    //构建成字父结构
                    //计算总价
                    this.totalPrice=0;
                    res.forEach(x=>{
                        this.totalPrice=this.totalPrice+(x.price*x.number);
                    })

                    this.buildTree(res);
                    this.tableLoading = false;
                })
            },


            buildTree(list){
                let tree=[];
                list.forEach(x=>{
                    //判断当前树中有没有改大类
                    if(tree.filter(y=>y.productId==x.productId).length==0){
                        //没有当前大类
                        tree.push({
                            id:uuid(),
                            productId:x.productId,
                            productName:x.productName,
                            shelfLifeNum:x.shelfLifeNum,
                            shelfLifeUnit:x.shelfLifeUnit,
                            type:"F",
                            children:[
                                {
                                    id:x.id,
                                    exampleId:x.exampleId,
                                    title:x.title,
                                    price:x.price,
                                    priceUnit:x.priceUnit,
                                    number:x.number,
                                    type:"S",
                                }
                            ]
                        })

                    }else{
                        //找到对应的
                        tree.map(y=>{
                            if(y.productId==x.productId){
                                y.children.push(
                                    {
                                        id:x.id,
                                        exampleId:x.exampleId,
                                        title:x.title,
                                        price:x.price,
                                        priceUnit:x.priceUnit,
                                        number:x.number,
                                        type:"S",
                                    }
                                )
                            }
                        })
                    }
                })
                this.tableData=tree;
            },

            /**
             * 刷新
             */
            refreshChange() {
                this.getList();
            },
        },
    }
</script>

<style scoped>
    .box {
        width: 100%;
        height: 100%;
    }
</style>