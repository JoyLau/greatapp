<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>Add-PCSESingleChoice</title>
</head>
<body oncontextmenu="return false;">
<div id="loading-mask"></div>
<div id="loading">
    <div class="loading-indicator">
        <img src="${path}/static/images/desktop/desktoploading.gif">
    </div>
</div>
</body>
</html>
<#include "../../common/common.ftl" />
<script type="text/javascript" src="${path}/static/js/app/plugins/UEditor.js"></script>
<script type="text/javascript" src="${path}/static/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path}/static/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${path}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    Ext.onReady(function() {
        Ext.create('Ext.container.Viewport',{
            layout: "border",
            items: [Ext.create('Ext.form.FormPanel',{
                anchor : '100% 100%',
                id : 'addChoiceForm',
                region: 'center',
                autoScroll : true,
                bodyStyle: 'background-image: url(' + basePath + '/static/images/desktop/body-bkg.png);padding:20px;',
                url : basePath + '/exampool/pcse/saveAddSingleChoice',
                frame : false,
                buttonAlign : 'center',
                bodyPadding: 20,
                items :[{
                    fieldLabel: '题目',
                    name: "title",
                    xtype: 'ueditor',
                    anchor: '-20',
                    height: 150,
                    ueditorConfig: {
                    }
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
                },{
                    xtype : 'textarea',
                    name : 'meno',
                    anchor : '90%',
                    height : 80,
                    allowBlank: true,
                    fieldLabel : '答案解析'
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
                                });
                            },
                            failure : function(form, action) {
                            }
                        });
                    }
                }]
            })]
        });

    })
</script>