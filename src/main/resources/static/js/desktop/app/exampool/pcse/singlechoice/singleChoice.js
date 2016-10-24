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
        Ext.create('Ext.container.Viewport',{
            renderTo : Ext.getBody(),
            layout : 'border',
            frame : true,
            border : false,
            loadMask : {
                msg : '正在加载...'
            },
            items: [{xtype : 'PCSESingleChoiceForm'},{xtype : 'PESCSingleChoiceDetail'},{xtype : 'PCSESingleChoiceGrid'}]
        });
    }
});