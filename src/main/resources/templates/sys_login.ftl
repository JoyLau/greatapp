<html>
<head>
    <title>国泰安薪酬管理实训系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link href="${request.contextPath}/static/css/login.css" rel="stylesheet" />
    <link href="${request.contextPath}/static/css/popup.css" rel="stylesheet"/>
    <script src="${request.contextPath}/static/js/jquery-1.7.2.min.js"></script>
    <script src="${request.contextPath}/static/js/respond.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/js/jquery.artDialog.source.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/js/iframeTools.source.js"></script>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <div class="center">
            <h1 class="logo"><img src="${request.contextPath}/static/images/logo2.png"/></h1>
            <ul>
                <li><a href="${request.contextPath}/anon/aboutMe" target="_blank">关于我们</a></li>
                <li><a href="${request.contextPath}/anon/productIntroduction" target="_blank">产品介绍</a></li>
            </ul>
        </div>
    </div>
    <div class="loginWrap">
        <div class="center">
            <div class="loginBlock">
                <form id="form1" action="${request.contextPath}/login" method="post">
                    <div class="login">
                        <p>用户登录</p>
                        <div class="name">
                            <input type="text" value="${username ??}" class="input" id="username" name="username" maxlength="20"  placeholder="用户名" />
                        </div>
                        <div class="password">
                            <input type="password" value="${password ??}" class="input" id="password" name="password" maxlength="20" placeholder="密码"  onpaste="return false;"/>
                        </div>
                        <input class="btn" type="submit" value="登 录" id="login"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="footer-fixer"></div>
</div>
<div class="footer">
    <div class="center">
        <p>深圳国泰安教育技术股份有限公司</p>
        <p>Copyright  <%=year %>  Shenzhen GTA Education Tech Ltd. All rights  reserved</p>
    </div>
</div>
<script type="text/javascript">
    //防止登录窗口在iframe或frameset中打开
    if (self != top) {
        top.location = self.location;
    }
    $(function () {
        //登陆界面输入框定位
        $(".login .input").each(function () {
            if ($(this).val() != "") {
                $(this).prev("label").hide();
            }
            $(this).focus(function () {
                $(this).addClass("focus");
                $(this).prev("label").addClass("focus");
            });
            $(this).blur(function () {
                $(this).removeClass("focus");
                $(this).prev("label").removeClass("focus");
                if ($(this).val() == "") {
                    $(this).prev("label").show();
                }
            });
            $(this).keyup(function () {
                if ($(this).val() == "") {
                    $(this).prev("label").show();
                }
                else {
                    $(this).prev("label").hide();
                }
            });
        });
        //login fail message
        if('${errorMessage}'){
            artDialog.alert('warning', '操作提示', '${errorMessage}', function () {
            });
        }
    });


    $("#form1").submit(function (e) {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "" || username == "用户名") {
            /* layer.alert("请输入用户名！"); */
            art.dialog.alert('warning', '操作提示', '请输入用户名！', function () {
            });
            //验证失效阻止提交
            e.preventDefault();
            return;
        }
        if (password == "" || password == "密码") {
            /* layer.alert("请输入密码！"); */
            art.dialog.alert('warning', '操作提示', '请输入密码！', function () {
            });
            e.preventDefault();
            return;
        }

        var pattern = new RegExp("[（！!@#￥%……&*（）——+{}：”|》？，。、）\"\'\\\\]");
        if (pattern.test(username)) {
            /* layer.alert("用户名含有特殊字符！"); */
            e.preventDefault();
            return;
        }
        if (pattern.test(password)) {
            /* layer.alert("密码含有特殊字符！"); */
            e.preventDefault();
            return;
        }

    });

    //网页内按下回车触发
    document.onkeydown = function () {
        if (event.keyCode == 13) {
            $("#login").click();
            return false;
        }
    };
</script>
</body>
</html>