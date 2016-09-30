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
        'MyDesktop.VideoWindow',
        'MyDesktop.GridWindow',
        'MyDesktop.TabWindow',
        'MyDesktop.douban',
        'MyDesktop.AccordionWindow',
        'MyDesktop.Notepad',
        'MyDesktop.webdisk',
        'MyDesktop.FishGame',
        'MyDesktop.BogusMenuModule',
        'MyDesktop.AllMenuModule',
        'MyDesktop.BogusModule',
        'MyDesktop.AboutLinb',
        'MyDesktop.WebExcel',
        'MyDesktop.WebTV',
        'MyDesktop.YYTai',
        'MyDesktop.Settings',
        'MyDesktop.DevTask'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return [
            new MyDesktop.AboutLinb(), 
            new MyDesktop.VideoWindow(),
            new MyDesktop.SystemStatus(),
            new MyDesktop.webdisk(),           
            new MyDesktop.douban(),
            new MyDesktop.FishGame(),
            new MyDesktop.BogusMenuModule(),
            new MyDesktop.AllMenuModule(),
            new MyDesktop.GridWindow(),
            new MyDesktop.TabWindow(),
            new MyDesktop.AccordionWindow(),
            new MyDesktop.Notepad(),
            new MyDesktop.WebExcel(),
            new MyDesktop.WebTV(),
            new MyDesktop.YYTai(),
            new MyDesktop.DevTask()
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
                    { name: '网格窗口', iconCls: 'grid-shortcut', module: 'grid-win' },
                    { name: '即时通讯', iconCls: 'accordion-shortcut', module: 'acc-win' },
                    { name: '记事本', iconCls: 'notepad-shortcut', module: 'notepad' },
                    { name: '捕鱼达人', iconCls: 'search-shortcut', module: 'fishgame' },
                    { name: '网络云盘', iconCls: 'pwebdisk-shortcut', module: 'webdisk' },
                    { name: '网络电视', iconCls: 'tv48-shortcut', module: 'webtv' },
                    { name: '音悦台', iconCls: 'yytai-shortcut', module: 'yytai' },
                    { name: '电子表格', iconCls: 'excel-shortcut', module: 'webexcel' },
                    { name: '豆瓣电台', iconCls: 'dbfm-shortcut', module: 'douban' },  
                    { name: '系统状态', iconCls: 'cpu-shortcut', module: 'systemstatus'},
                    { name: '关于桌面', iconCls: 'role-shortcut', module: 'aboutlinb' },
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
            height: 300,
            toolConfig: {
                width : 100,
                textAlign : 'center',
                items: [{
                    text:'进入全屏',
                    iconCls:'settings',
                    tooltip:'点击进入全屏',
                    handler: function (btn) {
                        var b = document.documentElement;
                        if(btn.getText() == '进入全屏'){
                            if (b.requestFullscreen) {
                                b.requestFullscreen()
                            } else {
                                if (b.msRequestFullscreen) {
                                    b.msRequestFullscreen()
                                } else {
                                    if (b.mozRequestFullScreen) {
                                        b.mozRequestFullScreen()
                                    } else {
                                        if (b.webkitRequestFullScreen) {
                                            b.webkitRequestFullScreen()
                                        }
                                    }
                                }
                            }
                            btn.setText('退出全屏');
                            btn.setTooltip('点击退出全屏');
                        }else {
                            if (document.exitFullscreen) {
                                document.exitFullscreen();
                            }
                            else if (document.mozCancelFullScreen) {
                                document.mozCancelFullScreen();
                            }
                            else if (document.webkitCancelFullScreen) {
                                document.webkitCancelFullScreen();
                            }
                            else if (document.msExitFullscreen) {
                                document.msExitFullscreen();
                            }
                            btn.setText('进入全屏');
                            btn.setTooltip('点击进入全屏');
                        }
                    },
                    scope: me
                },'->',
                    {
                        text:'锁定',
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
            quickStart: [
                { name: '手风琴窗口', iconCls: 'accordion', module: 'acc-win' },
                { name: '网格窗口', iconCls: 'icon-grid', module: 'grid-win' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('Logout', 'Are you sure you want to logout?');
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
