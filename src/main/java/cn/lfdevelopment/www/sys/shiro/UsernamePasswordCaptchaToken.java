/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by LiuFa on 2016/9/21.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 * 自定义继承UsernamePasswordToken，添加验证码参数
 */
class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    UsernamePasswordCaptchaToken(String username, char[] password,boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
