/**
 * Created by LiuFa on 2016/10/17.
 * static.js.desktop.app.exampool.main
 * greatapp
 */
Ext.define('examPoolMain.mainController', {
    extend: 'Ext.app.Controller',
    stores: [],
    models: [],
    views: ['examPoolMain.mainWestView'],
    //相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
    /*refs: [{
        ref: 'ChoiceGrid',
        selector: 'ChoiceGrid'
    }],*/
    init: function () {
        /*this.control({//这里的this相当于这个控制层
            'viewport > ChoiceGrid': {
                afterrender: function (gp) {
                    gp.down('button[id=addExamPool]').on('click', function () {
                        this.addExamPool()
                    }, this);
                    gp.down('button[id=updateExamPool]').on('click', function () {
                        this.updateExamPool()
                    }, this);
                    gp.down('button[id=deleteExamPool]').on('click', function () {
                        this.deleteExamPool()
                    }, this);
                }
            },
            //这样也可以监听grid的按钮点击事件
            'ChoiceGrid button[id=randomExamPool]': {
                click: function () {
                }
            }
        });*/
    }
});