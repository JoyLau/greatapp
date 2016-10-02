Ext.define('Desktop.FishGame', {
    extend: 'Ext.ux.desktop.Module',

    requires: [ ],

    id:'fishgame',

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('fishgame');
        if(!win){
            win = desktop.createWindow({
                id: 'fishgame',
                title:'捕鱼达人',
                width:1024,
                height:560,
                iconCls: 'fish-task',
                animCollapse:false,
                border: false,
                hideMode: 'offsets',
                layout: 'fit',
                html:'<iframe width=100% height=100% src="/static/games/fish/index.html"></iframe>'
            });
        }
        return win;
    }
});
