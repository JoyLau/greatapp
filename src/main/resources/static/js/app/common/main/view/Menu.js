Ext.define('et.view.Menu',{
    extend: 'Ext.tree.Panel',
    alias: 'widget.sxptmenu', 
    requires:['et.store.Menus'], 
    initComponent : function(){
        Ext.setGlyphFontFamily('FontAwesome');
        Ext.apply(this,{ 
            id: 'menu-panel',
            title: '系统菜单',
            iconCls:'icon-menu',
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
            store:Ext.create('et.store.Menus')
        }); 
        this.callParent(arguments); 
    } 
}); 
