Ext.define('et.view.Menu', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.sxptmenu',
    initComponent: function () {
        Ext.setGlyphFontFamily('FontAwesome');
        Ext.apply(this, {
            id: 'menu-panel',
            region: 'west',
            width: 200,
            minWidth: 150,
            maxWidth: 300,
            //侧边框点击收起
            split: true,
            collapsible: true,
            //折叠时，点击边框也可以展开
            floatable: false,
            title: '导航',
            layout: {
                type: 'accordion',
                animate: true, //点击的时候有动画动作
                activeOnTop: true
                //设置是否允许通过单击子面板的标题来展开或收缩面板，默认为true。
                // titleCollapse: true,
                // enableSplitters: true,
                //是否隐藏子面板的“展开收缩”按钮，默认为false。
                // hideCollapseTool: true
            },
            tools: [
                {
                    type: 'expand',
                    tooltip: '展开',
                    handler: function (event, toolEl, panel) {
                        // 实现逻辑
                    }
                },
                {
                    type: 'collapse',
                    tooltip: '折叠',
                    handler: function (event, toolEl, panel) {
                        // 实现逻辑
                    }
                },
                {
                    type: 'refresh',
                    tooltip: '刷新',
                    handler: function (event, toolEl, panel) {
                        // 实现逻辑
                    }
                }
            ],
            glyph: 0xf0c9,
            //内边距
            bodyStyle: 'padding:0',
            items: [{
                title: 'Panel 1',
                items:[{
                    text: 'Panel 1',
                    xtype : 'button'
                },{
                    text: 'Panel 1',
                    xtype : 'button'
                },{
                    text: 'Panel 1',
                    xtype : 'button'
                },{
                    text: 'Panel 1',
                    xtype : 'button'
                }]
            }, {
                title: 'Panel 2',
                html: 'Panel content!'
            }, {
                title: '系统设置',
                html: 'Panel content!'
            },{
                righturl: null,
                title: "客户管理",
                leaf: false,
                children :{
                    righturl: null,
                    title: "客户星",
                    leaf: true
                }
            },Ext.create("Ext.tree.Panel",{
                title: '系统菜单',
                glyph: 0xf007,
                margins : '0 0 -1 1',
                region:'west',
                border : false,
                enableDD : false,
                split: true,
                width : 212,
                minWidth : 200,
                maxWidth : 300,
                rootVisible: false,
                containerScroll : true,
                collapsible : true,
                autoScroll: false,
                animCollapse : true,
                useArrows: true,
                store:Ext.create('Ext.data.TreeStore',{
                    autoLoad: true,
                    proxy: {
                        type: 'ajax',
                        url: '/static/js/app/common/main/data/manager.json',
                        reader: {
                            type: 'json',
                            successProperty: 'success'
                        }
                    }
                })
            })]

        });
        this.callParent(arguments);
    }
});
