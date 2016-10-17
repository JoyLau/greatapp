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
    name:'examPoolChoice',
    appFolder: basePath + '/static/js/desktop/app/exampool/pcse',
    launch: function () {
        alert('asd');
        Ext.create('Ext.panel.Panel',{
            html: '1231231',
            renderTo : 'singleChoice'
        })
    }
});