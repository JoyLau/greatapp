/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        'Ext.window.MessageBox',
        'Ext.ux.desktop.ShortcutModel',
        'MyDesktop.SystemStatus',
        'MyDesktop.AccordionWindow',
        'MyDesktop.Notepad',
        'MyDesktop.FishGame',
        'MyDesktop.AboutLinb',
        'MyDesktop.YYTai',
        'MyDesktop.Settings',
        'MyDesktop.DevTask',
        'MyDesktop.Tank'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return [
            new MyDesktop.AboutLinb(), 
            new MyDesktop.SystemStatus(),
            new MyDesktop.FishGame(),
            new MyDesktop.AccordionWindow(),
            new MyDesktop.Notepad(),
            new MyDesktop.YYTai(),
            new MyDesktop.DevTask(),
            new MyDesktop.Tank()
        ];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            //cls: 'ux-desktop-black',

            contextMenuItems: [
                { text: '桌面背景', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: [
                    { name: '即时通讯', iconCls: 'accordion-shortcut', module: 'acc-win' },
                    { name: '记事本', iconCls: 'notepad-shortcut', module: 'notepad' },
                    { name: '捕鱼达人', iconCls: 'fish-shortcut', module: 'fishgame' },
                    { name: '90坦克', iconCls: 'tank-shortcut', module: '90tank' },
                    { name: '音悦台', iconCls: 'yytai-shortcut', module: 'yytai' },
                    { name: '系统状态', iconCls: 'cpu-shortcut', module: 'systemstatus'},
                    { name: '开发任务', iconCls: 'grid-shortcut', module: 'dev-task' }
                ]
            }),

            wallpaper: 'static/wallpapers/Wood-Sencha.jpg',
            wallpaperStretch: true
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            title: '开始',
            iconCls: 'user',
            height: 400,
            toolConfig: {
                width : 130,
                items: ['->',
                    {
                        text:'锁定',
                        textAlign : 'left',
                        iconCls:'settings',
                        tooltip:'锁定系统',
                        handler: function () {
                        },
                        scope: me
                    },
                    '-',
                    {
                        text:'关机',
                        tooltip:'退出系统',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('退出', '您确认退出系统吗?',function (btn) {
            if(btn == 'yes'){
                window.location.href = 'logout';
            }
        });
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
