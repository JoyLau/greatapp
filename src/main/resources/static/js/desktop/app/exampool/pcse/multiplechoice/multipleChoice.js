/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.pcse.multiplechoice
 * DevelopmentApp
 */
Ext.Loader.setConfig({enabled: true});
Ext.application({
    name:'PCSEMultiplechoice',
    appFolder: basePath + '/static/js/desktop/app/exampool/pcse/multiplechoice',
    launch: function () {
        Ext.create('Ext.panel.Panel',{
            id : mainPanelId,
            width : Ext.getCmp(mainPanelId.replace('mainPanel','')).getWidth(),
            height : Ext.getCmp(mainPanelId.replace('mainPanel','')).getHeight(),
            layout: "border",
            renderTo : 'PCSEMultipleChoice',
            items: [{
                xtype : 'panel',
                region: 'north',
                split: true,
                collapsible: true,
                title : 'north'
            },{
                xtype : 'panel',
                region: 'west',
                collapsible: true,
                split: true,
                html : '小儿麻小二郎......'
            },{
                xtype : 'panel',
                collapsible: true,
                region: 'center'
            }]
        });
    }
});