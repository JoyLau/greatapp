/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.service;

import cn.lfdevelopment.www.app.common.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuFa on 2016/8/5.
 * cn.lfdevelopment.www.app.common.login.service
 * DevelopmentApp
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    @Autowired
    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }


    @Override
    public void test(String i) {
        loginMapper.test(i);

    }
}
