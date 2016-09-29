/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('MyDesktop.DevTask', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'dev-task',

    init : function(){
        this.launcher = {
            text: '网格窗口',
            iconCls:'icon-grid'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('dev-task');
        if(!win){
            win = desktop.createWindow({
                id: 'dev-task',
                title:'开发任务',
                width:740,
                height:480,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        border: false,
                        xtype: 'grid',
                        store: new Ext.data.ArrayStore({
                            fields: [
                               { name: 'task' },
                               { name: 'username' },
                               { name: 'time' },
                               { name: 'state'}
                            ],
                            data: MyDesktop.DevTask.getDummyData()
                        }),
                        columns: [
                            new Ext.grid.RowNumberer(),
                            {
                                text: "任务",
                                flex: 1,
                                sortable: true,
                                dataIndex: 'task'
                            },
                            {
                                text: "添加人",
                                width: 70,
                                sortable: true,
                                dataIndex: 'username'
                            },
                            {
                                text: "添加时间",
                                width: 100,
                                sortable: true,
                                dataIndex: 'time'
                            },
                            {
                                text: "状态",
                                width: 70,
                                sortable: true,
                                dataIndex: 'state',
                                renderer : function(v, c, r) {
                                    if (v == 0) {
                                        return '<span style="color:red;">未处理</span>';
                                    } else if (v == 1) {
                                        return '正在处理';
                                    }else {
                                        return '已处理';
                                    }
                                }
                            }
                        ]
                    }
                ],
                tbar:[{
                    text:'添加任务',
                    tooltip:'点击添加一个任务',
                    iconCls:'add'
                }, '-', {
                    text:'标记已解决',
                    tooltip:'点击标记以解决',
                    iconCls:'option'
                },'-',{
                    text:'移除',
                    tooltip:'点击移除',
                    iconCls:'remove'
                }]
            });
        }
        return win;
    },

    statics: {
        getDummyData: function () {
            return [
                ['锁定、关机菜单的按钮字体居中','刘法','2016-9-30 00:30:51',1],
                ['点击时间区域显示日期控件','刘法','2016-9-30 00:41:23',1]
            ];
        }
    }
});

