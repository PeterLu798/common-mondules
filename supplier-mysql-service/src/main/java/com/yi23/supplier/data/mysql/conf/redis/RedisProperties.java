package com.yi23.supplier.data.mysql.conf.redis;

import com.yi23.commons.redis.cluster.resource.Clusters;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>Description: redis集群配置文件 </p>
 * <p>Company www.yi23.net </p>
 * <p>author lubaijiang </p>
 * <p>date 2018/3/1下午5:49 </P>
 */
@Getter
@Setter
@ConfigurationProperties(prefix = RedisProperties.REDIS_PREFIX)
public class RedisProperties {

    public static final String REDIS_PREFIX = "yi23.redis";

    private List<Clusters> clusters;
}
