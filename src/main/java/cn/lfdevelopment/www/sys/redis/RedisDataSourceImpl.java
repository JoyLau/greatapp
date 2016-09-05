/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by LiuFa on 2016/9/5.
 * cn.lfdevelopment.www.sys.redis
 * DevelopmentApp
 */
@Repository
public class RedisDataSourceImpl implements RedisDataSource {

    private final ShardedJedisPool shardedJedisPool;

    @Autowired
    public RedisDataSourceImpl(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    @Override
    public ShardedJedis getRedisClient() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            returnResource(shardJedis);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        if (shardedJedis == null)
            return;
        shardedJedis.close();
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        shardedJedis.close();
    }

}
