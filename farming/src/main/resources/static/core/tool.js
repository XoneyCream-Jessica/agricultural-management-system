var vue = new Vue({})


//获取url的参数
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){
            return decodeURI(pair[1]);
        }
    }
    return(false);
}


/**
 * 删除空字段
 * @param data
 */
function delNullField(obj) {
    let row=copyBean(obj);
    for(let key in row){
        if(row[key]==null || row[key]==''){
            delete row[key];
        }
    }
    return row;
}

/**
 * 移除avue自动生成的$开头的参数
 * @param obj
 */
function del$(obj){
    let row=copyBean(obj);
    for(let key in row){
        if(key.indexOf("$")==0){
            delete  row[key];
        }
    }
    return row;
}


/**
 * 复制字段
 * @param obj
 * @returns {any}
 */
function copyBean(obj) {
    return JSON.parse(JSON.stringify(obj));
}


function uuid(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [],
        i;
    radix = radix || chars.length;

    if (len) {
        // Compact form
        for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
    } else {
        // rfc4122, version 4 form
        var r;

        // rfc4122 requires these characters
        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
        uuid[14] = '4';

        // Fill in random data.  At i==19 set the high bits of clock sequence as
        // per rfc4122, sec. 4.1.5
        for (i = 0; i < 36; i++) {
            if (!uuid[i]) {
                r = 0 | Math.random() * 16;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
    }

    return uuid.join('');
}


/**
 * 深度合并对象
 * @param obj1
 * @param obj2
 * @returns {*}
 */
function deepMerge(obj1, obj2) {
    let key;
    for (key in obj2) {
        obj1[key] =
            obj1[key] &&
            obj1[key].toString() === "[object Object]" &&
            (obj2[key] && obj2[key].toString() === "[object Object]")
                ? deepMerge(obj1[key], obj2[key])
                : (obj1[key] = obj2[key]);
    }
    return obj1;
}


function buttonPermissionsCheck(code) {
    let permissions=getSession("userInfo").permissions;
    //判断是否有当前权限
    return  permissions.filter(x=>x.code==code).length==0?false:true;
}


/**
 * ajax get方法
 * @param url:访问url
 * @param data:数据
 * @param fun:回调函数
 */
function get(url, data, fun) {
    $.ajax({
        url: url,
        data: data,
        success: function (res) {
            //判断是否异常
            if(res.code!=1){
                vue.$message.error(res.msg);
            }else{
                if(fun==null){
                    data(res.data);
                }else{
                    fun(res.data);
                }
            }
        }
    })
}

/**
 * ajax del请求
 * @param url:访问url
 * @param data:数据
 * @param fun:回调函数
 */
function del(url, data, fun) {
    $.ajax({
        url: url,
        type: "delete",
        data: data,
        success: function (res) {
            if(res.code!=1){
                vue.$message.error(res.msg);
            }else{
                if(fun==null){
                    data(res);
                }else{
                    fun(res);
                }
            }

        }
    })
}


/**
 * ajax post方法
 * @param url:访问url
 * @param data:数据
 * @param fun:回调函数
 */
function post(url, data, fun) {
    $.ajax({
        url: url,
        type: "post",
        data: data,
        success: function (res) {
            if(res.code!=1){
                vue.$message.error(res.msg);
            }else{
                if(fun==null){
                    data(res);
                }else{
                    fun(res);
                }
            }

        }
    })
}

function postJson(url, data, fun) {
    $.ajax({
        url: url,
        type: "POST",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            if(res.code!=1){
                vue.$message.error(res.msg);
            }else{
                if(fun==null){
                    data(res);
                }else{
                    fun(res);
                }
            }

        }
    })
}



/**
 * ajax put方法
 * @param url:访问url
 * @param data:数据
 * @param fun:回调函数
 */
function put(url, data, fun) {
    $.ajax({
        url: url,
        type: "put",
        data: data,
        success: function (res) {
            if(res.code!=1){
                vue.$message.error(res.msg);
            }else{
                if(fun==null){
                    data(res);
                }else{
                    fun(res);
                }
            }

        }
    })
}


/**
 * 获取session数据
 * @param key:键
 */
function getSession(key){
    return JSON.parse(window.sessionStorage.getItem(key));
}

/**
 * 设置session数据
 * @param key：键
 * @param value：值
 */
function setSession(key,value) {
    window.sessionStorage.setItem(key, JSON.stringify(value));
}


/**
 * 同步请求返回数据
 * @param url:地址
 * @param data：参数
 */
function dic(url,data){
    let ret;
    $.ajax({
        type : "get",
        url : url,
        data:data,
        cache: false,
        async:false,  //使用同步的方式,true为异步方式
        success : function(res) {
            ret=res.data;
        }
    });
    return ret;
}

/**
 * 获取登录用户并设置session
 */
function getLoginUser(){
    let ret;
    $.ajax({
        type : "get",
        url : "/sys-user/getLoginUser",
        async:false,  //使用同步的方式,true为异步方式
        dataType : "json",
        success : function(res) {
            setSession("userInfo",res.data)
            ret=res.data;
        }
    });
    return ret;
}

/**
 * 成功输入框
 * @param msg
 */
function mes(msg) {
    vue.$message.success(msg);
}

/**
 * 失败消息提示
 * @param msg
 */
function mee(msg) {
    vue.$message.error(msg);
}

