/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceController',{
    extend: 'Ext.app.Controller',
    stores: ['pcseSingleChoice.SingleChoiceStore'],
    models: ['pcseSingleChoice.SingleChoiceModel'],
    views : ['pcseSingleChoice.SingleChoiceForm','pcseSingleChoice.SingleChoiceDetail','pcseSingleChoice.SingleChoiceGrid'],
    init: function () {
        this.control({//这里的this相当于这个控制层
            'viewport > PCSESingleChoiceGrid': {
                afterrender: function (gp) {
                    /*gp.down('button[action=add]').on('click', function () {
                        alert('PCSESingleChoice')
                    }, this);
                    gp.down('button[id='+moduleId + 'update]').on('click', function () {
                        this.update()
                    }, this);
                    gp.down('button[id='+moduleId + 'delete]').on('click', function () {
                        this.delete()
                    }, this);*/
                }
            }
            //这样也可以监听grid的按钮点击事件
            /*'PCSESingleChoiceGrid button[action=add]':{
                click:function(){
                    alert('aa')
                }
            }*/
        });
    }
});

function addChoice() {
    var addWin = Ext.create('Ext.window.Window',{
        title : '新增选择题',
        glyph: 0xf055,
        border : true,
        width : 900,
        height : 400,
        layout : 'fit',
        maximizable : true,
        collapsible : true,
        draggable : true,
        modal : true,
        animateTarget : 'addChoice',
        html : '<iframe scrolling="auto" frameborder="0" width=100% height=100% src="'+basePath+'/exampool/pcse/toAddSingleChoice"></iframe>',
        listeners: {
            beforeclose :function (panel, opts) {
                Ext.getCmp('pcse-singleChoice-grid').getStore().reload();
            }
        }
    });
    addWin.show();
}



function updateChoice() {
    var record = Ext.getCmp('pcse-singleChoice-grid').getSelectionModel().getSelection();
    if (record.length == 1) {
        var id = record[0].get('id');
        var updateWin = Ext.create('Ext.window.Window',{
            title : '更新选择题',
            glyph: 0xf14b,
            border : true,
            width : 900,
            height : 400,
            layout : 'fit',
            maximizable : true,
            collapsible : true,
            draggable : true,
            modal : true,
            animateTarget : 'updateChoice',
            html : '<iframe scrolling="auto" frameborder="0" width=100% height=100% src="'+basePath+'/exampool/pcse/toUpdateSingleChoice?id=' + id +'"></iframe>',
            listeners: {
                beforeclose :function (panel, opts) {
                    Ext.getCmp('pcse-singleChoice-grid').getStore().reload();
                }
            }
        });
        updateWin.show();
    }else {
        Ext.Msg.alert('提示', '请选择一条记录修改!');
    }

}

function deleteChoice() {
    var record = Ext.getCmp('pcse-singleChoice-grid').getSelectionModel().getSelection();
    var ids = '';
    if (record.length >= 1) {
        for (var i = 0; i < record.length; i++) {
            ids = ids + record[i].get('id') + ',';
        }
    } else {
        Ext.Msg.alert('提示', '请至少选择一条记录');
        return;
    }
    Ext.Msg.confirm('提示', '确定要删除这' + record.length + '条记录?',
        function(btn) {
            if (btn == 'yes') {
                Ext.Ajax.request({
                    url : basePath + '/exampool/pcse/deleteSingleChoice',
                    method : 'post',
                    params : {
                        ids : ids.substring(0, ids.length - 1)
                    },
                    success : function(response) {
                        var json = Ext.JSON.decode(response.responseText);
                        Ext.Msg.alert('提示', json.msg);
                        Ext.getCmp('pcse-singleChoice-grid').getStore().reload();
                    }
                })
            }
        });

}
function exportChoice() {

}
function importChoice() {

}
function removeRepeatChoice() {
    Ext.getCmp('pcse-singleChoice-grid').getStore().getProxy().url = basePath + '/exampool/pcse/singleChoice/removeRepeatChoice';
    Ext.getCmp('pcse-singleChoice-grid').getStore().load({params : {start : 0,limit : 10}});
    Ext.getCmp('pcse-singleChoice-grid').getStore().getProxy().url = basePath + '/exampool/pcse/singleChoice/getStore';
    Ext.Msg.alert('提示', '已为您罗列出系统中题目重复的数据，您可以选择性的进行删除');
}
function randomChoice() {

}