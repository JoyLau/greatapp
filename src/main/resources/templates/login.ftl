<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn" xmlns="http://www.w3.org/1999/html">
<head>
    <#--<meta http-equiv="pragma" content="no-cache">-->
    <#--<meta http-equiv="cache-control" content="no-cache">-->
    <#--<meta http-equiv="cache-control" content="max-age=86400">-->
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <script type="text/javascript">
        var path = '${path}';
        //防止其他页面在iframe或frameset中打开
        if (self != top) {
            top.location = self.location;
        }
        if(top.location.pathname.indexOf(path + '/login')){
            top.location = "${path}/login";
        }
    </script>

    <link href="${path}/static/ext-5.0.0/build/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css" rel="stylesheet"/>
    <link href="${path}/static/css/notification.css" rel="stylesheet"/>
    <link href="${path}/static/css/fontawesome/font-awesome.min.css" rel="stylesheet"/>

    <script src="${path}/static/ext-5.0.0/build/bootstrap.js" type="text/javascript"></script>
    <script src="${path}/static/ext-5.0.0/build/notification.js" type="text/javascript"></script>
    <script src="${path}/static/ext-5.0.0/build/packages/ext-locale/build/ext-locale-zh_CN.js" type="text/javascript"></script>



    <script type="text/javascript">
        Ext.onReady(function () {
            if (Ext.ieVersion < 9 && Ext.ieVersion > 0) {
                alert("您的IE浏览器版本过低,为保证体验，请使用IE9+,chrome,火狐等现代浏览器,或将浏览器切换到极速模式");
                self.opener = null;
                self.close();
            } else if(Ext.ieVersion != 0) {
                Ext.create('widget.uxNotification', {
                    title: '系统通知',
                    position: 'br',
                    iconCls: 'ux-notification-icon-error',
                    cls: 'ux-notification-light',
                    autoCloseDelay: 10000,
                    spacing: 20,
                    html: '系统检测到您的浏览器是IE'+Ext.ieVersion+',考虑到性能以及体验，建议您使用Chrome浏览器'
                }).show();
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
            }
        })
    </script>
    <script src="${path}/static/js/app/common/login.js" type="text/javascript"></script>
    <title>JoyLau - 管理系统登录</title>
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
            font-size: 6px;
            color: #3892d3;
        }

        .footer .center {
            padding-top: 5px;
            width: 100%
        }
        .footer .center a {
            font-size: 12px;
            text-decoration: none;
            color: #3892d3;
        }
    </style>
</head>
<body oncontextmenu="return false;">
<canvas id="canvas"></canvas>
<div class="footer">
    <div class="center">
        <p><a href="http://www.miitbeian.gov.cn/" target="_blank">皖ICP备16025014号-1</a><br />Designed and developed by <a href="http://www.lfdevelopment.cn/" target="_blank">JoyLau</a>.</br>Copyright © 2016-2017 lfdevelopment.cn. All Rights Reserved.</p>
    </div>
</div>
<#--<audio id="tipsMusic" src="${path}/static/music/tip.ogg"></audio>-->
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