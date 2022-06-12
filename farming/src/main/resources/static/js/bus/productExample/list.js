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
    viewBtn:true,
    column: [
        {
            prop: 'id',
            label: '批号',
            hide: true,
            addDisplay: false,
            editDisplay: false,
            span: 24,
        },
        {
            prop: 'title',
            label: '上架标题',
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
            prop: 'productId',
            label: '产品',
            type: 'select',
            filterable:true,
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            search: true,
            span: 12,
            dicData:dic('/product/findList').map(x=>{
                return{
                    value:x.id,
                    label:x.name
                }
            }),
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'productType',
            label: '产品类别',
            type: 'select',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 12,
            search: true,
            addDisplay: false,
            editDisplay: false,
            dicUrl:"/sys-dict-item/findByDictCode/product_type",
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'price',
            label: '售价/元',
            type: 'number',
            overHidden: true,
            minWidth: 60,
            maxlength: 10,
            span: 12,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'unit',
            label: '售卖单位',
            type: 'select',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 12,
            dicUrl:"/sys-dict-item/findByDictCode/price_unit",
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'productionDate',
            label: '生产日期',
            type: 'datetime',
            overHidden: true,
            minWidth: 60,
            maxlength: 200,
            span: 12,
            valueFormat:"yyyy-MM-dd HH:mm:ss",
            format:"yyyy-MM-dd HH:mm:ss",
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'producer',
            label: '生产地',
            hide: true,
            type: 'input',
            overHidden: true,
            minWidth: 60,
            maxlength: 255,
            span: 12,
            rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
            }]
        },
        {
            prop: 'inventory',
            label: '库存',
            type: 'number',
            precision:0,
            overHidden: true,
            minWidth: 60,
            addDisplay: false,
            editDisplay: false,
            maxlength: 255,
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
        {
            prop: 'note',
            label: '备注',
            type: 'textarea',
            hide: true,
            resize:'none',
            overHidden: true,
            minWidth: 60,
            maxlength: 500,
            span: 24,
        },
    ]
}


