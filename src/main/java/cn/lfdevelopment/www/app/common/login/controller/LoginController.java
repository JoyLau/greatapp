/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.controller;

import cn.lfdevelopment.www.app.common.login.service.LoginService;
import cn.lfdevelopment.www.common.util.JsonView;
import cn.lfdevelopment.www.common.util.WebUtil;
import cn.lfdevelopment.www.common.util.vcode.Captcha;
import cn.lfdevelopment.www.common.util.vcode.GifCaptcha;
import cn.lfdevelopment.www.sys.shiro.CaptchaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuFa on 2016/8/5.
 * cn.lfdevelopment.www.app.common.login.controller
 * DevelopmentApp
 */
@Controller
public class LoginController {

    private final Logger _logger = LoggerFactory.getLogger(this.getClass());

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            //用户已经登录过了
            modelAndView.setViewName("app/common/main/main");
            return modelAndView;
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
        } else if (CaptchaException.class.getName().equals(exceptionClassName)) {
            error = "验证码错误";
        } else if (exceptionClassName != null) {
            error = "您的账号存在异常,请联系管理员";
        }
        modelAndView.addObject("message", error);
        if(WebUtil.isAjax(request)){
            return JsonView.render(modelAndView, response);
        }
        return modelAndView;
    }

    @RequestMapping("/main")
    public ModelAndView mian(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView){
        modelAndView.setViewName("app/common/main/main");
        if(WebUtil.isAjax(request)){
            modelAndView.addObject("message", "loginSuccess");
            return JsonView.render(modelAndView, response);
        }
        return modelAndView;
    }
    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="/getGifCode",method= RequestMethod.GET)
    public void getGifCode(HttpServletRequest httpServletRequest,HttpServletResponse response){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(150,45,6);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            //会话session
            SecurityUtils.getSubject().getSession().setAttribute("captcha",captcha.text().toLowerCase());
            _logger.info(captcha.text().toLowerCase());
        } catch (Exception e) {
            _logger.error("获取验证码异常：%s",e.getMessage());
        }
    }

    @RequestMapping("/login/test")
    @ResponseBody
    void test (){
        loginService.test("管理员");
    }



    @RequestMapping("/zhihu")
    String zhihu (){
        return "zhihu";
    }
}
