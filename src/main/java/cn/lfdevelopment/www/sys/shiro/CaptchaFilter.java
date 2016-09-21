/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by LiuFa on 2016/9/21.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
@Component
public class CaptchaFilter extends FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "checkcode-inputEl";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {

        return captchaParam;

    }

    protected String getCaptcha(ServletRequest request) {

        return WebUtils.getCleanParam(request, getCaptchaParam());

    }

    protected UsernamePasswordCaptchaToken createToken(

            ServletRequest request, ServletResponse response) {

        String username = getUsername(request);

        String password = getPassword(request);

        String captcha = getCaptcha(request);

        boolean rememberMe = isRememberMe(request);

        String host = getHost(request);

        return new UsernamePasswordCaptchaToken(username,
                password.toCharArray(), rememberMe, host, captcha);

    }

    @Override
    protected boolean executeLogin(ServletRequest request,
                                   ServletResponse response) throws Exception {
        UsernamePasswordCaptchaToken token = createToken(request, response);
        Subject subject = getSubject(request, response);
        try {
            String captcha = token.getCaptcha();
            String exitCode = (String) subject.getSession().getAttribute("captcha");
            if (null == exitCode || !captcha.equalsIgnoreCase(exitCode)) {
                throw new CaptchaException();
            }
            subject.login(token);//正常验证
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

}
