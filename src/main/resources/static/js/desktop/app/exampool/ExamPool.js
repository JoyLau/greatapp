/**
 * Created by LiuFa on 2016/10/3.
 */
Ext.define('Desktop.exampool.ExamPool', {
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
                //窗口拖动不会透明化
                // ghost:false,
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
                    minTabWidth: 200,
                    items: [{
                        id: 'exam-pool-choice',
                        title: '选择题',
                        glyph: 0xf046,
                        loadMask: true,
                        group: null,
                        closable: false,
                        // autoLoad:{
                        //     url: 'exampool/choice',
                        //     scope: this, // optional scope for the callback
                        //     discardUrl: true,
                        //     nocache: true,
                        //     text: "页面加载中,请稍候……",
                        //     loadMask: 'loading...',
                        //     scripts: true},
                        html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                    }, {
                        title: '简答题',
                        loadMask: true,
                        group: null,
                        glyph: 0xf09d,
                        closable: false,
                        listeners:{
                           /* activate:function(){
                                this.update({html : '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'})
                            }*/
                        }
                    }, {
                        title: '综合题',
                        loadMask: true,
                        group: null,
                        glyph: 0xf275,
                        closable: false,
                        html: '开发中....'
                    }, {
                        title: '爬虫抓取',
                        loadMask: true,
                        group: null,
                        glyph: 0xf188,
                        closable: false,
                        html: '开发中....'
                    }, {
                        title: '题库设置',
                        loadMask: true,
                        group: null,
                        glyph: 0xf085,
                        closable: true,
                        html: '开发中....'
                    }]
                })]
            });
        }
        return win;
    }
});
