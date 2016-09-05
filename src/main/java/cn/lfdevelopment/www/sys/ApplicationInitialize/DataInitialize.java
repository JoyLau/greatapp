/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.ApplicationInitialize;

import cn.lfdevelopment.www.sys.redis.RedisClientTemplate;
import cn.lfdevelopment.www.sys.redis.RedisUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by LiuFa on 2016/9/5.
 * cn.lfdevelopment.www.sys.ApplicationInitialize
 * DevelopmentApp
 * 数据初始化
 */
@Component
public class DataInitialize implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger _logger = Logger.getLogger(DataInitialize.class);

    private final RedisClientTemplate redisClientTemplate;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    public DataInitialize(RedisClientTemplate redisClientTemplate) {
        this.redisClientTemplate = redisClientTemplate;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //root application context
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            //set stu_experiment
//            readRedisData();

            readAutoRedisData();
        }
    }

    @Cacheable(value = "usercache", keyGenerator = "keyGenerator")
    public String readAutoRedisData() {
        redisUtils.set("applicationMessage", System.getProperty("user.name"));
        System.out.println("redis.template"+redisUtils.get("applicationMessage"));
        return "asda";
    }

    private void readRedisData() {
        redisClientTemplate.set("applicationMessage", System.getProperty("user.name"));
        _logger.info(redisClientTemplate.get("applicationMessage"));
    }
}
