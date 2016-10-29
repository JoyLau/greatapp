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
    border: false,
    frame: true,
    region: 'center',
    viewConfig: {
        //启用数据复制
        // enableTextSelection: true
    },
    bodyStyle: 'background-image: url(' + basePath + '/static/images/desktop/body-bkg.png);',
    // plugins : Ext.create('Ext.ux.plugins.MaskBinder'),
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    emptyText : '<p style="text-align: center;"><img src=' +basePath + '"/static/images/desktop/gridnodata.png" draggable="false"/>哦哦,数据在火星</p>',
    // store: Ext.data.StoreManager.lookup('ChoiceStore'), //绑定Store
    initComponent : function() {
        var store = Ext.create('pcseSingleChoice.SingleChoiceStore');
        this.store = store;
        store.load({params : {start : 0,limit : 10}});
        this.dockedItems = [
            Ext.create('Ext.ux.plugins.ComboPage',{
                store: store,
                displayInfo: true,
                dock: 'bottom',
                plugins: [Ext.create('widget.ProgressBarPager',{
                    width : '70%'
                })]
        })];
        this.callParent(arguments);
    },

    tbar: [{
        text: '添加',
        glyph: 0xf055,
        id : 'addChoice',
        handler : addChoice
    }, '-', {
        text: '修改',
        glyph: 0xf14b,
        id : 'updateChoice',
        handler : updateChoice
    }, '-', {
        text: '删除',
        glyph: 0xf1f8,
        handler : deleteChoice
    }, '-', {
        text: '批量上传',
        glyph: 0xf0ee,
        id : 'importChoice',
        handler : importChoice
    }, '-', {
        text: '批量下载',
        glyph: 0xf0ed,
        handler : exportChoice,
        id : 'exportChoice'
    }, /*'-', {
        text: '来20题',
        tooltip : '输入题目数量，随机从题库中选出题目并导出',
        glyph: 0xf02c,
        handler : randomChoice
    },*/ '-', {
        text: '去重',
        tooltip : '筛选出题库中题目内容相同的选择题，您可以选择删除它们',
        glyph: 0xf0c5,
        handler : removeRepeatChoice
    }],
    columns: [{
        xtype: 'rownumberer'
    }, {
        text: '题目编号',
        hidden : true,
        dataIndex: 'id'
    }, {
        text: '题目',
        flex : 1,
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
        dataIndex: 'isImage',
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
    }, {
        text: '答案解析',
        dataIndex: 'meno',
        hidden: true
    }, {
        text: '更新时间',
        dataIndex: 'updateTime',
        hidden: true,
        renderer : function (val) {
            return Ext.Date.format(new Date(val), 'Y-m-d H:i:s');
        }
    }],
    listeners : {
        itemmouseenter: function (view, record) {
            var html = '<p><strong>' + record.data.title + '</strong></p>' +
                '<p><strong>A.&nbsp;</strong>' + record.data.answerA + '</p>' +
                '<p><strong>B.&nbsp;</strong>' + record.data.answerB + '</p>' +
                '<p><strong>C.&nbsp;</strong>' + record.data.answerC + '</p>' +
                '<p><strong>D.&nbsp;</strong>' + record.data.answerD + '</p>' +
                '<p><span style="color: rgb(155, 187, 89);">正确答案 : </span>' + getright(record.data.answerRight) + '</p>' +
                '<p><span style="color: rgb(35, 145, 187);">答案解析 : </span>' + record.data.meno + '</p>';
            Ext.getCmp('pcse-singleChoice-detail').update({html: html})
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