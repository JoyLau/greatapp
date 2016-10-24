/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceForm', {
    extend: 'Ext.form.Panel',
    alias : 'widget.PCSESingleChoiceForm',
    title: '题目查询',
    glyph: 0xf00e,
    id: 'exampool-choice-form',
    frame: true,
    region: 'north',
    labelAlign: 'right',
    collapsible: true,
    height: 200,
    bodyStyle: 'background-image: url(' + basePath + '/static/images/desktop/body-bkg.png);',
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
                fieldLabel: '编辑时间',
                format: 'Y-m-d',
                showToday: true,
                editable: false
            },{
                name: 'updateTimeEnd',
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
                    editable: false,
                    mode: 'local',
                    fieldLabel: '题目类型',
                    emptyText: '--请选择--',
                    anchor: '60%',
                    triggerAction: 'all',
                    hiddenName: 'state',
                    displayField: 'text',
                    valueField: 'id',
                    name : 'type',
                    store: new Ext.data.SimpleStore({
                        fields: ['id', 'text'],
                        data: [['0', '行测'],
                            ['1', '申论']]
                    })
                },{
                    xtype: 'combo',
                    editable: false,
                    mode: 'local',
                    fieldLabel: '包含图片',
                    emptyText: '--请选择--',
                    anchor: '60%',
                    triggerAction: 'all',
                    hiddenName: 'state',
                    displayField: 'text',
                    valueField: 'id',
                    name : 'isImage',
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
                    name: 'title',
                    fieldLabel: '题目名称'
                },{
                    xtype: 'combo',
                    editable: false,
                    mode: 'local',
                    fieldLabel: '正确答案',
                    emptyText: '--请选择--',
                    triggerAction: 'all',
                    hiddenName: 'state',
                    displayField: 'text',
                    valueField: 'id',
                    name : 'answerRight',
                    store: new Ext.data.SimpleStore({
                        fields: ['id', 'text'],
                        data: [['0', 'A'],
                            ['1', 'B'],
                            ['2', 'C'],
                            ['3', 'D']]
                    })
                }]
            }
        ]
    }
    ],
    keys: [{
        key: Ext.EventObject.ENTER,
        fn: function () {
            Ext.getCmp('pcse-singleChoice-grid').getStore().reload()
        }
    }],
    buttons: [{
        text: '查询',
        glyph: 0xf002,
        handler: function () {
            Ext.getCmp('pcse-singleChoice-grid').getStore().reload()
        }
    }, {
        text: '清空条件',
        glyph: 0xf056,
        handler: function () {
            Ext.getCmp('exampool-choice-form').getForm().reset();
        },
        scope: this
    }]
});