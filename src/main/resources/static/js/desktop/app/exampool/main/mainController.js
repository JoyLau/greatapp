/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.main
 * greatapp
 */
Ext.define('examPoolMain.mainController', {
    extend: 'Ext.app.Controller',
    views: ['examPoolMain.mainWestView','examPoolMain.mainCenterView'],
    init: function () {
        this.control({
            'examPoolWestView': {
            }
        });
    }
});