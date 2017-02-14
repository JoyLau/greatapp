<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <#--<meta http-equiv="cache-control" content="private">-->
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>JoyLau - 系统管理主面板</title>
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
</head>

<body oncontextmenu="return false;">
<div id="loading-mask"></div>
<div id="loading">
    <div class="loading-indicator">
        <img src="${path}/static/images/desktop/desktoploading.gif">
    </div>
</div>
<#--<div id="flashClock">
    <embed src="${path}/static/flash/20078858402648.swf" quality="high" wmode="transparent" width="160" height="150" align="middle"  type="application/x-shockwave-flash" />
</div>-->
<a target="_blank" alt="Powered by LiuFa"
   id="poweredby"><div></div></a>

</body>
</html>
<link rel="stylesheet" type="text/css" href="${path}/static/js/desktop/resources/ext-theme-neptune/ext-theme-neptune-all.css" />
<link rel="stylesheet" type="text/css" href="${path}/static/css/desktop/desktop.css" />
<link href="${path}/static/css/fontawesome/font-awesome.min.css" rel="stylesheet"/>

<script type="text/javascript" src="${path}/static/js/desktop/js/ext-all.js"></script>
<script type="text/javascript" src="${path}/static/js/desktop/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/static/js/desktop/js/ext-theme-neptune.js"></script>

<#--全局公共js-->
<script type="text/javascript" src="${path}/static/js/app/common/extcommon.js"></script>

<#--插件-->
<script type="text/javascript" src="${path}/static/js/app/plugins/TabCloseMenu.js"></script>
<script type="text/javascript" src="${path}/static/js/app/plugins/ProgressBarPager.js"></script>
<script type="text/javascript" src="${path}/static/js/app/plugins/MaskBinder.js"></script>
<#--<script type="text/javascript" src="/static/js/desktop/js/options-toolbar.js"></script>-->

<script type="text/javascript">
    var basePath = '${path}';
    Ext.setGlyphFontFamily('FontAwesome');

    Ext.Loader.setPath({
        'Ext.ux.desktop': 'static/js/desktop/js',
        Desktop: 'static/js/desktop/app'
    });

    Ext.require('Desktop.App');

    var DesktopApp;
    Ext.onReady(function () {
        Ext.get('loading').hide();
        Ext.get('loading-mask').fadeOut({ opacity: 0, duration: 2500});
        DesktopApp = Ext.create('Desktop.App');
        //解决4.2datefield星期显示y的问题
        var proto = Ext.picker.Date.prototype,date = Ext.Date;
        proto.monthNames = date.monthNames;
        proto.dayNames = date.dayNames;
        proto.format = date.defaultFormat;
    });
</script>