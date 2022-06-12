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
            prop: 'img',
            label: '图片',
            type: 'upload',
            action:"/upload",
            listType: 'picture-img',
            limit:1,
            propsHttp: {
                res: "data",
                url:"path"
            },
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 24,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },{
            prop: 'name',
            label: '产品名',
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
            prop: 'type',
            label: '产品类别',
            type: 'select',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 24,
            search: true,
            dicUrl:'/sys-dict-item/findByDictCode/product_type',
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'total',
            label: '总量/g',
            type: 'number',
            precision:0,
            multiple:true,
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 24,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'shelfLifeNum',
            label: '保质期',
            type: 'number',
            precision:0,
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 12,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'unit',
            label: '单位',
            type: 'select',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 12,
            dicUrl:'/sys-dict-item/findByDictCode/shelf_life_unit',
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'createUserName',
            label: '发布人',
            type: 'input',
            overHidden: true,
            minWidth: 60,
            maxlength: 500,
            span: 12,
            addDisplay: false,
            editDisplay: false
        },
        {
            prop: 'createUserPhone',
            label: '联系方式',
            type: 'input',
            overHidden: true,
            minWidth: 60,
            maxlength: 500,
            span: 12,
            addDisplay: false,
            editDisplay: false
        },
        {
            prop: 'createDate',
            label: '发布时间',
            type: 'datetime',
            valueFormat:"yyyy-MM-dd HH:mm:ss",
            format:"yyyy-MM-dd HH:mm:ss",
            overHidden: true,
            minWidth: 60,
            maxlength: 500,
            span: 12,
            addDisplay: false,
            editDisplay: false
        },
    ]
}


