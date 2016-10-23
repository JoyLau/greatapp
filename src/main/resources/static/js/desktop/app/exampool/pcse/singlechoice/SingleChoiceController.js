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
        border : false,
        width : 600,
        height : 400,
        layout : 'anchor',
        draggable : true,
        modal : true,
        animateTarget : Ext.getBody(),
        items : [Ext.create('Ext.form.FormPanel',{
            anchor : '100% 100%',
            id : moduleId +'addChoiceForm',
            autoScroll : true,
            bodyStyle: 'background-image: url(' + basePath + 'static/images/desktop/body-bkg.png);padding:20px;',
            url : basePath + '/exampool/saveChoice',
            frame : false,
            buttonAlign : 'center',
            bodyPadding: 20,
            items :[{
                xtype : 'textarea',
                name : 'title',
                anchor : '90%',
                height : 80,
                allowBlank: false,
                fieldLabel : '题目'
            },{
                xtype: 'textfield',
                name: 'answerA',
                allowBlank: false,
                fieldLabel: '选项A'
            },{
                xtype: 'textfield',
                name: 'answerB',
                allowBlank: false,
                fieldLabel: '选项B'
            },{
                xtype: 'textfield',
                name: 'answerC',
                allowBlank: false,
                fieldLabel: '选项C'
            },{
                xtype: 'textfield',
                name: 'answerD',
                allowBlank: false,
                fieldLabel: '选项C'
            },{
                xtype: 'combo',
                editable: false,
                mode: 'local',
                allowBlank: false,
                fieldLabel: '题目类型',
                emptyText: '--请选择--',
                anchor: '60%',
                triggerAction: 'all',
                name: 'type',
                displayField: 'text',
                valueField: 'id',
                store: new Ext.data.SimpleStore({
                    fields: ['id', 'text'],
                    data: [['0', '行测'],
                        ['1', '申论']]
                })
            },{
                xtype: 'combo',
                editable: false,
                mode: 'local',
                allowBlank: false,
                fieldLabel: '有无图片',
                emptyText: '--请选择--',
                anchor: '60%',
                triggerAction: 'all',
                name: 'isImage',
                displayField: 'text',
                valueField: 'id',
                store: new Ext.data.SimpleStore({
                    fields: ['id', 'text'],
                    data: [['0', '无'],
                        ['1', '有']]
                })
            },{
                xtype: 'combo',
                editable: false,
                allowBlank: false,
                mode: 'local',
                fieldLabel: '正确答案',
                emptyText: '--请选择--',
                anchor: '60%',
                triggerAction: 'all',
                name: 'answerRight',
                displayField: 'text',
                valueField: 'id',
                store: new Ext.data.SimpleStore({
                    fields: ['id', 'text'],
                    data: [['0', 'A'],
                        ['1', 'B'],
                        ['2', 'C'],
                        ['3', 'D']]
                })
            }],
            buttons : [{
                formBind : true,
                text : '保存',
                handler : function() {
                    var form = Ext.getCmp(moduleId +'addChoiceForm');
                    if (!form.getForm().isValid()) {
                        return;
                    }
                    var data = form.form.getValues();
                    form.getForm().submit({
                        method : 'post',
                        waitTitle : '保存',
                        waitMsg : '正在保存...',
                        params : {
                            data : Ext.encode(data)
                        },
                        success : function(form, action) {
                            Ext.Msg.alert('提示',action.result.msg,function () {
                                addWin.close();
                            });
                            Ext.getCmp('pcse-singleChoice-grid').getStore().reload();
                        },
                        failure : function(form, action) {
                        }
                    });
                }
            }, {
                text : '重置',
                handler : function() {
                    Ext.getCmp(moduleId +'addChoiceForm').getForm().reset();
                }
            }]
        })]
    });
    addWin.show();
}