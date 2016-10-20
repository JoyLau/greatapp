/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.PCSESingleChoiceGrid',
    id: 'pcse-singleChoice-grid',
    xtype : 'grid',
    border: false,
    frame: true,
    region: 'center',
    viewConfig:{
        loadMask:{
            msg:'loading...'
        }
    },
    selModel: Ext.create('Ext.selection.CheckboxModel'),

    // store: Ext.data.StoreManager.lookup('ChoiceStore'), //绑定Store
    initComponent : function() {
        var store = 'pcseSingleChoice.SingleChoiceStore';
        this.store = store;
        // store.load({params : {start : 0,limit : 10}});
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
        tooltip : '输入题目数量，随机从题库中选出题目并导出',
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
        dataIndex: 'answerA'
    }, {
        text: '选项B',
        dataIndex: 'answerB'
    }, {
        text: '选项C',
        dataIndex: 'answerC'
    }, {
        text: '选项D',
        dataIndex: 'answerD'
    }, {
        text: '题目类型',
        hidden: true,
        dataIndex: 'type',
        renderer : function (val) {
            switch (val) {
                case 0 : return "行测"; break;
                case 1 : return "申论"; break;
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
        dataIndex: 'answerRight',
        renderer : function (val) {
            return getright(val);
        }
    }],
    listeners : {
        itemmouseenter: function (view, record) {
            var html = '<p><strong>' + record.data.title + '</strong></p>' +
                '<p><strong>A.&nbsp;</strong>' + record.data.answerA + '</p>' +
                '<p><strong>B.&nbsp;</strong>' + record.data.answerB + '</p>' +
                '<p><strong>C.&nbsp;</strong>' + record.data.answerC + '</p>' +
                '<p><strong>D.&nbsp;</strong>' + record.data.answerD + '</p>' +
                '<p><span style="color: rgb(155, 187, 89);">正确答案 : </span>' + getright(record.data.answerRight) + '</p>';
            Ext.getCmp('pcse-singleChoice-detail').update({html: html})
        },
    afterrender : function (cmp, opts) {
        cmp.store.load({params : {start : 0,limit : 10}});
    }
    }
});

function getright(val) {
    switch (val) {
        case 0 : return "A"; break;
        case 1 : return "B"; break;
        case 2 : return "C"; break;
        case 3 : return "D"; break;
        default : return '<span style="color: red; ">没有填写答案</span>';
    }
}