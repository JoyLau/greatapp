/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by LiuFa on 2016/9/5.
 * cn.lfdevelopment.www.sys.redis
 * DevelopmentApp
 */
public interface RedisDataSource {
    ShardedJedis getRedisClient();

    void returnResource(ShardedJedis shardedJedis);
    void returnResource(ShardedJedis shardedJedis, boolean broken);
}
