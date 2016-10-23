/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.Loader.setConfig({enabled: true});
Ext.application({
    name:'pcseSingleChoice',
    appFolder: basePath + '/static/js/desktop/app/exampool/pcse/singlechoice',
    controllers:[
        'pcseSingleChoice.SingleChoiceController'
    ],
    launch: function () {
        Ext.create('Ext.panel.Panel',{
            id : moduleId,
            width : Ext.getCmp(moduleId.replace('mainPanel','')).getWidth(),
            height : Ext.getCmp(moduleId.replace('mainPanel','')).getHeight(),
            layout: "border",
            renderTo : 'PCSESingleChoice',
            items: [{xtype : 'PCSESingleChoiceForm'},{xtype : 'PESCSingleChoiceDetail'},{xtype : 'PCSESingleChoiceGrid'}]
        });
    }
});