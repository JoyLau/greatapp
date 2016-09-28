/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('et.view.TabPanel', {
    extend: 'Ext.tab.Panel',
    xtype: 'plain-tabs',
    initComponent: function () {
        Ext.apply(this, {
            id: 'content-panel',
            region: 'center',
            plain: true,
            defaults: {
                bodyPadding: 10,
                autoScroll: false
            },
            minTabWidth: 80,
            items: [{
                id: 'Home',
                title: '首页',
                glyph: 0xf015,
                loadMask: false,
                group: null,
                closable: false,
                autoLoad: {url: 'pages/welcome.jsp', callback: this.initSearch, scope: this}
//            html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="pages/welcome.jsp"></iframe>'
            },{
                title: '首页2',
                loadMask: false,
                group: null,
                closable: true,
                autoLoad: {url: 'pages/welcome.jsp', callback: this.initSearch, scope: this},
                html: 'asdasd'
            }]
        });
        this.callParent(arguments);
    }
}); 
