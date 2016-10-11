/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/9.
 */
Ext.define('examPoolChoice.ChoiceGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.ChoiceGrid',
    id: 'exampool-choice-grid',
    requires: [
        // basePath + '.static.js.app.plugins.ProgressBarPager'
    ],
    border: false,
    stripeRows: true,
    frame: true,
    region: 'center',
    selModel: Ext.create('Ext.selection.CheckboxModel'),

    // store: Ext.data.StoreManager.lookup('ChoiceStore'), //绑定Store
    initComponent : function() {
        var store = Ext.create('examPoolChoice.ChoiceStore');
        Ext.data.StoreManager.lookup('ChoiceStore');
        this.store = store;
        store.load({params : {start : 0,limit : 10}});
        this.dockedItems = [{
            xtype: 'pagingtoolbar',
            store: store,
            displayInfo: true,
            dock: 'bottom',
            plugins: Ext.create('widget.ProgressBarPager',{
                width : '70%'
            })
        }];
        this.callParent(arguments);
    },

    /*dockedItems: [{
        xtype: 'pagingtoolbar', //在Grid Panel中添加paging toolbar
        store: Ext.data.StoreManager.lookup('ChoiceStore'), //把paging toolbar的Store设置成和Grid Panel的Store一样
        displayInfo: true,
        dock: 'bottom',
         // plugins: 'progressbarpager'
    }],*/
    tbar: [{
        text: '添加',
        id: 'addCustomer',
        glyph: 0xf055
    }, '-', {
        text: '修改',
        id: 'updateCustomer',
        glyph: 0xf14b
    }, '-', {
        text: '删除',
        glyph: 0xf1f8
    }, '-', {
        text: '导入文件',
        glyph: 0xf0ed
    }, '-', {
        text: '导出题目',
        glyph: 0xf0ee
    }, '-', {
        text: '来20题',
        glyph: 0xf02c
    }],
    columns: [{
        xtype: 'rownumberer'
    }, {
        text: '题目',
        width: '50%',
        sortable: false,
        hideable: false,
        dataIndex: 'title' //Grid Panel中显示的字段，必须要和User Model中的字段一致
    }, {
        text: '选项A',
        width: '10%',
        dataIndex: 'answer_a'
    }, {
        text: '选项B',
        width: '10%',
        dataIndex: 'answer_b'
    }, {
        text: '选项C',
        width: '10%',
        dataIndex: 'answer_c'
    }, {
        text: '选项D',
        width: '10%',
        dataIndex: 'answer_d'
    }, {
        text: '选项E',
        hidden: true,
        dataIndex: 'answer_e'
    }, {
        text: '选项F',
        hidden: true,
        dataIndex: 'answer_f'
    }, {
        text: '选项G',
        hidden: true,
        dataIndex: 'answer_g'
    }, {
        text: '选项H',
        hidden: true,
        dataIndex: 'answer_h'
    }, {
        text: '正确答案',
        width: '10%',
        dataIndex: 'answer_right'
    }]
});
