var vue = new Vue({})

/*vue全局过滤器*/
Vue.filter('getDictLable', function(row) {
    return dic('/sys-dict-item/findByDictCode/'+row.code).filter(x=>x.value==row.val)[0].label;
})

Vue.filter('getDictLableByList', function(list,val) {
    if(list==null){
        return null;
    }
    return list.filter(x=>x.value==val)[0].label
})


