<template>
    <div class="box">
        <el-tabs v-model="activeName" style="height: 100%;width: 100%;">
            <el-tab-pane label="产品基本信息" name="1">
                <detailed :data="data" v-if="activeName=='1'"></detailed>
            </el-tab-pane>
            <el-tab-pane label="产品批次信息" name="2">
                <example :data="data" v-if="activeName=='2'"></example>
            </el-tab-pane>
            <el-tab-pane label="生长环境记录" name="3">
                <growing_environment :peid="data.id" v-if="activeName=='3'"></growing_environment>
            </el-tab-pane>
            <el-tab-pane label="营养成分检测记录" name="4">
                <nutrition :peid="data.id" v-if="activeName=='4'"></nutrition>
            </el-tab-pane>
            <el-tab-pane label="产品抽查记录" name="5">
                <spot_check :peid="data.id" :edit="edit" v-if="activeName=='5'"></spot_check>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
    module.exports = {
        name: "index",
        props: {
            data: Object,
            edit: {
                type: String,
                default: "false"
            }
        },
        components: {
            'detailed': httpVueLoader('/component/tab/module/detailed.vue'),
            'example': httpVueLoader('/component/tab/module/example.vue'),
            'growing_environment': httpVueLoader('/component/tab/module/growing_environment.vue'),
            'nutrition': httpVueLoader('/component/tab/module/nutrition.vue'),
            'spot_check': httpVueLoader('/component/tab/module/spot_check.vue'),
        },
        data() {
            return {
                activeName: '1'
            }
        }
    }
</script>

<style scoped>
    .box {
        width: 100%;
        height: 100%;
    }

    .el-tabs {
        display: flex;
        flex-direction: column;
    }

    .el-tabs__content {
        flex: 1;
    }

    .el-tab-pane {
        height: 100% !important;
    }
</style>