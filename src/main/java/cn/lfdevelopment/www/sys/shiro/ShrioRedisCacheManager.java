/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by LiuFa on 2016/9/13.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
public class ShrioRedisCacheManager extends AbstractCacheManager {
    @Autowired
    private RedisTemplate redisTemplate;

    public ShrioRedisCacheManager(RedisTemplate<byte[], Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Cache<byte[], Object> createCache(String name) throws CacheException {
        return new ShrioRedisCache<byte[], Object>(redisTemplate, name);
    }
}
