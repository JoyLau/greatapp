<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <link href="/static/ext-5.0.0/build/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css"
          rel="stylesheet"/>
    <link href="/static/css/notification.css" rel="stylesheet"/>

    <script src="/static/ext-5.0.0/build/bootstrap.js" type="text/javascript"></script>
    <script src="/static/ext-5.0.0/build/notification.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.onReady(function () {
            if (Ext.ieVersion < 9 && Ext.ieVersion > 0) {
                alert("您的IE浏览器版本过低,为保证体验，请使用IE9+,chrome,火狐等现代浏览器,或将浏览器切换到极速模式");
                self.opener = null;
                self.close();
            }
            if ('${message!}') {
                Ext.create('widget.uxNotification', {
                    position: 't',
                    cls: 'ux-notification-light',
                    closable: false,
                    title: '登陆失败',
                    width: 350,
                    iconCls: 'ux-notification-icon-information',
                    html: '${message!}'
                }).show();
                Ext.get('tipsMusic').dom.play();
            }
        })
    </script>
    <script src="/static/ext-5.0.0/build/packages/ext-locale/build/ext-locale-zh_CN.js"
            type="text/javascript"></script>
    <script src="/static/js/app/common/login.js" type="text/javascript"></script>
    <title>Login</title>
    <style type="text/css">
        body {
            text-align: center;
            overflow: hidden;
        }

        .footer {
            position: absolute;
            left: 0;
            width: 100%;
            height: 5%;
            text-align: center;
            margin-top: -80px
        }

        .footer p {
            font-size: 14px;
            color: #3892d3;
        }

        .footer .center {
            padding-top: 30px;
            width: 100%
        }
    </style>
</head>
<body oncontextmenu="return false;">
<canvas id="canvas"></canvas>
<div class="footer">
    <div class="center">
        <p>Copyright (c) 2016 by LiuFa. All rights reserved</p>
    </div>
</div>
<input id="errorMessage" type="hidden" value="${message!}"/>
<audio id="tipsMusic" src="/static/music/tip.ogg"></audio>
</body>
</html>

<script type="text/javascript">
    //定义画布宽高和生成点的个数
    var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 45;

    var canvas = document.getElementById('canvas');
    canvas.width = WIDTH;
    canvas.height = HEIGHT;
    var context = canvas.getContext('2d');
    context.strokeStyle = 'rgba(0,0,0,0.03)';
    context.strokeWidth = 3;
    context.fillStyle = 'rgba(0,0,0,0.05)';
    var circleArr = [];
    //调用执行
    document.body.style.backgroundColor = "#f7fafc";
    load();
</script>