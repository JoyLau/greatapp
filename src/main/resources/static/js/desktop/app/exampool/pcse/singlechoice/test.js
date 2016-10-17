/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.require(["*"]);
Ext.define("wddata", {
    extend: "Ext.data.Model",
    fields: ["rank", "id", "ddid", "ddcont", "title", "createtime", "xm", "isshare"]
});
var wdstore = Ext.create("Ext.data.Store", {
    pageSize: 10,
    model: "wddata",
    remoteSort: false,
    proxy: {
        type: "ajax",
        url: "workdocGrid.aspx",
        reader: {
            root: "items",
            totalProperty: "total"
        },
        simpleSortMode: true
    }
});
var wdtreestore = Ext.create("Ext.data.TreeStore", {
    proxy: {
        type: "ajax",
        url: "datadictTree.aspx?dirid=4"
    },
    root: {
        text: "工作文档",
        id: "9999",
        expanded: true
    },
    folderSort: false
});
var wdtree = Ext.create("Ext.tree.Panel", {
    store: wdtreestore,
    height: 548,
    width: 160,
    title: "文档分类",
    useArrows: false,
    collapsible: true,
    region: "west",
    tools: [{
        type: "refresh",
        handler: function(C, A) {
            tree.setLoading(true, tree.body);
            var B = tree.getRootNode();
            B.collapseChildren(true, false);
            Ext.Function.defer(function() {
                tree.setLoading(false);
                B.expand(true, true)
            }, 1000)
        }
    }],
    listeners: {
        afterrender: function(A) {
            A.getSelectionModel().select(0)
        },
        itemclick: function(C, A) {
            var B = Ext.getCmp("rgx").lastValue.isshare;
            wdstore.load({
                params: {
                    subId: A.raw.id,
                    ish: B
                }
            })
        }
    }
});
var wdgrid = Ext.create("Ext.grid.Panel", {
    xtype: "grid",
    title: "文档列表",
    collapsible: true,
    columnWidth: 1,
    store: wdstore,
    height: 240,
    region: "north",
    id: "wdgridid",
    emptyText: '<br><br><p align=center><img src="images/blank.png"></p>',
    disableSelection: false,
    columns: [{
        text: "序号",
        width: 40,
        dataIndex: "rank",
        sortable: true
    }, {
        text: "分类",
        width: 80,
        dataIndex: "ddcont",
        sortable: true
    }, {
        text: "标题",
        width: 180,
        flex: 1,
        dataIndex: "title",
        sortable: true
    }, {
        text: "作者",
        width: 80,
        dataIndex: "xm",
        sortable: true
    }, {
        text: "创建时间",
        width: 140,
        dataIndex: "createtime",
        renderer: function(A) {
            if (A) {
                return A.replace("T", " ")
            } else {
                return ""
            }
        },
        sortable: true
    }, {
        text: "共享",
        width: 40,
        dataIndex: "isshare",
        sortable: true
    }],
    listeners: {
        itemclick: function(D, A, C, E, B) {
            document.getElementById("wdframe").src = "getworkdoc.aspx?id=" + A.raw.id
        }
    },
    bbar: Ext.create("Ext.PagingToolbar", {
        store: wdstore,
        displayInfo: true,
        displayMsg: "显示 {0} - {1} 条，共计 {2} 条",
        emptyMsg: "没有数据"
    }),
    tbar: [{
        text: "新建文档",
        iconCls: "add",
        handler: function() {
            wdedit("newwd", "", "")
        }
    }, {
        text: "编辑",
        iconCls: "qm",
        handler: function() {
            var A = wdgrid.getSelectionModel().getSelection()[0];
            if (A) {
                wdedit(A.raw.id, A.raw.ddid, A.raw.title)
            } else {
                Ext.Msg.alert("出错啦", "没有选要编辑的信息！")
            }
        }
    }, {
        text: "删除",
        iconCls: "remove",
        handler: function() {
            var A = wdgrid.getSelectionModel().getSelection()[0];
            if (A) {
                Ext.Ajax.request({
                    url: "workdocdel.aspx?ids=" + A.raw.id + "&title=" + A.raw.title,
                    success: function(B) {
                        var C = Ext.JSON.decode(B.responseText);
                        if (C.success == true) {
                            wdstore.load()
                        } else {
                            Ext.Msg.alert("文档删除提示", "文档删除失败,请检查是否权限不够！")
                        }
                    },
                    failure: function(B) {
                        Ext.MessageBox.alert("错误", "请与后台服务人员联系")
                    }
                })
            } else {
                Ext.Msg.alert("提示", "请先选择您要操作的行！")
            }
        }
    }, "->",
        {
            xtype: "radiogroup",
            fieldLabel: "显示",
            id: "rgx",
            labelWidth: 30,
            width: 200,
            listeners: {
                change: function() {
                    var A = wdtree.getSelectionModel().getSelection()[0];
                    var B = Ext.getCmp("rgx").lastValue.isshare;
                    wdstore.load({
                        params: {
                            subId: A.raw.id,
                            ish: B
                        }
                    })
                }
            },
            items: [{
                name: "isshare",
                inputValue: "1",
                boxLabel: "共享文档",
                checked: true
            }, {
                name: "isshare",
                inputValue: "0",
                boxLabel: "我的文档"
            }]
        }]
});

