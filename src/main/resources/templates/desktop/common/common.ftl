<#assign path=request.contextPath />
<style type="text/css">
    #loading-mask {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: 20000;
        background-color: white;
    }
    #loading {
        position: absolute;
        left: 50%;
        top: 50%;
        z-index: 20001;
        height: 200px;
        width: 200px;
        margin:-100px 0 0 -100px
    }
</style>
<link rel="stylesheet" type="text/css" href="${path}/static/js/desktop/resources/ext-theme-neptune/ext-theme-neptune-all.css" />
<link rel="stylesheet" type="text/css" href="${path}/static/css/desktop/desktop.css" />
<link rel="stylesheet" href="${path}/static/css/fontawesome/font-awesome.min.css"/>

<script type="text/javascript" src="${path}/static/js/desktop/js/ext-all.js"></script>
<script type="text/javascript" src="${path}/static/js/desktop/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/static/js/desktop/js/ext-theme-neptune.js"></script>

<#--全局公共js-->
<script type="text/javascript" src="${path}/static/js/app/common/extcommon.js"></script>

<#--插件-->
<script type="text/javascript" src="${path}/static/js/app/plugins/TabCloseMenu.js"></script>
<script type="text/javascript" src="${path}/static/js/app/plugins/ProgressBarPager.js"></script>

<#--<script type="text/javascript" src="${path}/static/js/app/plugins/UEditor.js"></script>-->
<#--<script type="text/javascript" src="${path}/static/plugins/ueditor/ueditor.config.js"></script>-->
<#--<script type="text/javascript" src="${path}/static/plugins/ueditor/ueditor.all.min.js"></script>-->
<#--<script type="text/javascript" src="${path}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>-->
<#--<script type="text/javascript" src="${path}/static/js/app/plugins/MaskBinder.js"></script>-->
<#--<script type="text/javascript" src="/static/js/desktop/js/options-toolbar.js"></script>-->

<script type="text/javascript">
    var basePath = '${path}';
    Ext.setGlyphFontFamily('FontAwesome');

    Ext.onReady(function () {
        Ext.QuickTips.init();
        if(!Ext.isEmpty(Ext.get('loading-mask')) && !Ext.isEmpty(Ext.get('loading'))){
            Ext.get('loading').hide();
            Ext.get('loading-mask').fadeOut({ opacity: 0, duration: 1500});
        }
        //解决4.2datefield星期显示y的问题
        var proto = Ext.picker.Date.prototype, date = Ext.Date;
        proto.monthNames = date.monthNames;
        proto.dayNames = date.dayNames;
        proto.format = date.defaultFormat;
    })
</script>