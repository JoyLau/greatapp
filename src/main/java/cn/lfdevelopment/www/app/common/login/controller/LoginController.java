/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.controller;

import cn.lfdevelopment.www.app.common.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LiuFa on 2016/8/5.
 * cn.lfdevelopment.www.app.common.login.controller
 * DevelopmentApp
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping("/test")
    @ResponseBody
    void test (){
        loginService.test("管理员");
    }
}
