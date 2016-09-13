<html xmlns="http://www.w3.org/1999/xhtml"
>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
<head>
    <title>Hello World!</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data" id="form" action="${request.contextPath}/upload">
    <p>文件：<input type="file" name="file" /></p>
    <p><input type="submit" value="上传" /></p>
</form>
</body>
</html>
<script>
    window.onunload = function () {

    }
</script>