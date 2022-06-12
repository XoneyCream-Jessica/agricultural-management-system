<template>
    <div class="menu-tree">
        <label v-for="item in data" :key="item.id">
            <el-submenu :index="item.id" v-if="item.children!=null && item.children.length!=0">
                <template slot="title">
                    <i :class="item.icon" ></i>
                    <span>{{item.name}}</span>
                </template>
                <label>
                    <tree_menu :data="item.children"></tree_menu>
                </label>
            </el-submenu>
            <el-menu-item v-else :index="item.id">
                <i :class="item.icon" ></i>
                <span slot="title">{{item.name}}</span>
            </el-menu-item>
        </label>
    </div>
</template>
<script>
    module.exports={
        name: "Menu",
        components: {
            'tree_menu': httpVueLoader('/component/tree_menu.vue')
        },
        props: {
            data: Array,
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>
