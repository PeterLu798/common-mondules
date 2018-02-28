package com.yi23.commons.redis.cluster.jedis;

import com.yi23.commons.redis.IYiersanRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;

public class ClusterRedis implements IYiersanRedis {

    private static final Logger log = LoggerFactory.getLogger(ClusterRedis.class);
    private static final long serialVersionUID = -7226582938942298022L;
    private JedisCluster jedisCluster;
    private int expireTime;

    public ClusterRedis() {
    }

    public ClusterRedis(JedisCluster jedisCluster, int expireTime) {
        this.jedisCluster = jedisCluster;
        this.expireTime = expireTime;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
}
