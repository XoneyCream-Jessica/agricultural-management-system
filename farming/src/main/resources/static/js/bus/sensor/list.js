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
    menuWidth: 150,
    menuAlign: 'center',
    column: [
        {
            prop: 'id',
            label: 'ID',
            hide: true,
            addDisplay: false,
            editDisplay: false
        }, {
            prop: 'name',
            label: '传感器名',
            type: 'input',
            overHidden: true,
            minWidth: 80,
            maxlength: 200,
            span: 24,
            search: true,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        }, {
            prop: 'sensorType',
            label: '类别',
            type: 'select',
            overHidden: true,
            minWidth: 80,
            maxlength: 200,
            span: 24,
            search: true,
            dicUrl:'/sys-dict-item/findByDictCode/sensor_type',
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },

    ]
}


