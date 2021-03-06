/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('et.view.Header', {
    extend: 'Ext.toolbar.Toolbar',
    border: true,
    id: 'headerBar',
    autoHeight: true,
    region: 'north',
    // style: 'background:#f5f5f5;',
    uses : [
        'static.js.app.utils.ButtonTransparent'
    ],
    items: ['&nbsp;&nbsp;&nbsp;', {
        id: 'top-user',
        glyph: 0xf007,
        xtype : 'buttontransparent',
        text: '管理员',
        handler: function () {

        }
    }, '->', {
        xtype: 'tbtext',
        text: Ext.Date.format(new Date(), 'Y-m-d H:i:s'),
        listeners: {
            render: function (cmp) {
                Ext.TaskManager.start({
                    run: function () {
                        cmp.setText(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
                    },
                    interval: 1000
                });
            }
        }
    },
        '-', {
            xtype: 'textfield',
            name: 'searchField',
            emptyText: '输入您的搜索关键词'
        }, {
            text: '搜索',
            glyph: 0xf00e,
            id: 'header-search',
            handler: function () {
            }
        }, '-', {
            text: '关于',
            glyph: 0xf06a,
            id: 'header-about',
            handler: function () {
            }
        }, '-', {
            text: '帮助',
            glyph: 0xf059,
            id: 'header-help',
            handler: function () {
            }
        }, '-', {
            text: '退出',
            glyph: 0xf011,
            id: 'header-logout',
            handler: function () {
                window.location.href = 'logout';
            }
        }]
}); 
