/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.main
 * greatapp
 */
Ext.Loader.setConfig({enabled: true});
Ext.application({
    name:'examPoolMain',
    appFolder: basePath + '/static/js/desktop/app/exampool/main',
    controllers:[
        'examPoolMain.mainController'
    ],
    launch: function () {
        Ext.create('Ext.panel.Panel',{
            renderTo : 'examPoolMain',
            height: '100%',
            layout: "border",
            id :'examPoolMainPanel',
            items: [Ext.create('examPoolMain.mainWestView'),
                Ext.create('Ext.tab.Panel', {
                    id :'examPoolCenterTabPanel',
                    resizeTabs: true,
                    enableTabScroll: true,
                    activeTab: 0,
                    autoDestroy: true,
                    region: "center",
                    collapsible: false,
                    plain: true,
                    defaults: {
                        autoScroll: false
                    },
                    minTabWidth: 100,
                    items :[{
                        title: '单选题',
                        glyph: 0xf00c,
                        loadMask: true,
                        group: null,
                        closable: false,
                        // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                    }]
                })]

        })
    }
});