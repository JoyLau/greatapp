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
        autoScroll: true
    },
    minTabWidth: 150,
    items :[{
        title: '首页',
        glyph: 0xf015,
        loadMask: true,
        group: null,
        closable: false,
        // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
    },{
        title: '首页',
        glyph: 0xf015,
        loadMask: true,
        group: null,
        closable: false,
        // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
    }]
});