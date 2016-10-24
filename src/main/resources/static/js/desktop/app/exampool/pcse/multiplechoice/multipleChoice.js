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
    name:'pcseMultipleChoice',
    appFolder: basePath + '/static/js/desktop/app/exampool/pcse/multiplechoice',
    controllers:[
        'pcseMultipleChoice.MultipleChoiceController'
    ],
    launch: function () {
        Ext.create('Ext.container.Viewport',{
            layout: "border",
            items: [Ext.create('pcseMultipleChoice.MultipleChoicePanel'),{
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
