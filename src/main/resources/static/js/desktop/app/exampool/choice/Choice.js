/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/*
/!**
 * queryForm
 *!/
Ext.define('Desktop.exampool.Choice', {
    extend: 'Ext.form.Panel',
    title: '题目查询',
    id : 'exampool-choice-form',
    frame: true,
    region: 'north',
    labelAlign: 'right',
    collapsible: true,
    height: '15%',
    border: false,
    bodyPadding: 20,
    items: [{
        layout: 'column',
        items: [{
            columnWidth: .3,
            layout: 'form',
            items: [{
                xtype: 'textfield',
                name: 'name',
                fieldLabel: '题目名称',
                labelAlign: "right",
                anchor: '70%'
            }, {
                name: 'updateTimeStart',
                xtype: 'datefield',
                labelAlign: "right",
                fieldLabel: '添加时间',
                format: 'Y-m-d',
                anchor: '70%',
                showToday: true,
                editable: false
            }]

        }, {
            columnWidth: .3,
            layout: 'form',
            items: [{
                xtype: 'combo',
                editable: false,
                mode: 'local',
                fieldLabel: '题目类型',
                labelAlign: "right",
                emptyText: '--请选择--',
                anchor: '60%',
                triggerAction: 'all',
                selectOnFocus: true,
                hiddenName: 'state',
                displayField: 'text',
                valueField: 'id',
                store: new Ext.data.SimpleStore({
                    fields: ['id', 'text'],
                    data: [['0', '公务员试题'],
                        ['1', '驾考试题'],
                        ['2', '自考试题'],
                        ['3', '其他分类']]
                })
            }, {
                name: 'updateTimeEnd',
                xtype: 'datefield',
                fieldLabel: '至',
                labelAlign: "right",
                format: 'Y-m-d',
                anchor: '70%',
                showToday: true,
                editable: false
            }]
        }, {
            columnWidth: .3,
            layout: 'form',
            items: [{
                xtype: 'combo',
                editable: false,
                mode: 'local',
                fieldLabel: '正确答案',
                emptyText: '--请选择--',
                labelAlign: "right",
                anchor: '60%',
                triggerAction: 'all',
                selectOnFocus: true,
                hiddenName: 'state',
                displayField: 'text',
                valueField: 'id',
                store: new Ext.data.SimpleStore({
                    fields: ['id', 'text'],
                    data: [['0', 'A'],
                        ['1', 'B'],
                        ['2', 'C'],
                        ['3', 'D'],
                        ['4', 'E'],
                        ['5', 'F'],
                        ['6', 'G'],
                        ['7', 'H']]
                })
            }]
        }]
    }],
    keys: [{
        key: Ext.EventObject.ENTER,
        fn: function () {
            userStore.reload()
        },
        scope: this
    }],
    buttons: [{
        text: '查询',
        handler: function () {
            userStore.reload();
        }
    }, {
        text: '清空条件',
        handler: function () {
            Ext.getCmp('exampool-choice-form').getForm().reset();
        },
        scope: this
    }]
});


/!**
 * grid
 *!/

Ext.define('ChoiceModel', {
    extend: 'Ext.data.Model',
    fields: ['title', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'answer']
});

var userStore = Ext.create('Ext.data.Store', {
    model: 'ChoiceModel',
    autoLoad: true,
    pageSize: 10, //设置每页显示的数据数量
    proxy: {
        type: 'ajax',
        url: 'static/js/app/common/main/data/data.json',
        reader: {
            type: 'json',
            root: 'choice', //指定从json的哪个属性获取数据，如果不指定，则从json的跟属性获取
            totalProperty: 'total' //总数据数量
        }
    }
});

Ext.define('Desktop.exampool.ChoiceGrid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'static.js.app.plugins.ProgressBarPager'
    ],
    border: false,
    stripeRows: true,
    frame: true,
    region: 'center',
    store: userStore, //绑定上面创建的Store
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    dockedItems: [{
        xtype: 'pagingtoolbar', //在Grid Panel中添加paging toolbar
        store: userStore, //把paging toolbar的Store设置成和Grid Panel的Store一样
        displayInfo: true,
        plugins: 'progressbarpager'
    }],
    tbar: [{
        text: '添加',
        id: 'addCustomer',
        glyph: 0xf055,
        listeners: {}

    }, '-', {
        text: '修改',
        id: 'updateCustomer',
        glyph: 0xf14b,
        listeners: {}
    }, '-', {
        text: '删除',
        glyph: 0xf1f8,
        handler: function () {
        },
        listeners: {}
    }, '-', {
        text: '导入文件',
        glyph: 0xf0ed,
        handler: function () {
        },
        listeners: {}
    }, '-', {
        text: '导出题目',
        glyph: 0xf0ee,
        handler: function () {
        },
        listeners: {}
    }, '-', {
        text: '来20题',
        glyph: 0xf02c,
        handler: function () {
        },
        listeners: {}
    }],
    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            text: '题目',
            width: '50%',
            sortable: false,
            hideable: false,
            dataIndex: 'title' //Grid Panel中显示的字段，必须要和User Model中的字段一致
        },
        {
            text: '选项A',
            width: '10%',
            dataIndex: 'a'
        },
        {
            text: '选项B',
            width: '10%',
            dataIndex: 'b'
        }
        ,
        {
            text: '选项C',
            width: '10%',
            dataIndex: 'c'
        }
        ,
        {
            text: '选项D',
            width: '10%',
            dataIndex: 'd'
        }
        ,
        {
            text: '选项E',
            hidden: true,
            dataIndex: 'e'
        },
        {
            text: '选项F',
            hidden: true,
            dataIndex: 'f'
        }
        ,
        {
            text: '选项G',
            hidden: true,
            dataIndex: 'g'
        }
        ,
        {
            text: '选项H',
            hidden: true,
            dataIndex: 'h'
        }
        ,
        {
            text: '正确答案',
            width: '10%',
            dataIndex: 'answer'
        }
    ]
});*/
Ext.Loader.setConfig({enabled: true});
Ext.application({
    name:'examPoolChoice',
    appFolder: basePath + '/static/js/desktop/app/exampool/choice',
    controllers:[
        'examPoolChoice.ChoiceController'
    ],
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            hideBorders: true,
            requires : [
                'examPoolChoice.ChoiceGrid',
                'examPoolChoice.ChoiceQueryForm'
            ],
                        // layout: 'border',
                        items: [
                            Ext.create('examPoolChoice.ChoiceQueryForm')
                        ]
        });
        /*Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [{
                region: 'north',
                html: '<h1 class="x-panel-header">Page Title</h1>',
                border: false,
                margins: '0 0 5 0'
            }, {
                region: 'west',
                collapsible: true,
                title: 'Navigation',
                width: 150
                // 这里通常可以使用 TreePanel 或者 AccordionLayout布局的导航菜单
            }, {
                region: 'south',
                title: 'South Panel',
                collapsible: true,
                html: 'Information goes here',
                split: true,
                height: 100,
                minHeight: 100
            }, {
                region: 'east',
                title: 'East Panel',
                collapsible: true,
                split: true,
                width: 150
            }, {
                region: 'center',
                xtype: 'tabpanel', // TabPanel本身没有title属性
                activeTab: 0,      // 配置默认显示的激活面板
                items: {
                    title: 'Default Tab',
                    html: 'The first tab\'s content. Others may be added dynamically'
                }
            }]
        });*/
    }
});
