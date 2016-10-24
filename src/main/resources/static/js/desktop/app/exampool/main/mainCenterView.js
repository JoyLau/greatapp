/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/18.
 * static.js.desktop.app.exampool.main
 * DevelopmentApp
 */
Ext.define('examPoolMain.mainCenterView',{
    extend: 'Ext.tab.Panel',
    id :'examPoolCenterTabPanel',
    alias : 'widget.examPoolCenterTabPanel',
    resizeTabs: true,
    enableTabScroll: true,
    activeTab: 0,
    autoDestroy: true,
    region: "center",
    collapsible: false,
    plain: true,
    defaults: {
        autoScroll: false
    },
    minTabWidth: 150,
    items :[{
        id : 'examPoolCenterTabPanel-MainPanel',
        title: '工作台',
        glyph: 0xf015,
        loadMask: true,
        closable: false,
        bodyStyle: 'background-image: url(' + basePath + 'static/images/desktop/body-bkg.png);',
        html: '工作台写点什么比较好呢？先想想吧.............'
    }],
    initComponent: function () {
        Ext.apply(this, {
            plugins : Ext.create('Ext.ux.plugins.TabCloseMenu')
        });
        this.callParent(arguments);
    }
});