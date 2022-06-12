<template>
    <!--用户信息修改模块-->
    <div class="box">
        <el-dialog
                title="用户信息"
                :visible.sync="dialogVisible"
                width="40%">
            <avue-form ref="form" v-model="userInfo" :option="option"  @submit="submit"></avue-form>
        </el-dialog>

    </div>
</template>
<script>
    module.exports = {
        name: "user_info",
        data(){
            return{
                dialogVisible:false,
                userInfo:getSession("userInfo"),
                option:{
                    column:[
                        {
                            prop: 'name',
                            label: '姓名',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 24,
                            search: true,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'sex',
                            label: '性别',
                            type: 'radio',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 200,
                            span: 24,
                            dicUrl:'/sys-dict-item/findByDictCode/sex',
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                        {
                            prop: 'phone',
                            label: '电话',
                            type: 'input',
                            overHidden: true,
                            minWidth: 60,
                            maxlength: 11,
                            span: 24,
                            rules: [{
                                required: true,
                                message: '请输入',
                                trigger: 'blur'
                            }]
                        },
                    ],
                }
            }
        },
        methods:{
            submit(form,done){
                this.$confirm('是否确认修改？修改后将重新登录', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    put("sys-user/update", {
                        id:form.id,
                        name:form.name,
                        username:form.username,
                        phone:form.phone,
                        sex:form.sex,
                    }, res => {
                        this.$message.success('修改成功');
                        window.location.href="logout";
                    })
                    done()
                })

            },
        }
    }
</script>

<style scoped>

</style>