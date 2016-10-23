/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.main
 * greatapp
 */
Ext.define('examPoolMain.mainWestView', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.examPoolWestView',
    initComponent: function () {
        Ext.apply(this, {
            id: 'menu-panel',
            region: 'west',
            width: 200,
            minWidth: 150,
            maxWidth: 300,
            height : '100%',
            split: true,
            collapsible: true,
            //折叠时，点击边框也可以展开
            floatable: true,
            title: '导航',
            glyph: 0xf0c9,
            layout: {
                type: 'accordion',
                animate: true,
                activeOnTop: true,
                autoScroll : true
            },
            tools: [{
                    type: 'refresh',
                    tooltip: '刷新',
                    handler: function (event, toolEl, panel) {
                    }
                }],
            listeners: {
                afterrender :function (comp, opts) {
                    var me = this;
                    Ext.Function.defer(me.getMenu, 300, me);
                }
            }
        });
        this.callParent(arguments);
    },

    //从服务器请求菜单数据生成 accordion和Tree 菜单
    getMenu : function () {
        var me = this;
        Ext.Ajax.request({
            method : 'post',
            url : basePath + '/exampool/getMenu',
            success : function(response) {
                var json = Ext.JSON.decode(response.responseText);
                if (json.success) {
                    Ext.each(json.menu, function (el) {
                        if (el == undefined) {
                            return true;
                        }
                        var panel = Ext.create('Ext.panel.Panel', {
                            title : el.name,
                            titleAlign : 'center',
                            glyph : Number(el.icon),
                            layout : 'fit'
                        });
                        panel.add(me.buildTree(el));
                        Ext.getCmp("menu-panel").add(panel);
                    });
                }
            },
            failure : function(request) {
                Ext.MessageBox.show( {
                    title : '操作提示',
                    msg : "连接服务器失败",
                    buttons : Ext.MessageBox.OK,
                    icon : Ext.MessageBox.ERROR
                });
            }
        });
    },

    buildTree : function (json) {
        return Ext.create('Ext.tree.Panel',{
            //箭头树
            // useArrows: true,
            rootVisible: false,
            border: false,
            animate : true,
            root: {
                id: Ext.id(),
                text: "",
                expanded: true
            },
            listeners: {
                beforerender : function (cmp,opts) {
                    var root = cmp.getRootNode();
                    Ext.each(json.children, function (el) {
                        root.appendChild({
                            id : el.id,
                            text: el.name,
                            leaf: el.leaf != 0,
                            iconCls  : el.icon,
                            icon : basePath + '/static/images/desktop/add.gif',
                            qtitle : el.url,
                            // qtip : el.notes
                        });
                    })
                },
                //节点单击事件
                itemclick: function (view, record, item, index, e) {
                    var id = 'examPoolCenterTabs' + record.get('id');
                    var text = record.get('text');
                    var notes = record.get('qtip');
                    var url = record.get('qtitle');
                    var glyph = record.get('iconCls');
                    var leaf = record.get('leaf');
                    var tabs = Ext.getCmp('examPoolCenterTabPanel');//获取center布局的组件
                    var addPanel = tabs.getComponent(id); //新增一个component
                    if (leaf) {
                        //判断tab页是否已经打开，如果没打开，则新增一个，否则激活它
                        if(!addPanel ) {
                            addPanel = tabs.add({
                                id : id,
                                title : text,
                                closable : true,
                                glyph: Number(glyph),
                                /*loader: {
                                    url: basePath + url + '?mainPanelId=' + id + 'mainPanel',
                                    autoLoad: true,
                                    scripts: true,
                                    loadMask: true
                                },*/
                                loader: {
                                    renderer : function(loader, response, active) {
                                        loader.getTarget().update(response.responseText, true);
                                        return true;
                                    },
                                    url: basePath + url + '?mainPanelId=' + id + 'mainPanel',
                                    autoLoad: true,
                                    scripts: true,
                                    loadMask : true
                                },
                                listeners:{
                                    resize:function(me,width,height){
                                        if (!Ext.isEmpty(Ext.getCmp(id + 'mainPanel'))) {
                                            Ext.getCmp(id + 'mainPanel').setHeight(height);
                                            Ext.getCmp(id + 'mainPanel').setWidth(width);
                                        }
                                    },
                                    beforeclose : function () {
                                    }
                                }
                            }).show();
                        }
                        tabs.setActiveTab(addPanel);
                    }
                },
                scope: this
            }
        });
    }
});