function wdedit(B, D, A) {
    Ext.define("wdtypeData", {
        extend: "Ext.data.Model",
        fields: ["id", "ddcont"]
    });
    var E = Ext.create("Ext.data.Store", {
        model: "wdtypeData",
        remoteSort: false,
        proxy: {
            type: "ajax",
            url: "datadictcombo.aspx?dirid=4"
        }
    });
    E.load();
    var C = Ext.widget("form", {
        border: false,
        bodyPadding: 10,
        bodyStyle: "background-image: url(images/body-bkg.png);",
        layout: "column",
        fieldDefaults: {
            labelAlign: "right",
            labelWidth: 40,
            labelStyle: "font-weight:bold"
        },
        items: [{
            xtype: "hidden",
            id: "did",
            name: "did",
            value: B
        }, {
            xtype: "textfield",
            fieldLabel: "标题",
            blankText: "请输入标题",
            name: "wdtitle",
            id: "wdtitle",
            allowBlank: false,
            value: A,
            width: 480
        }, {
            xtype: "combo",
            id: "ntype",
            name: "ntype",
            fieldLabel: "分类",
            store: E,
            width: 160,
            labelWidth: 40,
            mode: "local",
            queryMode: "local",
            editable: false,
            displayField: "ddcont",
            valueField: "id",
            emptyText: "请选择..",
            value: D,
            allowBlank: false,
            enabled: true,
            selectOnFocus: true
        }, {
            xtype: "radiogroup",
            fieldLabel: "是否共享",
            labelWidth: 65,
            width: 220,
            items: [{
                name: "isshare",
                inputValue: "1",
                boxLabel: "共享",
                checked: true
            }, {
                name: "isshare",
                inputValue: "0",
                boxLabel: "不共享"
            }]
        }, {
            xtype: "htmleditor",
            anchor: "98%",
            fieldLabel: "内容",
            name: "wdcont",
            id: "wdcont",
            allowBlank: false,
            blankText: "内容不能为空",
            fontFamilies: ["宋体", "隶书", "黑体", "楷体", "Arial", "Courier New", "Tahoma", "Times New Roman", "Verdana"],
            width: 650,
            height: 360
        }]
    });
    Ext.create("Ext.window.Window", {
        border: true,
        id: "newwdwin",
        title: "新建/编辑工作文档",
        frame: true,
        modal: true,
        width: 680,
        height: 500,
        maximizable: true,
        resizable: true,
        iconCls: "qm",
        hideMode: "offsets",
        layout: "fit",
        bbar: ["->",
            {
                xtype: "button",
                text: "退出",
                iconCls: "delete",
                handler: function() {
                    Ext.getCmp("newwdwin").close()
                }
            }, {
                xtype: "button",
                text: "提交",
                iconCls: "icon-upload",
                handler: function() {
                    if (C.form.isValid()) {
                        if (Ext.getCmp("wdcont").getValue().length < 20) {
                            Ext.Msg.alert("提示", "信息内容太短(<20)!");
                            return
                        };
                        Ext.getCmp("wdcont").setValue(Ext.util.Format.htmlEncode(Ext.getCmp("wdcont").getValue()));
                        C.form.submit({
                            url: "workdocadd.aspx",
                            method: "post",
                            waitMsg: "正在写入,请稍候...",
                            success: function(G, F) {
                                if (F.result.msg == "成功") {
                                    wdstore.loadPage(1);
                                    Ext.Msg.alert("提示", "提交编辑文档成功！");
                                    Ext.getCmp("newwdwin").close()
                                } else {
                                    Ext.Msg.alert("失败", "只有登录后的教师用户完整输入才能创建!")
                                }
                            },
                            failure: function(F) {
                                Ext.MessageBox.alert("错误", "请与后台服务人员联系")
                            }
                        })
                    } else {
                        Ext.Msg.alert("提示", "请完整输入以上内容!")
                    }
                }
            }],
        items: C
    }).show();
    if (B.length > 15) {
        Ext.Ajax.request({
            url: "wdGetcont.aspx?id=" + B,
            success: function(F) {
                if (F.responseText == "noeditrole!") {
                    Ext.Msg.alert("提示", "只有作者才能编辑该文档!");
                    Ext.getCmp("newwdwin").close()
                } else {
                    Ext.getCmp("wdcont").setValue(F.responseText)
                }
            },
            failure: function(F) {
                Ext.MessageBox.alert("错误", "请与后台服务人员联系")
            }
        })
    }
};
var wdview = Ext.create("Ext.panel.Panel", {
    region: "center",
    html: '<iframe id="wdframe" name="wdframe" width="100%" height="100%" src="getworkdoc.aspx"></iframe>'
});

function workdocdo() {
    wdstore.load();
    var A = new Ext.Panel({
        layout: "border",
        id: "panelm3-19",
        flex: 1,
        height: 548,
        renderTo: "workdoca",
        items: [wdtree,
            {
                xtype: "panel",
                layout: "border",
                region: "center",
                items: [wdgrid, wdview]
            }]
    })
};
Ext.onReady(workdocdo);