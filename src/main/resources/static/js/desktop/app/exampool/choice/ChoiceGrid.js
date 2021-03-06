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
        id: 'addExamPool',
        text: '添加',
        glyph: 0xf055
    }, '-', {
        id: 'updateExamPool',
        text: '修改',
        glyph: 0xf14b
    }, '-', {
        id: 'deleteExamPool',
        text: '删除',
        glyph: 0xf1f8
    }, '-', {
        id: 'importExamPool',
        text: '导入文件',
        glyph: 0xf0ed
    }, '-', {
        id: 'exportExamPool',
        text: '导出题目',
        glyph: 0xf0ee
    }, '-', {
        id: 'randomExamPool',
        text: '来20题',
        glyph: 0xf02c
    }],
    columns: [{
        xtype: 'rownumberer'
    }, {
        text: '题目编号',
        hidden : true,
        dataIndex: 'id'
    }, {
        text: '题目',
        width: '50%',
        sortable: false,
        hideable: false,
        dataIndex: 'title'
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
        text: '题目类型',
        hidden: true,
        dataIndex: 'type',
        renderer : function (val) {
            switch (val) {
                case 0 : return "公务员试题"; break;
                case 1 : return "驾考试题"; break;
                case 2 : return "自考试题"; break;
                case 3 : return "其他分类"; break;
                default : return '未知类型';
            }
        }
    }, {
        text: '有无图片',
        hidden: true,
        dataIndex: 'is_image',
        renderer : function (val) {
            switch (val) {
                case 0 : return "无"; break;
                case 1 : return "有"; break;
                default : return '未知类型';
            }
        }
    }, {
        text: '正确答案',
        dataIndex: 'answer_right',
        renderer : function (val) {
            return getright(val);
        }
    }],
    listeners : {
        itemmouseenter: function (view, record) {
            var html = '<p><strong>' + record.data.title + '</strong></p>' +
                '<p>' + record.data.answer_a + '</p>' +
                '<p>' + record.data.answer_b + '</p>' +
                '<p>' + record.data.answer_c + '</p>' +
                '<p>' + record.data.answer_d + '</p>' +
                '<p>' + record.data.answer_e + '</p>' +
                '<p>' + record.data.answer_f + '</p>' +
                '<p>' + record.data.answer_g + '</p>' +
                '<p>' + record.data.answer_h + '</p>' +
                '<p><span style="color: rgb(155, 187, 89);">正确答案 : </span>' + getright(record.data.answer_right) + '</p>';
            Ext.getCmp('exampool-choice-detail').update({html: html})
        }
    }
});

function getright(val) {
    switch (val) {
        case 0 : return "A"; break;
        case 1 : return "B"; break;
        case 2 : return "C"; break;
        case 3 : return "D"; break;
        case 4 : return "E"; break;
        case 5 : return "F"; break;
        case 6 : return "G"; break;
        case 7 : return "H"; break;
        default : return '<span style="color: red; ">没有填写答案</span>';
    }
}