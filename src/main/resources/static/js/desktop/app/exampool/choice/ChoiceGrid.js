/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/9.
 */
Ext.define('examPoolChoice.ChoiceGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.ChoiceGrid',
    requires: [
        basePath + '/static.js.app.plugins.ProgressBarPager',
        'examPoolChoice.ChoiceStore'
    ],
    border: false,
    stripeRows: true,
    frame: true,
    region: 'center',
    store: 'ChoiceStore', //绑定上面创建的Store
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    dockedItems: [{
        xtype: 'pagingtoolbar', //在Grid Panel中添加paging toolbar
        store: 'ChoiceStore', //把paging toolbar的Store设置成和Grid Panel的Store一样
        displayInfo: true,
        plugins: 'progressbarpager'
    }],
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
        dataIndex: 'a'
    }, {
        text: '选项B',
        width: '10%',
        dataIndex: 'b'
    }, {
        text: '选项C',
        width: '10%',
        dataIndex: 'c'
    }, {
        text: '选项D',
        width: '10%',
        dataIndex: 'd'
    }, {
        text: '选项E',
        hidden: true,
        dataIndex: 'e'
    }, {
        text: '选项F',
        hidden: true,
        dataIndex: 'f'
    }, {
        text: '选项G',
        hidden: true,
        dataIndex: 'g'
    }, {
        text: '选项H',
        hidden: true,
        dataIndex: 'h'
    }, {
        text: '正确答案',
        width: '10%',
        dataIndex: 'answer'
    }]
});