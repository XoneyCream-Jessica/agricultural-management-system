var tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    indexWidth: 50,
    stripe: true,
    align: 'center',
    searchMenuSpan: 6,
    labelWidth: 120,
    height: '600px',
    calcHeight: 400,
    dialogWidth: 900,
    menuWidth: 300,
    menuAlign: 'center',
    addBtn: buttonPermissionsCheck("SYS_USER_ADD"),
    editBtn: buttonPermissionsCheck("SYS_USER_UPDATE"),
    delBtn: buttonPermissionsCheck("SYS_USER_DEL"),
    column: [
        {
            prop: 'id',
            label: 'ID',
            hide: true,
            addDisplay: false,
            editDisplay: false
        }, {
            prop: 'username',
            label: '用户名',
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
        {
            prop: 'roleNames',
            label: '角色',
            type: 'select',
            multiple:true,
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            addDisplay: false,
            editDisplay: false,
            span: 24,
            search: true,
            dicData:dic('/sys-role/findList').map(x=>{
                return {
                    value:x.name,
                    label:x.name
                }
            }),
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
    ]
}


