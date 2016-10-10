/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/9.
 */
/**
 * queryForm
 */
Ext.define('examPoolChoice.ChoiceQueryForm', {
    extend: 'Ext.form.Panel',
    alias : 'widget.ChoiceQueryForm',
    title: '题目查询',
    id: 'exampool-choice-form',
    frame: true,
    region: 'north',
    labelAlign: 'right',
    collapsible: true,
    height: 200,
    border: false,
    bodyPadding: 20,
    items:[{
        autoHeight:true,
        layout:'column',
        border:false,
        items: [{
            columnWidth:.33,
            xtype: 'fieldset',
            title: '时间',
            layout:'form',
            defaults: {anchor: '95%',labelWidth : 60},
            style: 'margin-left: 5px;padding-left: 5px;',
            items:[{
                    name: 'updateTimeStart',
                    xtype: 'datefield',
                    fieldLabel: '添加时间',
                    format: 'Y-m-d',
                    showToday: true,
                    editable: false
                },{
                name: 'updateTimeStart',
                xtype: 'datefield',
                fieldLabel: '至',
                format: 'Y-m-d',
                showToday: true,
                editable: false
            }]
        },
            {
                columnWidth:.33,
                xtype: 'fieldset',
                title: '类型',
                layout:'form',
                defaults: {anchor: '95%',labelWidth : 60},
                style: 'margin-left: 5px;padding-left: 5px;',
                items:[{
                    xtype: 'combo',
                    editable: true,
                    mode: 'local',
                    fieldLabel: '题目类型',
                    emptyText: '--请选择--',
                    anchor: '60%',
                    triggerAction: 'all',
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
                },{
                    xtype: 'combo',
                    editable: true,
                    mode: 'local',
                    fieldLabel: '包含图片',
                    emptyText: '--请选择--',
                    anchor: '60%',
                    triggerAction: 'all',
                    hiddenName: 'state',
                    displayField: 'text',
                    valueField: 'id',
                    store: new Ext.data.SimpleStore({
                        fields: ['id', 'text'],
                        data: [['0', '不包含'],
                            ['1', '包含']]
                    })
                }]
            },
            {
                columnWidth:.33,
                xtype: 'fieldset',
                title: '题目信息',
                layout:'form',
                defaults: {anchor: '95%',labelWidth : 60},
                style: 'margin-left: 5px;padding-left: 5px;',
                items:[{
                    xtype: 'textfield',
                    emptyText : '输入题目关键字进行模糊查询',
                    name: 'name',
                    fieldLabel: '题目名称'
                },{
                    xtype: 'combo',
                    editable: true,
                    mode: 'local',
                    fieldLabel: '正确答案',
                    emptyText: '--请选择--',
                    triggerAction: 'all',
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
            }
        ]
    }
    ],
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