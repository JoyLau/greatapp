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
            height : 400,
            layout : 'anchor',
            draggable : true,
            modal : true,
            animateTarget : Ext.get('addExamPool'),
            items : [Ext.create('Ext.form.FormPanel',{
                anchor : '100% 100%',
                id : 'addChoiceForm',
                autoScroll : true,
                bodyStyle: {
                    background: '#eaf3fa',
                    padding: '10px'
                },
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
                    name: 'answer_a',
                    allowBlank: false,
                    fieldLabel: '选项A'
                },{
                    xtype: 'textfield',
                    name: 'answer_b',
                    allowBlank: false,
                    fieldLabel: '选项B'
                },{
                    xtype: 'textfield',
                    name: 'answer_c',
                    allowBlank: false,
                    fieldLabel: '选项C'
                },{
                    xtype:'fieldset',
                    border: false,
                    padding:'0 0 0 0',
                    margin: '0 0 5',
                    layout: 'column',
                    items:[{
                        xtype: 'textfield',
                        width : 255,
                        name: 'answer_d',
                        allowBlank: false,
                        fieldLabel: '选项D'
                    },{
                            xtype: 'button',
                            glyph: 0xf067,
                            id: 'addbutton',
                            margin: '0 20',
                            handler: function() {
                                Ext.getCmp("answer_e").setVisible(true);
                                Ext.getCmp("answer_f").setVisible(true);
                                Ext.getCmp("answer_g").setVisible(true);
                                Ext.getCmp("answer_h").setVisible(true);
                            }
                        }]
                },{
                    xtype:'fieldset',
                    border: false,
                    id : 'answer_e',
                    padding:'0 0 0 0',
                    layout: 'column',
                    margin: '0 0 5',
                    hidden : true,
                    items:[{
                        xtype: 'textfield',
                        width : 255,
                        name: 'answer_e',
                        fieldLabel: '选项E'
                    },{
                        xtype: 'button',
                        glyph: 0xf068,
                        margin: '0 20',
                        handler: function() {
                            Ext.getCmp('addChoiceForm').getForm().findField('answer_e').setValue('');
                            //移除组件
                            // addChoiceForm.remove('answer_e');
                            Ext.getCmp("answer_e").setVisible(false);
                        }
                    }]
                },{
                    xtype:'fieldset',
                    border: false,
                    id : 'answer_f',
                    padding:'0 0 0 0',
                    margin: '0 0 5',
                    layout: 'column',
                    hidden : true,
                    items:[{
                        xtype: 'textfield',
                        width : 255,
                        name: 'answer_f',
                        fieldLabel: '选项F'
                    },{
                        xtype: 'button',
                        glyph: 0xf068,
                        margin: '0 20',
                        handler: function() {
                            Ext.getCmp('addChoiceForm').getForm().findField('answer_f').setValue('');
                            Ext.getCmp("answer_f").setVisible(false);
                        }
                    }]
                },{
                    xtype:'fieldset',
                    id : 'answer_g',
                    border: false,
                    padding:'0 0 0 0',
                    margin: '0 0 5',
                    layout: 'column',
                    hidden : true,
                    items:[{
                        xtype: 'textfield',
                        width : 255,
                        name: 'answer_g',
                        fieldLabel: '选项G'
                    },{
                        xtype: 'button',
                        glyph: 0xf068,
                        margin: '0 20',
                        handler: function() {
                            Ext.getCmp('addChoiceForm').getForm().findField('answer_g').setValue('');
                            Ext.getCmp("answer_g").setVisible(false);
                        }
                    }]
                },{
                    xtype:'fieldset',
                    border: false,
                    padding:'0 0 0 0',
                    margin: '0 0 5',
                    id : 'answer_h',
                    layout: 'column',
                    hidden : true,
                    items:[{
                        xtype: 'textfield',
                        width : 255,
                        name: 'answer_h',
                        fieldLabel: '选项H'
                    },{
                        xtype: 'button',
                        glyph: 0xf068,
                        margin: '0 20',
                        handler: function() {
                            Ext.getCmp('addChoiceForm').getForm().findField('answer_h').setValue('');
                            Ext.getCmp("answer_h").setVisible(false);
                        }
                    }]
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
                        data: [['0', '公务员试题'],
                            ['1', '驾考试题'],
                            ['2', '自考试题'],
                            ['3', '其他分类']]
                    })
                },{
                    xtype:'fieldset',
                    border: false,
                    padding:'0 0 0 0',
                    margin: '0 0 5',
                    layout: 'column',
                    items:[{
                        xtype: 'combo',
                        editable: false,
                        mode: 'local',
                        width : 255,
                        allowBlank: false,
                        fieldLabel: '有无图片',
                        emptyText: '--请选择--',
                        triggerAction: 'all',
                        name: 'is_image',
                        displayField: 'text',
                        valueField: 'id',
                        store: new Ext.data.SimpleStore({
                            fields: ['id', 'text'],
                            data: [['0', '无'],
                                ['1', '有']]
                        }),
                        listeners:{
                            select:function(combo,record,index){
                                var value = combo.getValue();
                                if(value==1){
                                    Ext.getCmp("uploadButton").enable();
                                }else{
                                    Ext.getCmp("uploadButton").disable();
                                }
                            }
                        }
                    },{
                        xtype: 'button',
                        text: '上传图片',
                        margin: '0 20',
                        disabled : true,
                        id : 'uploadButton',
                        handler: function (btn) {
                            Ext.Loader.setPath({
                                'Ext.ux.plugins': basePath + '/static/js/app/plugins'
                            });
                            Ext.require(['Ext.ux.plugins.UploadPanel']);
                            Ext.onReady(function () {
                                    Ext.create('Ext.window.Window', {
                                        title: '上传图片',
                                        id : 'prentWin',
                                        autoScroll: true,
                                        border: false,
                                        layout: 'fit',
                                        modal : true,
                                        animateTarget : Ext.get('uploadButton'),
                                        width: 700,
                                        height: 360,
                                        closable : true,
                                        items : [
                                            Ext.create('Ext.ux.plugins.UploadPanel',{
                                                prentWinId : 'prentWin'
                                            })
                                        ]
                                    }).show();
                                }
                            )
                        }
                    }]
                },{
                    xtype: 'combo',
                    editable: false,
                    allowBlank: false,
                    mode: 'local',
                    fieldLabel: '正确答案',
                    emptyText: '--请选择--',
                    anchor: '60%',
                    triggerAction: 'all',
                    name: 'answer_right',
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
                }],
                buttons : [{
                    formBind : true,
                    text : '保存',
                    handler : function() {
                        var form = Ext.getCmp('addChoiceForm');
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
                                    Ext.getCmp('addChoiceWin').close();
                                    Ext.getCmp('exampool-choice-grid').getStore().reload();
                                });
                            },
                            failure : function(form, action) {
                                /*if (validJson(action.response.responseText)) {
                                    Ext.MessageBox.show({
                                        title : '提示',
                                        msg : action.result.msg,
                                        buttons : Ext.MessageBox.OK,
                                        icon : Ext.MessageBox.ERROR
                                    });
                                }*/
                            }
                        });
                    }
                }, {
                    text : '重置',
                    handler : function() {
                        Ext.getCmp('addChoiceForm').getForm().reset();
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