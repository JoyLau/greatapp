/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by LiuFa on 2016/9/21.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 * 验证码异常
 */
public class CaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    CaptchaException() {

        super();

    }
}
