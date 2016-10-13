/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/13.
 * static.js.desktop.js
 * DevelopmentApp
 */
Ext.define('Ext.ux.desktop.PreviewBox',{
        extend :'Ext.Component',
        inited: false,
        defaultZIndex: 21000,
        defaultLeft: 0,
        defaultBottom: 40,
        hideBottom: 30,
        boxWidth: 250,
        cloneWinMaxWidth: 220,
        cloneWinMaxHeight: 116,
        hideDelay: 500,
        showDelay: 500,
        initComponent: function () {
            this.renderTo = document.body;
            this.cls = "lbs-previewbox";
            this.hidden = true;
            this.inited = false;
            this.hoverCount = 0
        },


        createBoxElements: function () {
            var a = this.getEl(), b;
            this.boxMl = a.createChild({ tag: "div", cls: "lbs-previewbox-ml" });
            this.boxMr = this.boxMl.createChild({ tag: "div", cls: "lbs-previewbox-mr" });
            this.boxMc = this.boxMr.createChild({ tag: "div", cls: "lbs-previewbox-mc" });
            this.arrow = a.createChild({ tag: "div", cls: "lbs-previewbox-arrow" });
            b = this.boxMc;
            this.desc = b.createChild({ tag: "div", cls: "lbs-previewbox-desc" });
            b.createChild({ tag: "hr" });
            this.win = b.createChild({ tag: "div", cls: "lbs-previewbox-win" });
            this.inited = true;
            this.mon(mydsk.desktop.taskbar.getEl(), "click", this.onTaskbarClick, this);
            this.mon(mydsk.desktop.taskbar.getEl(), "contextmenu", this.onTaskbarClick, this)
        },
        onTaskbarClick: function () { this.hideBox(true) },
        showBox: function (a) {
            this.needShowBox = true;
            this.hoverCount += 1;
            Ext.defer(function () { this.doShowBox(a, this.hoverCount) }, 300, this);
        },

        doShowBox: function (f, e) {
            alert(f.win + f.centerX);
            var d, h, b, a, c, g;
            if (!f || !f.win || !f.centerX) { alert("required parameters not exist"); return }
            if (this.hoverCount !== e) { return }
            if (!this.needShowBox) { return }
            if (!this.inited) { this.createBoxElements() }
            c = Ext.isNumber(f.centerX) ? f.centerX : this.defaultLeft;
            d = f.win;
            h = d.getEl();
            g = d.title || "";
            this.desc.update(g || "");
            if (this.clonedEl) { this.clonedEl.remove() }
            this.clonedEl = this.getClonedEl(d);
            this.clonedEl.show();
            this.win.appendChild(this.clonedEl);
            b = this.getEl();
            if (this.isVisible()) {
                b.setBottom(this.defaultBottom);
                this.show();
                b.shift({ left: c - (this.boxWidth / 2), opacity: 1, easing: 'easeIn', duration: 500 })
            } else {
                b.setLeft(c - (this.boxWidth / 2));
                b.setBottom(this.hideBottom);
                b.setOpacity(0.2);
                this.show();
                b.shift({ bottom: this.defaultBottom, opacity: 1, easing: 'easeIn', duration: 500 })
            }
            this.hoverCount = 0
        },

        hideBox: function (a) {
            this.needShowBox = false;
            Ext.defer((function () { if (this.needShowBox) { return } this.doHideBox(a) }), (a === true) ? 0 : 300, this)
        },

        doHideBox: function (b) {
            var c;
            var a = function () {
                if (this.needShowBox) { return }
                this.hide();
            };
            if (this.clonedEl) { this.clonedEl.remove() }
            this.hoverCount = 0;
            if (b === true) { a.call(this); return }
            c = this.getEl();
            c.shift({ bottom: this.hideBottom, opacity: 0, duration: 500, easing: 'easeIn', scope: this, callback: a })
        },


        getClonedEl: function (e) {
            var c = 0;
            var h = 0;
            var g = e.getEl();
            var b = g.dom.cloneNode(true);
            b.className = b.className.replace('x-hide-offsets', '');
            b.removeAttribute("id");
            var f = Ext.get(b);
            f._previewMask = f.createChild({ tag: "div", cls: "lbs-previewbox-win-mask" });
            var a = g.getSize();
            var d = this.cloneWinMaxWidth / a.width;
            c = (this.cloneWinMaxHeight - a.height * d) / 2;
            if ((a.height * d) > this.cloneWinMaxHeight) { d = this.cloneWinMaxHeight / a.height; c = 0; h = (this.cloneWinMaxWidth - a.width * d) / 2 }
            d = Math.min(d, 1);
            f.setStyle("transform-origin", "0% 0%");
            f.setStyle("-webkit-transform-origin", "0% 0%");
            f.setStyle("-moz-transform-origin", "0% 0%");
            f.setStyle("-o-transform-origin", "0% 0%");
            f.setStyle("msTransform-origin", "0% 0%");
            f.setStyle("-webkit-transform", String.format("scale({0})", d));
            f.setStyle("-moz-transform", String.format("scale({0})", d));
            f.setStyle("-o-transform", String.format("scale({0})", d));
            f.setStyle("transform", String.format("scale({0})", d));
            f.setStyle("msTransform", String.format("scale({0},{1})", d, d));
            f.setLeftTop(h, c);
            return f
        }
    });