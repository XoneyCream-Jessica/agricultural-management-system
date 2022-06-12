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
    menuWidth: 200,
    menuAlign: 'center',
    addBtn: buttonPermissionsCheck("SYS_ROLE_ADD"),
    editBtn: buttonPermissionsCheck("SYS_ROLE_UPDATE"),
    delBtn: buttonPermissionsCheck("SYS_ROLE_DEL"),
    column: [
        {
            prop: 'id',
            label: '角色id',
            hide: true,
            addDisplay: false,
            editDisplay: false
        }, {
            prop: 'name',
            label: '角色名',
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
        }, {
            prop: 'code',
            label: '角色编码',
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
    ]
}


