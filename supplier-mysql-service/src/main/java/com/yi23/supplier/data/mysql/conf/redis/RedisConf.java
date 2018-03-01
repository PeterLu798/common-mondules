package com.yi23.supplier.data.mysql.conf.redis;

import com.yi23.commons.redis.IYiersanRedis;
import com.yi23.commons.redis.cluster.jedis.YiRedisClusterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: redis配置中心 </p>
 * <p>Company www.yi23.net </p>
 * <p>author lubaijiang </p>
 * <p>date 2018/3/1下午5:48 </P>
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConf {

    /**
     * 应用编号，可以配置多个应用
     */
    private static final String applicationId = "supplier-data-mysql-service";
    /**
     * 应用下的集群编号，一个应用下可以有多个集群
     */
    private static final String clusterId = "default";

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 底层封装的jedis API，该对象是线程安全的。
     * @return IYiersanRedis 底层原理使用的是redis3.0集群机制
     */
    @Bean
    public IYiersanRedis yiersanRedis(){
        return YiRedisClusterFactory.getInstance(redisProperties.getClusters()).getYiersanRedis(applicationId, clusterId).getIYiersanRedis();
    }

}
