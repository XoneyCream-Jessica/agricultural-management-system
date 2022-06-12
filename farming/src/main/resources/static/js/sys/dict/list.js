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
    addBtn: buttonPermissionsCheck("SYS_DICT_ADD"),
    editBtn: buttonPermissionsCheck("SYS_DICT_UPDATE"),
    delBtn: buttonPermissionsCheck("SYS_DICT_DEL"),
    column: [
        {
            prop: 'id',
            label: 'ID',
            hide: true,
            addDisplay: false,
            editDisplay: false
        }, {
            prop: 'dictName',
            label: '字典名',
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
        },{
            prop: 'dictCode',
            label: '字典编码',
            type: 'input',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 24,
            search: true,

        },{
            prop: 'desc',
            label: '字典描述',
            type: 'input',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 24,
        },
    ]
}


