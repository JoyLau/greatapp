Ext.define('Desktop.FishGame', {
    extend: 'Ext.ux.desktop.Module',
    id:'Desktop.FishGame',

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('fishgame');
        if(!win){
            win = desktop.createWindow({
                id: 'fishgame',
                title:'捕鱼达人',
                width:1024,
                height:770,
                iconCls: 'fish-task',
                animCollapse:false,
                border: false,
                hideMode: 'offsets',
                layout: 'fit',
                //拖动时不会透明化窗口
                // ghost:false,
                html:'<iframe width=100% height=100% src="/static/games/fish/index.html"></iframe>'
            });
        }
        return win;
    }
});
