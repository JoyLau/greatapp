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
            split: true,
            collapsible: true,
            //折叠时，点击边框也可以展开
            floatable: true,
            title: '导航',
            glyph: 0xf0c9,
            layout: {
                type: 'accordion',
                animate: true,
                activeOnTop: true
            },
            tools: [{
                    type: 'expand',
                    tooltip: '展开',
                    handler: function (event, toolEl, panel) {
                        // 实现逻辑
                    }
                },{
                    type: 'collapse',
                    tooltip: '折叠',
                    handler: function (event, toolEl, panel) {
                        // 实现逻辑
                    }
                }]
        });
        this.callParent(arguments);




        //构建树
        var buildTree = function (json) {
            return Ext.create('Ext.tree.Panel',
                {
                    useArrows: true,
                    rootVisible: false,
                    border: false,
                    root: {
                        id: Ext.id(),
                        text: "",
                        expanded: true,
                        children: json.children
                    },
                    listeners: {
                        //节点单击事件
                        itemclick: function (view, record, item, index, e) {
                            var id = 'examPoolCenterTabs' + record.get('id');
                            var text = record.get('text');
                            var url = record.get('url');
                            var leaf = record.get('leaf');
                            var tabs = Ext.getCmp('examPoolCenterTabPanel');//获取center布局的组件
                            var addPanel = tabs.getComponent(id); //新增一个component
                            if (leaf) {
                                //先判断你需要打开的tab页是否已经打开，如果没打开，则新增一个，否则激活它
                                if(!addPanel ) {
                                    addPanel = Ext.create('Ext.panel.Panel',{
                                        id : id,
                                        title : text, //同上
                                        tabTip : text,
                                        closable : true,
                                        html:'<iframe id=mainPage width="100%" height="100%" frameborder=0 src='+url+'></iframe>'
                                    });
                                    tabs.add(addPanel );
                                }
                                tabs.setActiveTab(addPanel);
                            }
                        },
                        scope: this
                    }
                });
        };
        /**
         * 创建AJAX请求，从服务器请求菜单数据生成 accordion和Tree 菜单
         */
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
                            // id: el.id,
                            title : el.text,
                            layout : 'fit'
                        });
                        panel.add(buildTree(el));
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
    }
});
