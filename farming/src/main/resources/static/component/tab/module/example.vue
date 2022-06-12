<template>
    <div class="box">
        <el-form ref="form" :model="data" label-width="80px" style="width: 100%">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="生长图片:">
                        <el-image
                                style="width: 300px; height: 200px"
                                :src="data.productExampleImgs[0].path"
                                :preview-src-list="getImgs(data.productExampleImgs)">
                        </el-image>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="批号:">
                        {{data.id}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="库存:">
                        {{data.inventory}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="售价:">
                        {{data.price}}/{{{code:"price_unit",val:data.product.unit} |getDictLable}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="生产日期:">
                        {{data.productionDate}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="生产地:">
                        {{data.producer}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="发布时间:">
                        {{data.createDate}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="发布人:">
                        {{data.createUserName}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="联系电话:">
                        {{data.createUserName}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="备注:">
                        {{data.note}}
                    </el-form-item>
                </el-col>

            </el-row>
        </el-form>
    </div>
</template>

<script>
    module.exports = {
        name: "info",
        props: {
            data: Object,
        },
        computed: {

        },
        data() {
            return {}
        },

        methods: {
            getImgs: function (data) {

                return data.map(x=>x.path);
            }
        }
    }
</script>

<style scoped>

</style>