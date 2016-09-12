/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.mapper;

import cn.lfdevelopment.www.app.common.login.pojo.SysUser;
import cn.lfdevelopment.www.sys.base.BaseMapper;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.app.common.login.mapper
 * DevelopmentApp
 */
public interface UserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByName(String name);
}
