/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.ueditor.service;

import cn.lfdevelopment.www.app.common.ueditor.mapper.UeditorConfigMapper;
import cn.lfdevelopment.www.app.common.ueditor.pojo.UeditorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuFa on 2016/10/27.
 * cn.lfdevelopment.www.app.common.ueditor.service
 * DevelopmentApp
 */
@Service
public class UeditorConfigService {
    @Autowired
    private UeditorConfigMapper ueditorConfigMapper;

    public String getGloableConfig() {
        UeditorConfig ueditorConfig = new UeditorConfig();
        ueditorConfig.setId(1);
        return ueditorConfigMapper.selectByPrimaryKey(ueditorConfig).getGlobalConfig();
    }
}
