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