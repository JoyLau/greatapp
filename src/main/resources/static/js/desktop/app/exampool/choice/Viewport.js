/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('examPoolChoice.Viewport',{
    extend: 'Ext.Viewport', 
    layout: 'fit', 
    hideBorders: true, 
    requires : [
        'examPoolChoice.ChoiceGrid',
        'examPoolChoice.ChoiceQueryForm'
    ],
    initComponent : function(){ 
        var me = this; 
        Ext.apply(me, {
            items: [{ 
                layout: 'border',
                items: [ 
                    Ext.create('examPoolChoice.ChoiceGrid'),
                    Ext.create('examPoolChoice.ChoiceQueryForm')
                ]
            }] 
        }); 
        me.callParent(arguments); 
    } 
});
