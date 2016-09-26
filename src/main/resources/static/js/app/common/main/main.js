/*
Ext.require([
    'Ext.container.Viewport',
    'Ext.grid.Panel',
    'Ext.grid.plugin.RowEditing',
    'Ext.layout.container.Border'
]);

Ext.onReady(function() {
    var text = 'a b c d e f g h i g k l m n o p q r s t u v w x y z',
        i = 50,
        sentences = [],
        words = text.split(' '),
        edCfg = {
            xtype: 'textfield'
        },
        paragraph;

    while (i--) {
        sentences.push(text);
    }
    paragraph = sentences.join(' ');

    Ext.define('Fubar', {
        extend: 'Ext.data.Model',
        fields: [ 'foo', 'bar', 'baz', 'zork', 'gork', 'bork' ]
    });

    // Hide the RTL Button as we already are RTL
    Ext.ComponentManager.onAvailable('options-toolbar', function(toolbar){

        var rtlButton = toolbar.down('button');
        if (rtlButton) {
            rtlButton.hide();
        }
    });


    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        rtl: true,
        items: [{
            region: 'north',
            title: '北区',
            height: 100,
            html: "<center>这里是北区</center>",
            autoScroll: true,
            collapsible: true,
            split: true
        },{
            region: 'west',
            id: 'west-region',
            title: '西区',
            width: 150,
            collapsible: true,
            split: true,
            tbar: {
                enableOverflow: true,
                items: [{
                    text: "这是西区"
                }, {
                    text: "西区二"
                }, {
                    text: "西区三"
                }, {
                    text: "西区四"
                }, {
                    text: "西区五"
                }]
            }
        }, {
            region: 'center',
            xtype: 'grid',
            plugins: [new Ext.grid.plugin.RowEditing()],
            title: '中区',
            columns: [
                { dataIndex: 'foo', text: "第一列", field: edCfg},
                { dataIndex: 'bar', text: "第二列", field: edCfg },
                { dataIndex: 'baz', text: "第三列", field: edCfg },
                { dataIndex: 'zork', text: "第四列", field: edCfg },
                { dataIndex: 'gork', text: "第五列", field: edCfg },
                { dataIndex: 'bork', text: "第六列", field: edCfg, flex: 1 }
            ],
            store: Ext.create('Ext.data.Store', {
                model: 'Fubar',
                data: [
                    [words[6], words[8], words[9], words[10], words[11], words[12]],
                    [words[5], words[4], words[3], words[2], words[1], words[0]],
                    [words[12], words[11], words[10], words[9], words[8], words[6]],
                    [words[0], words[1], words[2], words[3], words[4], words[5]],
                    [words[6], words[8], words[9], words[10], words[11], words[12]],
                    [words[5], words[4], words[3], words[2], words[1], words[0]],
                    [words[12], words[11], words[10], words[9], words[8], words[6]],
                    [words[0], words[1], words[2], words[3], words[4], words[5]],
                    [words[6], words[8], words[9], words[10], words[11], words[12]],
                    [words[5], words[4], words[3], words[2], words[1], words[0]],
                    [words[12], words[11], words[10], words[9], words[8], words[6]],
                    [words[0], words[1], words[2], words[3], words[4], words[5]],
                    [words[6], words[8], words[9], words[10], words[11], words[12]],
                    [words[5], words[4], words[3], words[2], words[1], words[0]],
                    [words[12], words[11], words[10], words[9], words[8], words[6]],
                    [words[0], words[1], words[2], words[3], words[4], words[5]],
                    [words[6], words[8], words[9], words[10], words[11], words[12]],
                    [words[5], words[4], words[3], words[2], words[1], words[0]],
                    [words[12], words[11], words[10], words[9], words[8], words[6]],
                    [words[0], words[1], words[2], words[3], words[4], words[5]]
                ]
            })
        }, {
            region: 'east',
            title: '东区',
            width: 200,
            collapsible: true,
            split: false
        }]
    });
});*/

Ext.Loader.setConfig({enabled: true});        //开启动态加载
Ext.application({
    name:'et',            //呵呵，就是ExtTest
    autoCreateViewport: true,
    appFolder:'static/js/app/common/main',    //指定根目录
    controllers:[
        'Menu'　　　　　　//在这里引入菜单，下次将详细介绍如何实现菜单加载
    ]
});
