<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${request.contextPath}/static/ext-5.0.0/build/ext-all.js"></script>
    <script src="${request.contextPath}/static/ext-5.0.0/build/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
    <link href="${request.contextPath}/static/ext-5.0.0/build/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css" rel="stylesheet" />
    <title>Document</title>
</head>
<body>

</body>
</html>
<script type="text/javascript">
    Ext.onReady(function () {
        Ext.MessageBox.alert("提示:", "服务器出错啦...",function () {
            window.close();
        });
    });
</script>