/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('examPoolChoice.ChoiceController', {
    extend: 'Ext.app.Controller',
    stores: ['examPoolChoice.ChoiceStore'],
    models: ['examPoolChoice.ChoiceModel'],
    views : ['examPoolChoice.ChoiceQueryForm','examPoolChoice.ChoiceDetail','examPoolChoice.ChoiceGrid'],
    //相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
    refs: [{
            ref: 'ChoiceGrid',
            selector: 'ChoiceGrid'
        }],
    init: function () {
        this.control({//这里的this相当于这个控制层
            'viewport > ChoiceGrid': {
                afterrender: function (gp) {
                    gp.down('button[id=addExamPool]').on('click', function () {
                        this.addExamPool()
                    }, this);
                    gp.down('button[id=updateExamPool]').on('click', function () {
                        this.updateExamPool()
                    }, this);
                    gp.down('button[id=deleteExamPool]').on('click', function () {
                        this.deleteExamPool()
                    }, this);
                }
            },
            //这样也可以监听grid的按钮点击事件
            'ChoiceGrid button[id=randomExamPool]':{
                click:function(){
                }
            }
        });
    },


    addExamPool: function () {
        Ext.create('Ext.window.Window',{
            id : 'addChoiceWin',
            title : '新增选择题',
            glyph: 0xf055,
            border : false,
            width : 600,
            height : 300,
            layout : 'anchor',
            draggable : true,
            modal : true,
            animateTarget : Ext.get('addExamPool'),
            items : [Ext.create('Ext.form.FormPanel',{
                anchor : '100% 100%',
                url : basePath + 'manage/customer_save.action',
                monitorValid : true,
                frame : false,
                labelAlign : 'right',
                buttonAlign : 'center',
                labelWidth : 100,
                items :[{

                }],
                buttons : [{
                    formBind : true,
                    text : '保存',
                    handler : function() {
                        if (!fp.getForm().isValid()) {
                            return;
                        }
                        var data = fp.form.getValues();
                        fp.getForm().submit({
                            method : 'post',
                            waitTitle : '保存',
                            waitMsg : '正在保存...',
                            params : {
                                data : Ext.encode(data)
                            },
                            success : function(form, action) {
                                Ext.example
                                    .msg('提示', action.result.msg);
                                Ext.getCmp('addWin').close();
                                if (cfg.callback) {
                                    cfg.callback();
                                }
                            },
                            failure : function(form, action) {
                                if (validJson(action.response.responseText)) {
                                    Ext.MessageBox.show({
                                        title : '提示',
                                        msg : action.result.msg,
                                        buttons : Ext.MessageBox.OK,
                                        icon : Ext.MessageBox.ERROR
                                    });
                                }
                            }
                        });
                    }
                }, {
                    text : '重置',
                    handler : function() {
                        fp.getForm().reset();
                    }
                }]
            })]
        }).show();
    },

    updateExamPool: function () {
    },

    deleteExamPool: function () {
    },

    randomExamPool: function () {
        this.getChoiceGrid().store.load();

    }
});