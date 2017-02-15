/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

/**
 * @class Ext.ux.desktop.TaskBar
 * @extends Ext.toolbar.Toolbar
 */
Ext.define('Ext.ux.desktop.TaskBar', {
    // This must be a toolbar. we rely on acquired toolbar classes and inherited toolbar methods for our
    // child items to instantiate and render correctly.
    extend: 'Ext.toolbar.Toolbar',

    requires: [
        'Ext.button.Button',
        'Ext.resizer.Splitter',
        'Ext.menu.Menu',
        // 'Ext.ux.desktop.PreviewBox',
        'Ext.ux.desktop.StartMenu'
    ],

    alias: 'widget.taskbar',

    cls: 'ux-taskbar',

    /**
     * @cfg {String} startBtnText
     * The text for the Start Button.
     */
    startBtnText: '开始',

    initComponent: function () {
        var me = this;

        // me.previewBox = new Ext.ux.desktop.PreviewBox();
        me.startMenu = new Ext.ux.desktop.StartMenu(me.startConfig);

        me.windowBar = new Ext.toolbar.Toolbar(me.getWindowBarConfig());

        me.tray = new Ext.toolbar.Toolbar(me.getTrayConfig());

        me.items = [
            {
                xtype: 'button',
                cls: 'ux-start-button',
                // iconCls: 'ux-start-button-icon',
                glyph: 0xf17a,
                menu: me.startMenu,
                menuAlign: 'bl-tl',
                text: me.startBtnText
            },
            '-',
            {
                xtype : 'button',
                tooltip: {text: '点击进入全屏', align: 'bl-tl'},
                text : '',
                // icon: "static/images/desktop/fullscreen.png",
                glyph: 0xf0b2,
                handler: fullscreen
            },
            '-',
            me.windowBar,
            '-', {
                id: 'goworkflow',
                tooltip: {text: '即时通讯', align: 'bl-tl'},
                icon: "static/images/desktop/im16.png",
                handler: function () {

                    var module = DesktopApp.getModule('Desktop.AccordionWindow'),
                        window;
                    if (module) {
                        window = module.createWindow();
                        window.showAt([Ext.getBody().getWidth() - 265, Ext.getBody().getHeight() - 455]);
                    }


                }
            },'-',
            me.tray
        ];

        me.callParent();
    },

    afterLayout: function () {
        var me = this;
        me.callParent();
        me.windowBar.el.on('contextmenu', me.onButtonContextMenu, me);
    },

    /**
     * This method returns the configuration object for the Tray toolbar. A derived
     * class can override this method, call the base version to build the config and
     * then modify the returned object before returning it.
     */
    getTrayConfig: function () {
        var ret = {
            items: this.trayItems
        };
        delete this.trayItems;
        return ret;
    },

    getWindowBarConfig: function () {
        return {
            flex: 1,
            cls: 'ux-desktop-windowbar',
            items: [ '&#160;' ],
            layout: { overflowHandler: 'Scroller' }
        };
    },

    getWindowBtnFromEl: function (el) {
        var c = this.windowBar.getChildByElement(el);
        return c || null;
    },

    onButtonContextMenu: function (e) {
        var me = this, t = e.getTarget(), btn = me.getWindowBtnFromEl(t);
        if (btn) {
            e.stopEvent();
            me.windowMenu.theWin = btn.win;
            me.windowMenu.showBy(t);
        }
    },

    onWindowBtnClick: function (btn) {
        var win = btn.win;

        if (win.minimized || win.hidden) {
            btn.disable();
            win.show(null, function() {
                btn.enable();
            });
        } else if (win.active) {
            btn.disable();
            win.on('hide', function() {
                btn.enable();
            }, null, {single: true});
            win.minimize();
        } else {
            win.toFront();
        }
    },

    addTaskButton: function(win) {
        var config = {
            iconCls: win.iconCls,
            enableToggle: true,
            toggleGroup: 'all',
            // width: 140,
            margins: '0 2 0 3',
            text: Ext.util.Format.ellipsis(win.title, 20),
            listeners: {
                click: this.onWindowBtnClick,
                scope: this
            },
            win: win
        };

        var cmp = this.windowBar.add(config);
        cmp.toggle(true);
        return cmp;
    },

    removeTaskButton: function (btn) {
        var found, me = this;
        me.windowBar.items.each(function (item) {
            if (item === btn) {
                found = item;
            }
            return !found;
        });
        if (found) {
            me.windowBar.remove(found);
        }
        return found;
    },

    setActiveButton: function(btn) {
        if (btn) {
            btn.toggle(true);
        } else {
            this.windowBar.items.each(function (item) {
                if (item.isButton) {
                    item.toggle(false);
                }
            });
        }
    },
});

/**
 * @class Ext.ux.desktop.TrayClock
 * @extends Ext.toolbar.TextItem
 * This class displays a clock on the toolbar.
 */
/*Ext.define('Ext.ux.desktop.TrayClock', {
    extend: 'Ext.button.Button',

    alias: 'widget.trayclock',

    id : 'trayclock',

    disableMouseOver : false,

    initComponent: function () {
        var me = this;
        // 设置事件监听
        if (!this.listeners)
            this.listeners = {};

        Ext.apply(this.listeners, {
            // 鼠标移开，背景设置透明
            mouseout : function() {
                this.setTransparent(document.getElementById(this.id));
            },
            mouseover : function(button) {
                button.setTooltip(Ext.Date.format(new Date(), 'l, j F Y h:i A'))
            },
            // 背景设置透明
            afterrender : function(cmp) {
                this.setTransparent(document.getElementById(this.id));
                Ext.TaskManager.start({
                    run: function () {
                        cmp.setText(Ext.Date.format(new Date(), 'H:i:s A'));
                    },
                    interval: 1000
                });
            }
        });
        me.callParent();
    },

    setTransparent : function(b) {
        b.style.backgroundImage = 'inherit';
        b.style.backgroundColor = 'inherit';
        b.style.borderColor = 'transparent';
    }
});*/
Ext.define('Ext.ux.desktop.TrayClock', {
    extend: 'Ext.toolbar.TextItem',

    alias: 'widget.trayclock',

    cls: 'ux-desktop-trayclock',

    html: '&#160;',

    timeFormat: 'H:i:s A',

    tpl: '{time}',

    initComponent: function () {
        var me = this;

        me.callParent();

        if (typeof(me.tpl) == 'string') {
            me.tpl = new Ext.XTemplate(me.tpl);
        }
    },

    afterRender: function () {
        var me = this;
        Ext.Function.defer(me.updateTime, 100, me);
        me.callParent();
    },

    onDestroy: function () {
        var me = this;

        if (me.timer) {
            window.clearTimeout(me.timer);
            me.timer = null;
        }

        me.callParent();
    },

    updateTime: function () {
        var me = this, time = Ext.Date.format(new Date(), me.timeFormat),
            text = me.tpl.apply({ time: time });
        if (me.lastText != text) {
            me.setText(text);
            me.lastText = text;
        }
        me.timer = Ext.Function.defer(me.updateTime, 1000, me);
    }
});


var fullscreen = function (btn) {
    var b = document.documentElement;
    if(btn.getText() == ''){
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
        btn.setGlyph(0xf066);
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
        btn.setText('');
        btn.setGlyph(0xf065);
        btn.setTooltip('点击进入全屏');
    }
};
