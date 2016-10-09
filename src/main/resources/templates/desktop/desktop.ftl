<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <#--<meta http-equiv="cache-control" content="private">-->
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>Desktop</title>
    <link rel="stylesheet" type="text/css" href="/static/js/desktop/resources/ext-theme-neptune/ext-theme-neptune-all.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/desktop/desktop.css" />
    <link href="/static/css/fontawesome/font-awesome.min.css" rel="stylesheet"/>

    <!-- GC -->
    <!-- <x-compile> -->
    <!-- <x-bootstrap> -->
    <script type="text/javascript" src="/static/js/desktop/js/ext-all.js"></script>
    <script type="text/javascript" src="/static/js/desktop/js/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/desktop/js/ext-theme-neptune.js"></script>
    <script type="text/javascript" src="/static/js/desktop/js/options-toolbar.js"></script>
    <!-- </x-bootstrap> -->
    <script type="text/javascript">
        Ext.Loader.setPath({
            'Ext.ux.desktop': 'static/js/desktop/js',
            Desktop: 'static/js/desktop/app'
        });

        Ext.require('Desktop.App');

        var DesktopApp;
        Ext.onReady(function () {
//            Ext.Loader.setConfig({
//            disableCaching: true
//        });
            DesktopApp = Ext.create('Desktop.App');
        });
    </script>
    <!-- </x-compile> -->
</head>

<body oncontextmenu="return false;">
<div id="flashClock">
    <embed src="/static/flash/20078858402648.swf" quality="high" wmode="transparent" width="160" height="150" align="middle"  type="application/x-shockwave-flash" />
</div>
<a target="_blank" alt="Powered by LiuFa"
   id="poweredby"><div></div></a>

</body>
</html>