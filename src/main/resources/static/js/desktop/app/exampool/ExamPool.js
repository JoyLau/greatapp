/**
 * Created by LiuFa on 2016/10/3.
 */
Ext.define('Desktop.exampool.ExamPool', {
    // requires : ['Desktop.exampool.Choice'],
    id:'exam-pool',
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('exam-pool');
        if(!win){
            win = desktop.createWindow({
                id: 'exam-pool',
                title:'题库',
                width:'50%',
                height:'70%',
                iconCls: 'exam-pool-task',
                animCollapse:true,
                constrainHeader:true,
                layout: 'fit',
                items: [Ext.create('Ext.tab.Panel', {
                    resizeTabs: true,
                    enableTabScroll: true,
                    activeTab: 0,
                    autoDestroy: true,
                    collapsible: false,
                    defaults: {
                        autoScroll: false
                    },
                    minTabWidth: 80,
                    items: [{
                        id: 'exam-pool-choice',
                        title: '选择题',
                        glyph: 0xf046,
                        loadMask: true,
                        group: null,
                        closable: false,
                        items: [Ext.create('Desktop.exampool.Choice'),

                            Ext.create('Ext.grid.Panel', {
                                border : false,
                                stripeRows : true,
                                frame : true,
                                region : 'center',
                                store: userStore, //绑定上面创建的Store
                                selModel: Ext.create('Ext.selection.CheckboxModel'),
                                dockedItems: [{
                                    xtype: 'pagingtoolbar', //在Grid Panel中添加paging toolbar
                                    store: userStore, //把paging toolbar的Store设置成和Grid Panel的Store一样
                                    dock: 'bottom',
                                    displayInfo: true
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
                                        hidden : true,
                                        dataIndex: 'e'
                                    },
                                    {
                                        text: '选项F',
                                        hidden : true,
                                        dataIndex: 'f'
                                    }
                                    ,
                                    {
                                        text: '选项G',
                                        hidden : true,
                                        dataIndex: 'g'
                                    }
                                    ,
                                    {
                                        text: '选项H',
                                        hidden : true,
                                        dataIndex: 'h'
                                    }
                                    ,
                                    {
                                        text: '正确答案',
                                        width: '10%',
                                        dataIndex: 'answer'
                                    }


                                ]
                            })

                        ]
                    }, {
                        title: '简答题',
                        loadMask: true,
                        group: null,
                        glyph: 0xf09d,
                        closable: false,
                        html: '开发中....'
                    }, {
                        title: '综合题',
                        loadMask: true,
                        group: null,
                        glyph: 0xf275,
                        closable: false,
                        html: '开发中....'
                    }]
                })]
            });
        }
        return win;
    }
});

Ext.define('Choice', {
    extend: 'Ext.data.Model',
    fields: ['title', 'a', 'b','c','d','e','f','g','h','answer']
});

var userStore = Ext.create('Ext.data.Store', {
    model: 'Choice',
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
