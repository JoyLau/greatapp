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
    requires : ['Ext.ux.plugins.TabCloseMenu'],
    resizeTabs: true,
    enableTabScroll: true,
    activeTab: 0,
    autoDestroy: true,
    region: "center",
    collapsible: false,
    plain: true,
    defaults: {
        autoScroll: true
    },
    minTabWidth: 150,
    plugins : Ext.create('Ext.ux.plugins.TabCloseMenu'),
    items :[{
        title: '首页',
        glyph: 0xf015,
        loadMask: true,
        closable: false,
        html: '首页写点什么比较好呢？先想想吧.............'
    }]
});