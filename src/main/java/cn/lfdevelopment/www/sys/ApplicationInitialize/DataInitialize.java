/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.ApplicationInitialize;

import cn.lfdevelopment.www.app.sys.pojo.Sys_dic;
import cn.lfdevelopment.www.app.sys.service.DicService;
import cn.lfdevelopment.www.sys.redis.RedisUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

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
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DicService dicService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //root application context
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            List<Sys_dic> dicList = dicService.getdicList();
            redisUtils.set("dicList",dicList);
        }
    }
}
