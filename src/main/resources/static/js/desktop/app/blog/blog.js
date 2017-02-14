/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2017/2/14.
 * static.js.desktop.app.blog
 * DevelopmentApp
 */
Ext.define('Desktop.blog.blog', {
    id:'desk-blog',
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('desk-blog');
        if(!win){
            win = desktop.createWindow({
                id: 'desk-blog',
                title:'网站及博客管理主界面',
                width:'70%',
                height:'50%',
                iconCls: 'blog-shortcut-short',
                animCollapse:true,
                //窗口拖动不会透明化
                // ghost:false,
                constrainHeader:true,
                layout: "border",
                items: [xltree, xlview]
            });
        }
        return win;
    }
});

var xltree = Ext.create("Ext.tree.Panel",{
    title: '系统菜单',
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
        autoLoad: false,
        proxy: {
            type: 'ajax',
            url: basePath +'/static/data/blog/menu.json',
            reader: {
                type: 'json',
                successProperty: 'success'
            }
        }
    })
})


var xlview = Ext.create("Ext.panel.Panel", {
    region: "center",
    tbar: [{
        text: "新建校历",
        iconCls: "add",
    }, {
        text: "删除校历",
        iconCls: "remove",
    }],
    html: ''
});
