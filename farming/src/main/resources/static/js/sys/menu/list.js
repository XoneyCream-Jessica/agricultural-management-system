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
    addBtn: buttonPermissionsCheck("SYS_MENU_ADD"),
    editBtn: buttonPermissionsCheck("SYS_MENU_UPDATE"),
    delBtn: buttonPermissionsCheck("SYS_MENU_DEL"),
    column: [
        {
            prop: 'id',
            label: '菜单id',
            hide: true,
            addDisplay: false,
            editDisplay: false
        }, {
            prop: 'name',
            label: '菜单名',
            type: 'input',
            overHidden: true,
            maxlength: 200,
            minWidth: 60,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        }, {
            prop: 'type',
            label: '菜单类型',
            type: 'radio',
            maxlength: 200,
            minWidth: 50,
            overHidden: true,
            dicData: [
                {value: '0', label: "菜单"},
                {value: '1', label: "页面"},
                {value: '2', label: "按钮"},
            ],
            rules: [{
                required: true,
                message: '请选择',
                trigger: 'blur'
            }]
        }, {
            prop: 'code',
            label: '菜单编码',
            type: 'input',
            overHidden: true,
            maxlength: 200,
            minWidth: 60,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        }, {
            prop: 'icon',
            label: '图标',
            type: 'icon',
            iconList: iconList,
            overHidden: true,
            maxlength: 200,
            minWidth: 60,
        }, {
            prop: 'path',
            label: '菜单路径',
            type: 'input',
            maxlength: 200,
            overHidden: true
        }, {
            prop: 'sort',
            label: '排序',
            type: 'number',
            overHidden: true,
            minWidth: 50,
        }
    ]
}


