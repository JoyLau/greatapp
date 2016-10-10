/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/
Ext.Loader.setConfig({enabled: true});
Ext.application({
    name:'examPoolChoice',
    appFolder: basePath + '/static/js/desktop/app/exampool/choice',
    controllers:[
        'examPoolChoice.ChoiceController'
    ],
    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [{
                xtype: 'ChoiceQueryForm'
            }, {
                xtype: 'ChoiceDetail'
            },{
                xtype: 'ChoiceGrid'
            }]
        });

        Ext.get('loading').hide();
        Ext.get('loading-mask').fadeOut({
            hidden: true
        });
    }
});