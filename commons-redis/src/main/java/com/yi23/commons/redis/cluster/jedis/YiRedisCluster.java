package com.yi23.commons.redis.cluster.jedis;

import com.yi23.commons.redis.IYiersanRedis;
import com.yi23.commons.redis.YiersanRedis;
import com.yi23.commons.redis.exception.RedisRuntimeException;
import redis.clients.jedis.JedisCluster;

public class YiRedisCluster implements YiersanRedis {
    private JedisCluster jedisCluster;
    private IYiersanRedis redis;
    private int expireTime;

    @Override
    public IYiersanRedis getIYiersanRedis() {
        if(null == jedisCluster){
            throw new RedisRuntimeException("jedisCluster is not allowed to be null !");
        }
        return constructShangpinRedis(jedisCluster);
    }

    private IYiersanRedis constructShangpinRedis(JedisCluster jedisCluster) {
        this.redis = new ClusterRedis(jedisCluster, this.expireTime);
        return this.redis;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public IYiersanRedis getRedis() {
        return redis;
    }

    public void setRedis(IYiersanRedis redis) {
        this.redis = redis;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

}
