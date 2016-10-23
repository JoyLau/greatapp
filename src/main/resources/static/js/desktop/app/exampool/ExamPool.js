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
                /*items: [Ext.create('Ext.tab.Panel', {
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
                        id: 'pcse',
                        title: '省级公务员试题',
                        glyph: 0xf2a3,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                            url: 'exampool/fileuUpload',
                            scope: this, // optional scope for the callback
                            discardUrl: true,
                            nocache: true,
                            text: "页面加载中,请稍候……",
                            loadMask: 'loading...',
                            scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                            id : 'pcse_panel',
                            resizeTabs: true,
                            enableTabScroll: true,
                            activeTab: 0,
                            autoDestroy: true,
                            collapsible: false,
                            defaults: {
                                autoScroll: false
                            },
                            minTabWidth: 50,
                            items :[{
                                title: '单选题',
                                glyph: 0xf00c,
                                loadMask: true,
                                closable: false,
                                /!*loader: {
                                    url: 'exampool/choice',
                                    autoLoad: true,
                                    scripts: true
                                }*!/
                                // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                            },{
                                title: '多选题',
                                glyph: 0xf046,
                                closable: false,
                                /!*loader: {
                                    url: 'exampool/pcse/multipleChoice',
                                    autoLoad: true,
                                    scripts: true
                                },*!/
                                loader: {
                                    url: 'static/games/test.html',
                                    autoLoad: true,
                                    scripts: true
                                },
                                listeners:{
                                     activate:function(tab){
                                         tab.loader.load();
                                     }
                                }
                            },{
                                title: '简答题',
                                glyph: 0xf1ea,
                                loadMask: true,
                                group: null,
                                closable: false,
                            },{
                                title: '判断题',
                                glyph: 0xf058,
                                loadMask: true,
                                group: null,
                                closable: false,
                            },{
                                title: '论述题',
                                glyph: 0xf0cb,
                                loadMask: true,
                                group: null,
                                closable: false,
                            },{
                                title: '案列分析题',
                                glyph: 0xf022,
                                loadMask: true,
                                group: null,
                                closable: false,
                            }]
                        }).show()
                        ]
                    }, {
                        title: '国家级公务员试题',
                        glyph: 0xf19c,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                         url: 'exampool/fileuUpload',
                         scope: this, // optional scope for the callback
                         discardUrl: true,
                         nocache: true,
                         text: "页面加载中,请稍候……",
                         loadMask: 'loading...',
                         scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                                resizeTabs: true,
                                enableTabScroll: true,
                                activeTab: 0,
                                autoDestroy: true,
                                collapsible: false,
                                defaults: {
                                    autoScroll: false
                                },
                                minTabWidth: 50,
                                items :[{
                                    title: '单选题',
                                    glyph: 0xf00c,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                    // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                                },{
                                    title: '多选题',
                                    glyph: 0xf046,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '简答题',
                                    glyph: 0xf1ea,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '判断题',
                                    glyph: 0xf058,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '论述题',
                                    glyph: 0xf0cb,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '案列分析题',
                                    glyph: 0xf022,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                }]
                            })
                        ]
                    }, {
                        title: '事业单位考试题',
                        glyph: 0xf09d,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                         url: 'exampool/fileuUpload',
                         scope: this, // optional scope for the callback
                         discardUrl: true,
                         nocache: true,
                         text: "页面加载中,请稍候……",
                         loadMask: 'loading...',
                         scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                                resizeTabs: true,
                                enableTabScroll: true,
                                activeTab: 0,
                                autoDestroy: true,
                                collapsible: false,
                                defaults: {
                                    autoScroll: false
                                },
                                minTabWidth: 50,
                                items :[{
                                    title: '单选题',
                                    glyph: 0xf00c,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                    // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                                },{
                                    title: '多选题',
                                    glyph: 0xf046,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '简答题',
                                    glyph: 0xf1ea,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '判断题',
                                    glyph: 0xf058,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '论述题',
                                    glyph: 0xf0cb,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '案列分析题',
                                    glyph: 0xf022,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                }]
                            })
                        ]
                    }, {
                        title: '政法干警考试题',
                        glyph: 0xf283,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                         url: 'exampool/fileuUpload',
                         scope: this, // optional scope for the callback
                         discardUrl: true,
                         nocache: true,
                         text: "页面加载中,请稍候……",
                         loadMask: 'loading...',
                         scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                                resizeTabs: true,
                                enableTabScroll: true,
                                activeTab: 0,
                                autoDestroy: true,
                                collapsible: false,
                                defaults: {
                                    autoScroll: false
                                },
                                minTabWidth: 50,
                                items :[{
                                    title: '单选题',
                                    glyph: 0xf00c,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                    // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                                },{
                                    title: '多选题',
                                    glyph: 0xf046,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '简答题',
                                    glyph: 0xf1ea,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '判断题',
                                    glyph: 0xf058,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '论述题',
                                    glyph: 0xf0cb,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '案列分析题',
                                    glyph: 0xf022,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                }]
                            })
                        ]
                    }, {
                        title: '自学考试题',
                        glyph: 0xf29d,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                         url: 'exampool/fileuUpload',
                         scope: this, // optional scope for the callback
                         discardUrl: true,
                         nocache: true,
                         text: "页面加载中,请稍候……",
                         loadMask: 'loading...',
                         scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                                resizeTabs: true,
                                enableTabScroll: true,
                                activeTab: 0,
                                autoDestroy: true,
                                collapsible: false,
                                defaults: {
                                    autoScroll: false
                                },
                                minTabWidth: 50,
                                items :[{
                                    title: '单选题',
                                    glyph: 0xf00c,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                    // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                                },{
                                    title: '多选题',
                                    glyph: 0xf046,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '简答题',
                                    glyph: 0xf1ea,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '判断题',
                                    glyph: 0xf058,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '论述题',
                                    glyph: 0xf0cb,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '案列分析题',
                                    glyph: 0xf022,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                }]
                            })
                        ]
                    },{
                        title: '大学生村官考试题',
                        glyph: 0xf298,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                         url: 'exampool/fileuUpload',
                         scope: this, // optional scope for the callback
                         discardUrl: true,
                         nocache: true,
                         text: "页面加载中,请稍候……",
                         loadMask: 'loading...',
                         scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                                resizeTabs: true,
                                enableTabScroll: true,
                                activeTab: 0,
                                autoDestroy: true,
                                collapsible: false,
                                defaults: {
                                    autoScroll: false
                                },
                                minTabWidth: 50,
                                items :[{
                                    title: '单选题',
                                    glyph: 0xf00c,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                    // html: '<iframe id="iframePage" scrolling="auto" frameborder="0" width="100%" height="100%" src="exampool/choice"></iframe>'
                                },{
                                    title: '多选题',
                                    glyph: 0xf046,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '简答题',
                                    glyph: 0xf1ea,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '判断题',
                                    glyph: 0xf058,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '论述题',
                                    glyph: 0xf0cb,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '案列分析题',
                                    glyph: 0xf022,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                }]
                            })
                        ]
                    },{
                        title: '职业技能考试题',
                        glyph: 0xf2a1,
                        loadMask: true,
                        group: null,
                        closable: false,
                        /!*autoLoad:{
                         url: 'exampool/fileuUpload',
                         scope: this, // optional scope for the callback
                         discardUrl: true,
                         nocache: true,
                         text: "页面加载中,请稍候……",
                         loadMask: 'loading...',
                         scripts: true},*!/
                        items :[
                            Ext.create('Ext.tab.Panel', {
                                resizeTabs: true,
                                enableTabScroll: true,
                                activeTab: 0,
                                autoDestroy: true,
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
                                },{
                                    title: '多选题',
                                    glyph: 0xf046,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '简答题',
                                    glyph: 0xf1ea,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '判断题',
                                    glyph: 0xf058,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '论述题',
                                    glyph: 0xf0cb,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                },{
                                    title: '案列分析题',
                                    glyph: 0xf022,
                                    loadMask: true,
                                    group: null,
                                    closable: false,
                                }]
                            })
                        ]
                    }/!*,{
                        title: '<i class="fa fa-cog fa-spin fa-lg fa-fw"></i>题库设置',
                        loadMask: true,
                        group: null,
                        closable: true,
                        html: '开发中....'
                    }*!/]
                })]*/





                /*loader: {
                    url: basePath + '/exampool/exampool',
                    autoLoad: true,
                    scripts: true,
                    loadMask: true
                },*/
                loader: {
                    renderer : function(loader, response, active) {
                        loader.getTarget().update(response.responseText, true);
                        return true;
                    },
                    url: basePath + '/exampool/exampool',
                    autoLoad: true,
                    scripts: true
                },
                listeners:{
                    resize:function(me,width,height){
                        if (!Ext.isEmpty(Ext.getCmp('examPoolMainPanel'))) {
                            Ext.getCmp('examPoolMainPanel').setHeight(height - 40);
                            Ext.getCmp('examPoolMainPanel').setWidth(width - 3);
                        }
                    },
                    beforeclose : function () {
                        if (!Ext.isEmpty(Ext.getCmp('examPoolMainPanel'))) {
                            Ext.getCmp('examPoolMainPanel').destroy();
                        }
                    }
                }
            });
        }
        return win;
    }
});
