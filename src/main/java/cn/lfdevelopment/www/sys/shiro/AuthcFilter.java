/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by LiuFa on 2016/9/22.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
public class AuthcFilter extends FormAuthenticationFilter {
    //前台验证码参数
    private static final String DEFAULT_CAPTCHA_PARAM = "checkcode-inputEl";

    private String getCaptchaParam() {
        return DEFAULT_CAPTCHA_PARAM;
    }

    private String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    AuthcFilter() {
        setUsernameParam("username");
        setPasswordParam("password");
        setRememberMeParam("rememberMe");
        setLoginUrl("/login");
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request,response,"/main");
        return false;
    }

    @Override
    protected UsernamePasswordCaptchaToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new UsernamePasswordCaptchaToken(username,password.toCharArray(), rememberMe, host, captcha);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception{
        UsernamePasswordCaptchaToken token = createToken(request, response);
        Subject subject = getSubject(request, response);
        try {
            //页面验证码
            String captcha = token.getCaptcha();
            //后台验证码
            String exitCode = (String) subject.getSession().getAttribute("captcha");
            //跑出自定义的验证码异常，方便在LoginController接受
            if (null == exitCode || !captcha.equalsIgnoreCase(exitCode)) {
                throw new CaptchaException();
            }
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }
}
