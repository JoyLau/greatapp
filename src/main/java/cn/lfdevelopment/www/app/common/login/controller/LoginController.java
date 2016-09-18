/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.controller;

import cn.lfdevelopment.www.app.common.login.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuFa on 2016/8/5.
 * cn.lfdevelopment.www.app.common.login.controller
 * DevelopmentApp
 */
@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping("/logi66n")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            //用户已经登录过了
            return "/sys/user/sys_login";
        }
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            //注意：此处虽然是未找到该账号，但是仍然要提示用户‘用户名或密码错误’，这是为了确保安全性，防止用户根据提示信息进行暴力登录-@liufa
            error = "用户名或密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            //同理
            error = "用户名或密码错误";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "您的账号已被锁定，请联系管理员";
        } else if (exceptionClassName != null) {
            error = "您的账号存在异常,请联系管理员";
        }
        model.addAttribute("errorMessage", error);
        return "sys_login";
    }



    @RequestMapping("/login/test")
    @ResponseBody
    void test (){
        loginService.test("管理员");
    }


    @RequestMapping("/test")
    @ResponseBody
    String test1 (){
        return "中文";
    }
}
