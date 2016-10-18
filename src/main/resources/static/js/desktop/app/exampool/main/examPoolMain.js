/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.main
 * greatapp
 */
Ext.Loader.setConfig({enabled: true});
Ext.application({
    name:'examPoolMain',
    appFolder: basePath + '/static/js/desktop/app/exampool/main',
    controllers:['examPoolMain.mainController'],
    launch: function () {
        Ext.create('Ext.panel.Panel',{
            id :'examPoolMainPanel',
            height: '100%',
            layout: "border",
            renderTo : 'examPoolMain',
            items: [{xtype : 'examPoolWestView'},{xtype : 'examPoolCenterTabPanel'}]

        })
    }
});