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
    xtype : 'ChoiceQueryForm',
    title: '题目查询',
    id: 'exampool-choice-form',
    frame: true,
    region: 'north',
    labelAlign: 'right',
    collapsible: true,
    height: '25%',
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