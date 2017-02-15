/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.ApplicationInitialize;

import cn.lfdevelopment.www.app.common.ueditor.service.UeditorConfigService;
import cn.lfdevelopment.www.app.sys.pojo.SysDic;
import cn.lfdevelopment.www.app.sys.pojo.SysRight;
import cn.lfdevelopment.www.app.sys.service.DicService;
import cn.lfdevelopment.www.app.sys.service.SysRightService;
import cn.lfdevelopment.www.sys.redis.RedisUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuFa on 2016/9/5.
 * cn.lfdevelopment.www.sys.ApplicationInitialize
 * DevelopmentApp
 * 数据初始化
 */
@Component
public class DataInitialize implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger _logger = Logger.getLogger(DataInitialize.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UeditorConfigService ueditorConfigService;

    @Autowired
    private DicService dicService;
    @Autowired
    private SysRightService sysRightService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //root application context
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            setMenuData();
            setUEditorConfig();
        }
    }


    /**
     * 缓存字典数据
     */
    private void setDicData(){
        List<SysDic> dicList = dicService.getdicList();
        redisUtils.set("dicList",dicList);
    }

    /**
     * 缓存权限数据
     */
    private void setMenuData(){
        List<SysRight> rootList = sysRightService.getSysRightRoot();
        List<SysRight> trees = new ArrayList<>();
        for (SysRight sysRight : rootList) {
            if (sysRight.getLeaf()==1)
                continue;
            for (SysRight right : rootList) {
                if (right.getParentid().compareTo(sysRight.getId()) == 0) {
                    sysRight.getChildren().add(right);
                }
            }
            trees.add(sysRight);
        }
        redisUtils.set("menuList", trees);
    }

    /**
     * 缓存UEditor配置信息
     */
    private void setUEditorConfig(){
        redisUtils.set("UEditorConfig",ueditorConfigService.getGloableConfig());
    }
}
