/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('et.view.South',{
    extend: 'Ext.Toolbar', 
    initComponent : function(){ 
        Ext.apply(this,{ 
            id:"bottom", 
            frame:false,
            region:"south",
            style : 'background:#f5f5f5;',
            // height:20,
            items:['->',"<span style='color: #3892d3;-webkit-user-select:none; -moz-user-select:none; -ms-user-select:none;user-select:none;'>Copyright (c) 2016 by LiuFa. All rights reserved</span>",'->']
        }); 
        this.callParent(arguments); 
    } 
}); 
