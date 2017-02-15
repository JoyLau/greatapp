/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Desktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        'Ext.window.MessageBox',
        'Ext.ux.desktop.ShortcutModel'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(name){
        return [
            Ext.create(name)
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
                    { name: '即时通讯', iconCls: 'accordion-shortcut', module: 'Desktop.AccordionWindow' },
                    { name: '记事本', iconCls: 'notepad-shortcut', module: 'Desktop.Notepad' },
                    // { name: '捕鱼达人', iconCls: 'fish-shortcut', module: 'fishgame' },
                    // { name: '90坦克', iconCls: 'tank-shortcut', module: '90tank' },
                    // { name: '音悦台', iconCls: 'yytai-shortcut', module: 'yytai' },
                    { name: '系统状态', iconCls: 'cpu-shortcut', module: 'Desktop.SystemStatus'},
                    { name: '开发任务', iconCls: 'grid-shortcut', module: 'Desktop.DevTask' },
                    { name: '题库', iconCls: 'exam-pool-shortcut', module: 'Desktop.exampool.ExamPool' },
                    { name: '网站博客', iconCls: 'blog-shortcut', module: 'Desktop.blog.blog' }
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
                        tooltip:'锁定系统,再次使用时需要输入密码解锁系统',
                        handler: function () {
                        },
                        scope: me
                    },
                    '-',
                    {
                        text:'关机',
                        tooltip:'安全退出系统',
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
        var dlg = new Desktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
