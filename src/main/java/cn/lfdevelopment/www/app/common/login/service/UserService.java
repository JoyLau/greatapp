/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.service;

import cn.lfdevelopment.www.app.common.login.mapper.UserMapper;
import cn.lfdevelopment.www.app.common.login.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.app.common.login.service
 * DevelopmentApp
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public SysUser getUserByName(String name){
        return userMapper.selectUserByName(name);
    }
}
