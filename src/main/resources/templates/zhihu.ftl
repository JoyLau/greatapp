<#assign path=request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit" />
    <meta name="description" content="一个真实的网络问答社区，帮助你寻找答案，分享知识。"/>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>知乎 - 与世界分享你的题目、经验和见解</title>


    <link rel="stylesheet" href="${path}/static/js/zhihu/main.css">
    <script src="${path}/static/js/zhihu/instant.js"></script>
</head>
<body class="zhi ">



<div class="index-main">
    <div class="index-main-body">
        <div class="index-header">

            <h1 class="logo hide-text">知乎</h1>

            <h2 class="subtitle">与世界分享你的题目、经验和见解</h2>
        </div>

        <div class="desk-front sign-flow clearfix sign-flow-simple">

            <div class="index-tab-navs">
                <div class="navs-slider">
                    <a href="#signup" class="active">注册</a>
                    <a href="#signin">登录</a>
                    <span class="navs-slider-bar"></span>
                </div>
            </div>


            <div class="view view-signin" data-za-module="SignInForm">
                <form method="POST">
                    <input type="hidden" name="_xsrf" value="a38ce4d044863f3b79f712f2aac602fa"/>
                    <div class="group-inputs">

                        <div class="email input-wrapper">

                            <input type="text" name="account" aria-label="手机号或邮箱" placeholder="手机号或邮箱" required>
                        </div>
                        <div class="input-wrapper">
                            <input type="password" name="password" aria-label="密码" placeholder="密码" required>
                        </div>

                        <div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">
                            <div class="Captcha-operate">
                                <input type="hidden" name="captcha" required data-rule-required="true" data-msg-required="请点击图中倒立的文字">
                                <input type="hidden" name="captcha_type" value="cn" required>
                                <label class="Captcha-prompt">请点击图中倒立的文字</label>
                                <span class="Captcha-refresh js-refreshCaptcha sprite-index-icon-refresh"></span>
                            </div>
                            <div class="Captcha-imageConatiner">
                                <img class="Captcha-image" alt="验证码" >
                            </div>
                        </div>

                    </div>
                    <div class="button-wrapper command">
                        <button class="sign-button submit" type="submit">登录</button>
                    </div>
                    <div class="signin-misc-wrapper clearfix">
                        <label class="remember-me">
                            <input type="checkbox" name="remember_me" checked value="true"> 记住我
                        </label>
                        <a class="unable-login" href="#">无法登录?</a>
                    </div>

                    <div class="social-signup-wrapper" data-za-module="SNSSignIn">
                        <span class="name js-toggle-sns-buttons">社交帐号登录</span>

                        <div class="sns-buttons">
                            <a title="微信登录" class="js-bindwechat" href="#"><i class="sprite-index-icon-wechat"></i></a>
                            <a title="微博登录" class="js-bindweibo" href="#"><i class="sprite-index-icon-weibo"></i></a>
                            <a title="QQ 登录" class="js-bindqq" href="#"><i class="sprite-index-icon-qq"></i></a>
                        </div>


                    </div>

                </form>

                <div class="QRCode">
                    <button class="QRCode-toggleButton">
                        <span class="sprite-global-icon-qrcode"></span>
                        <span class="QRCode-toggleButtonText ">下载 App</span>
                    </button>
                    <div class="QRCode-card">
                        <div class="QRCode-image"></div>
                        <div class="sprite-index-icon-arrow"></div>
                    </div>
                </div>


            </div>
            <div class="view view-signup selected" data-za-module="SignUpForm">
                <form class="zu-side-login-box" action="/register/email" id="sign-form-1" autocomplete="off" method="POST">
                    <input type="password" hidden>
                    <input type="hidden" name="_xsrf" value="a38ce4d044863f3b79f712f2aac602fa"/>
                    <div class="group-inputs">


                        <div class="name input-wrapper">
                            <input required type="text" name="fullname" aria-label="姓名" placeholder="姓名">
                        </div>
                        <div class="email input-wrapper">

                            <input required type="text" class="account" name="phone_num" aria-label="手机号（仅支持中国大陆）" placeholder="手机号（仅支持中国大陆）">

                        </div>

                        <div class="input-wrapper">
                            <input required type="password" name="password" aria-label="密码" placeholder="密码（不少于 6 位）" autocomplete="off">
                        </div>

                        <div class="input-wrapper captcha-module" data-type="en" >
                            <input id="captcha" name="captcha" placeholder="验证码" required data-rule-required="true" data-msg-required="请填写验证码">
                            <div class="captcha-container">

                                <img class="js-refreshCaptcha captcha" width="120" height="30" data-tooltip="s$t$看不清楚？换一张"  alt="验证码">
                            </div>
                        </div>

                    </div>

                    <div class="button-wrapper command">
                        <button class="sign-button submit" type="submit">注册</button>
                    </div>
                </form>


                <div class="QRCode">
                    <button class="QRCode-toggleButton">
                        <span class="sprite-global-icon-qrcode"></span>
                        <span class="QRCode-toggleButtonText ">下载 App</span>
                    </button>
                    <div class="QRCode-card">
                        <div class="QRCode-image"></div>
                        <div class="sprite-index-icon-arrow"></div>
                    </div>
                </div>



            </div>
        </div>
    </div>

</div>
<div class="footer">
    <span>&copy; 2016 by LiuFa</span>
    <span class="dot">·</span>
    <a target="_blank" href="/roundtable">圆桌</a>
    <span class="dot">·</span>
    <a target="_blank" href="/explore" data-za-c="explore" data-za-a="visit_explore" data-za-l="home_bottom_explore">发现</a>
    <span class="dot">·</span>
    <a target="_blank" href="/app">移动应用</a>
    <span class="dot">·</span>


    <a href="/org/signin" class="footer-mobile-show">使用机构帐号登录</a>

    <span class="dot footer-mobile-show">·</span>

    <a href="/contact" class="footer-mobile-show">联系我们</a>
    <span class="dot">·</span>
    <a target="_blank" href="/careers">寻求合作</a>
    <span class="dot">·</span>
</div>




<script src="${path}/static/js/zhihu/vendor.js"></script>
<script src="${path}/static/js/zhihu/base.js"></script>

<script src="${path}/static/js/zhihu/common.js"></script>
<script src="${path}/static/js/zhihu/page-index.js"></script>
<meta name="entry" content="ZH.entrySignPage" data-module-id="page-index">


</body>
</html>