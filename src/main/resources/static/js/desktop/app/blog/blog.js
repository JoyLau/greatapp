/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2017/2/14.
 * static.js.desktop.app.blog
 * DevelopmentApp
 */
Ext.define('Desktop.blog.blog', {
    id:'Desktop.blog.blog',
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('Desktop.blog.blog');
        if(!win){
            win = desktop.createWindow({
                id: 'Desktop.blog.blog',
                title:'网站及博客管理主界面',
                width:'70%',
                height:'50%',
                iconCls: 'blog-shortcut-short',
                animCollapse:true,
                constrainHeader:true,
                layout: "border",
                items: [westTree(),centerPanel()]
            });
        }
        return win;
    }
});

function westTree() {
    return Ext.create("Ext.tree.Panel",{
        title: '菜单',
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
            model:  Ext.define('model', {
                extend: 'Ext.data.Model',
                fields: [ 'id', 'parentid','text','url' ]
            }),
            proxy: {
                type: 'ajax',
                url: basePath +'/blog/getMenuTree',
                reader: {
                    type: 'json',
                    successProperty: 'success'
                }
            }
        }),
        listeners: {
            itemclick: function (view, record, item, index, e) {
                alert(record.get('url'))
            }
        }
    });
}

function centerPanel() {
    return Ext.create("Ext.panel.Panel", {
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
}
