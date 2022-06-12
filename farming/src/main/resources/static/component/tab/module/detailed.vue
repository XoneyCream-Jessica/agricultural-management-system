<template>
    <div class="box">
        <el-form ref="form" :model="data" label-width="80px" style="width: 100%">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="产品图片:">
                        <el-image
                                style="width: 300px; height: 200px"
                                :src="data.product.img"
                                :preview-src-list="[data.product.img]">
                        </el-image>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="产品名:">
                        {{data.product.name}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="产品类型:">
                        <el-tag>{{{code:"product_type",val:data.product.type} |getDictLable}}</el-tag>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="总量/g:">
                        {{data.product.total}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="保质期:">
                        {{data.product.shelfLifeNum}}{{{code:"shelf_life_unit",val:data.product.unit} |getDictLable}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="上架人:">
                        {{data.product.createUserName}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="联系电话:">
                        {{data.product.createUserPhone}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="上架时间:">
                        {{data.product.createDate}}
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    module.exports={
        name: "info",
        props: {
            data: Object,
        },

        data(){
            return{

            }
        },

        methods:{

        }
    }
</script>

<style scoped>
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>
