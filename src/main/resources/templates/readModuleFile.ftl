<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<#--<meta http-equiv="cache-control" content="private">-->
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>Desktop</title>
    <link rel="stylesheet" type="text/css" href="/static/js/desktop/resources/ext-theme-neptune/ext-theme-neptune-all.css" />
    <script type="text/javascript" src="/static/js/desktop/js/ext-all.js"></script>
    <script type="text/javascript" src="/static/js/desktop/js/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/desktop/js/ext-theme-neptune.js"></script>
</head>

<body oncontextmenu="return false;">
<div id="testDiv"></div>
</body>
</html>
<script type="text/javascript">
    var basePath = '${path}';
    var formPanel = Ext.create('Ext.form.FormPanel',{
        id : 'testform',
        anchor : '100% 100%',
        autoScroll : true,
        url : basePath + '/test/save',
        frame : false,
        buttonAlign : 'center',
        bodyPadding: 20,
        items :[{
            xtype:'fieldset',
            border: false,
            padding:'0 0 5 0',
            margin: '0 0 5',
            layout: 'column',
            items:[{
                xtype: 'textfield',
                name: 'field1_1',
                id: 'field1_1',
                allowBlank: false,
                fieldLabel: ''
            },{
                xtype: 'textfield',
                name: 'field1_2',
                id: 'field1_2',
                margin: '0 50',
                allowBlank: false,
                fieldLabel: ''
            }]
        },{
            xtype:'fieldset',
            border: false,
            padding:'0 0 5 0',
            margin: '0 0 5',
            layout: 'column',
            items:[{
                xtype: 'textfield',
                name: 'field2_1',
                id: 'field2_1',
                allowBlank: false,
                fieldLabel: ''
            },{
                xtype: 'textfield',
                margin: '0 50',
                name: 'field2_2',
                id: 'field2_2',
                allowBlank: false,
                fieldLabel: ''
            }]
        },{
            xtype:'fieldset',
            border: false,
            padding:'0 0 5 0',
            margin: '0 0 5',
            layout: 'column',
            items:[{
                xtype: 'textfield',
                name: 'field3_1',
                id: 'field3_1',
                allowBlank: false,
                fieldLabel: ''
            },{
                xtype: 'textfield',
                margin: '0 50',
                name: 'field3_2',
                id: 'field3_2',
                allowBlank: false,
                fieldLabel: ''
            }]
        },{
            xtype:'fieldset',
            border: false,
            padding:'0 0 5 0',
            margin: '0 0 5',
            layout: 'column',
            items:[{
                xtype: 'textfield',
                name: 'field4_1',
                id: 'field4_1',
                allowBlank: false,
                fieldLabel: ''
            },{
                xtype: 'textfield',
                margin: '0 50',
                name: 'field4_2',
                id: 'field4_2',
                allowBlank: false,
                fieldLabel: ''
            }]
        }],
        listeners : {
            beforerender : function () {
                Ext.Ajax.request({
                    method : 'post',
                    url : basePath + '/test/readProperty',
                    success : function(response) {
                        var json = Ext.JSON.decode(response.responseText);
                        if (json.success) {
                            Ext.getCmp('field1_1').setValue(json.properties.split(',')[0]);
                            Ext.getCmp('field2_1').setValue(json.properties.split(',')[1]);
                            Ext.getCmp('field3_1').setValue(json.properties.split(',')[2]);
                            Ext.getCmp('field4_1').setValue(json.properties.split(',')[3]);
                        }
                    }
                });

            }
        },
        buttons : [{
            formBind : true,
            text : '保存',
            handler : function() {
                var form = Ext.getCmp('testform');
                if (!form.getForm().isValid()) {
                    return;
                }
                form.getForm().submit({
                    method : 'post',
                    waitTitle : '保存',
                    waitMsg : '正在保存...',
                    success : function(form, action) {
                        Ext.Msg.alert('提示',action.result.msg,function () {

                        });
                    },
                    failure : function(form, action) {
                    }
                });
            }
        }, {
            text : '重置',
            handler : function() {
                Ext.getCmp('testform').getForm().reset();
            }
        }]
    });

    Ext.onReady(function () {
        Ext.create('Ext.panel.Panel',{
            width: 600,
            height: 400,
            renderTo: 'testDiv',
            collapsible: true,
            title : '更改表单项',
            layout: 'fit',
            items: [formPanel]
        })
    })
</script>