/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('et.store.Menus',{
    extend: 'Ext.data.TreeStore',
    requires: 'et.model.Menu',
 	model: 'et.model.Menu',
 	autoLoad: true,
 	proxy: {
        type: 'ajax',
        url: '/static/js/app/common/main/data/manager.json',
        reader: {
            type: 'json',
            successProperty: 'success'
        }
    } 
});
