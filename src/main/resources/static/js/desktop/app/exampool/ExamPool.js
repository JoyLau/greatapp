/**
 * Created by LiuFa on 2016/10/3.
 */
Ext.define('Desktop.exampool.ExamPool', {
    // requires : ['Desktop.exampool.Choice'],
    id:'exam-pool',
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('exam-pool');
        if(!win){
            win = desktop.createWindow({
                id: 'exam-pool',
                title:'题库',
                width:'50%',
                height:'70%',
                iconCls: 'exam-pool-task',
                animCollapse:true,
                constrainHeader:true,
                layout: 'fit',
                items: [Ext.create('Ext.tab.Panel', {
                    resizeTabs: true,
                    enableTabScroll: true,
                    activeTab: 0,
                    autoDestroy: true,
                    collapsible: false,
                    defaults: {
                        autoScroll: false
                    },
                    minTabWidth: 80,
                    items: [{
                        id: 'exam-pool-choice',
                        title: '选择题',
                        glyph: 0xf046,
                        loadMask: true,
                        group: null,
                        closable: false,
                        // items: [Ext.create('Desktop.exampool.Choice'),Ext.create('Desktop.exampool.ChoiceGrid')]
                        autoLoad: {url: 'exampool/choice'}
                    }, {
                        title: '简答题',
                        loadMask: true,
                        group: null,
                        glyph: 0xf09d,
                        closable: false,
                        html: '开发中....'
                    }, {
                        title: '综合题',
                        loadMask: true,
                        group: null,
                        glyph: 0xf275,
                        closable: false,
                        html: '开发中....'
                    }]
                })]
            });
        }
        return win;
    }
});
