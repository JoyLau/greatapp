﻿Ext.define('Desktop.YYTai', {
    extend: 'Ext.ux.desktop.Module',

    requires: [ ],

    id:'yytai',
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('yytai');
        if(!win){
            win = desktop.createWindow({
                id: 'yytai',
                title:'音乐MTV',
                width:816,
                height:495,
                iconCls: 'icon-grid',
                animCollapse:false,
                border: false,
                hideMode: 'offsets',
                layout: 'fit',
                html:'<iframe width=100% height=100% src="http://www.yinyuetai.com/webqq"></iframe>'
            });
        }
        return win;
    }
});
