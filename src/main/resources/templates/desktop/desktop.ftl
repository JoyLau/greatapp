<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Desktop</title>
    <link rel="stylesheet" type="text/css" href="/static/js/desktop/resources/css/ext-all-neptune.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/desktop/desktop.css" />

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
            MyDesktop: 'static/js/desktop/app'
        });

        Ext.require('MyDesktop.App');

        var myDesktopApp;
        Ext.onReady(function () {
            myDesktopApp = new MyDesktop.App();
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