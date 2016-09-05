/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;

/**
 * Created by LiuFa on 2016/9/5.
 * cn.lfdevelopment.www.sys.redis
 * DevelopmentApp
 */
@Configuration
public class RedisConfig {
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setTestOnBorrow(Boolean.TRUE);
        return jedisPoolConfig;
    }

    @Bean
    public JedisShardInfo jedisShardInfo() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo("www.lfdevelopment.cn",6379,5000);
        jedisShardInfo.setPassword("123");
        return jedisShardInfo;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool(){
        ArrayList<JedisShardInfo> list = new ArrayList<>();
        list.add(jedisShardInfo());
        return new ShardedJedisPool(jedisPoolConfig(),list);
    }
}