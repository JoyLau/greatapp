<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
      <link href="${path}/static/ext-5.0.0/build/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css" rel="stylesheet" />

      <script src="${path}/static/ext-5.0.0/build/bootstrap.js" type="text/javascript"></script>
      <script src="${path}/static/ext-5.0.0/build/notification.js" type="text/javascript"></script>
      <script type="text/javascript">
          if('${message!}'){
              Ext.onReady(function () {
                  Ext.create('widget.uxNotification', {
                      position: 't',
                      cls: 'ux-notification-light',
                      closable: false,
                      title: '登陆失败',
                      width : 350,
                      iconCls: 'ux-notification-icon-information',
                      html: '${message!}'
                  }).show();
              })
          }
      </script>
      <#--<script src="${path}/static/ext-5.0.0/build/ext-all.js" type="text/javascript"></script>-->
      <script src="${path}/static/ext-5.0.0/build/packages/ext-locale/build/ext-locale-zh_CN.js" type="text/javascript"></script>
      <script src="${path}/static/js/app/common/login.js" type="text/javascript"></script>
    <title>Login</title>
      <style type="text/css">
          body {
              text-align: center;
              overflow: hidden;
          }
          #instructions ul li {
              list-style-type:disc;
              list-style-position:outside;
              font-size:12px;
              margin:0 0 0 20px;
          }

          /* Icons */
          .ux-notification-icon-information {
              background-image: url('${path}/static/ext-5.0.0/build/packages/ext-theme-crisp/build/resources/images/form/exclamation.png');
          }

          .ux-notification-icon-error {
              background-image: url('${path}/static/images/notification/icon16_error.png');
          }


          /* Using standard theme */
          .ux-notification-window .x-window-body {
              text-align: center;
              padding: 15px 5px 15px 5px;
          }


          /* Custom styling */
          .ux-notification-light .x-window-header {
              background-color: transparent;
          }

          body .ux-notification-light {
              background-image: url('images/fader.png');
          }

          .ux-notification-light .x-window-body {
              text-align: center;
              padding: 15px 5px 20px 5px;
              background-color: transparent;
              border: 0 solid white;
          }
          .footer{position:absolute;left:0;width:100%;height:5%;text-align:center;margin-top:-80px}
          .footer p{font-size:14px;color:#3892d3;}
          .footer .center{padding-top:30px;width: 100%}
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
    window.onload = function () {
        document.body.style.backgroundColor="#f7fafc";
        load();
    }
</script>