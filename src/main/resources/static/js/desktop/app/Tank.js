/**
 * Created by LiuFa on 2016/10/2.
 */
Ext.define('Desktop.Tank', {
    extend: 'Ext.ux.desktop.Module',

    requires: [ ],

    id:'90tank',
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('90tank');
        if(!win){
            win = desktop.createWindow({
                id: '90tank',
                title:'90坦克—儿时经典游戏',
                width:800,
                height:600,
                iconCls: 'tank-task',
                animCollapse:false,
                border: false,
                hideMode: 'offsets',
                layout: 'fit',
                html:'<iframe width=100% height=100% scrolling="no" src="/static/games/90Tank/index.html"></iframe>'
            });
        }
        return win;
    }
});