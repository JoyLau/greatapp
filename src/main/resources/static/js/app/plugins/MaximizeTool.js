/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/9/28.
 */
Ext.define('static.js.app.plugins.MaximizeTool', {
    extend: 'Ext.util.Observable',
    alias: 'plugin.maximize',
    init : function(ct) {
        var maximizeTool = {
            itemid : 'maximize',
            handler : this.handleMaximize,
            scope : ct,
            type : 'maximize',
            qtip : '最大化'
        };
        ct.tools = ct.tools || [];
        var newTools = ct.tools.slice();
        ct.tools = newTools;
        for (var i = 0, len = ct.tools.length; i < len; i++) {
            if (ct.tools[i].id == 'maximize')
                return;
        }
        ct.tools[ct.tools.length] = maximizeTool;
    },
//最大化处理函数
    handleMaximize : function(event, toolEl, owner) {
        var panel = owner.up('panel');
        panel.originalOwnerCt = panel.ownerCt;
        panel.originalPosition = panel.ownerCt.items.indexOf(panel);
        panel.originalSize = panel.getSize();

        if (!toolEl.window) {
            var defaultConfig = {
                itemid : (panel.getId() + '-MAX'),
                width : Ext.getBody().getSize(),
                height : Ext.getBody().getSize(),
                resizable : true,
                draggable : true,
                closable : true,
                closeAction : 'hide',
                hideBorders : true,
                plain : true,
                layout : 'fit',
                autoScroll : true,
                border : false,
                bodyBorder : false,
                frame : true,
                pinned : true,
                modal : true,
                title : "最大化",
                bodyStyle : 'background-color: #ffffff;'
            };
            toolEl.window = Ext.create('Ext.window.Window', defaultConfig);
//隐藏的时候绑定最小化处理函数
            toolEl.window.on('hide', handleMinimize, panel);
        }
        if (!panel.dummyComponent) {
            var dummyCompConfig = {
                title : panel.title,
                width : panel.getSize().width,
                height : panel.getSize().height,
                html : '&nbsp;'
            };
            panel.dummyComponent = new Ext.create('Ext.panel.Panel',
                dummyCompConfig);
        }

        panel.tools['maximize'].setVisible(false);
        toolEl.window.add(panel);
        panel.originalOwnerCt.insert(panel.originalPosition,
            panel.dummyComponent);
        panel.originalOwnerCt.doLayout();
        panel.dummyComponent.setSize(panel.originalSize);
        panel.dummyComponent.setVisible(true);
        toolEl.window.show(this);
        function handleMinimize(window) {
            this.dummyComponent.getEl().unmask();
            this.dummyComponent.setVisible(false);
            this.originalOwnerCt.insert(this.originalPosition, this);
            this.originalOwnerCt.doLayout();
            this.setSize(this.originalSize);
            this.tools['maximize'].setVisible(true);
        }
    },



});