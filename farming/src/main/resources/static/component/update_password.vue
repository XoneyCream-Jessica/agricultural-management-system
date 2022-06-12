<template>
    <!--用户信息修改模块-->
    <div class="box">
        <el-dialog
                title="修改密码"
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
                            prop: 'odlPass',
                            label: '旧的密码',
                            type: 'password',
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
                            prop: 'newPass',
                            label: '新的密码',
                            type: 'password',
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
                            prop: 'confirmPass',
                            label: '确认密码',
                            type: 'password',
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
                    put("sys-user/updatePassword", form, res => {
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