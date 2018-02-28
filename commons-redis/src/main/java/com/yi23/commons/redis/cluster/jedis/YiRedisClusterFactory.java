package com.yi23.commons.redis.cluster.jedis;

import com.yi23.commons.redis.YiersanRedis;
import com.yi23.commons.redis.cluster.resource.Clusters;
import com.yi23.commons.redis.cluster.resource.Node;
import com.yi23.commons.redis.exception.RedisRuntimeException;
import com.yi23.commons.redis.exception.ResourceLoaderException;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.*;

public class YiRedisClusterFactory implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(YiRedisClusterFactory.class);
    private static final long serialVersionUID = -6493978613583566349L;
    private static volatile YiRedisClusterFactory instance = null;
    private Map<String,Map<String, YiersanRedis>> applicationRedisMap;

    public static YiRedisClusterFactory getInstance(List<Clusters> clusters){
        if(instance == null){
            synchronized (YiRedisClusterFactory.class){
                if(instance == null){
                    instance = new YiRedisClusterFactory(clusters);
                }
            }
        }
        return instance;
    }

    public YiersanRedis getYiersanRedis(String applicationId, String clusterId){
        return applicationRedisMap.get(applicationId).get(clusterId);
    }

    private YiRedisClusterFactory(List<Clusters> clusters){
        try {
            initApplicationRedis(clusters);
            log.info("*_*=*_*=*_*=*_*=*_*=*_*=*_* 【 Successfully initialize Yi23 Redis application cluster 】 *_*=*_*=*_*=*_*=*_*=*_**_*=*_*");
        } catch (Throwable e) {
            e.printStackTrace();
            Throwable t = new RedisRuntimeException(
                    "Failed to initialize the Redis application cluster ！！！！！！！！！！！！！！！！！！！！！！！！！！！", e);
            throw new ResourceLoaderException(t);
        }
    }

    private void initApplicationRedis(List<Clusters> clusters) {
        Map<String, Map<String, YiersanRedis>> applicationRedisMap = new HashMap<String, Map<String, YiersanRedis>>();
        if(null == clusters){
            Throwable t = new RedisRuntimeException("未传入redis集群配置信息.");
            throw new ResourceLoaderException(t);
        }else {
            for(Clusters cluster : clusters){
                Map<String, YiersanRedis> map = new HashMap<String, YiersanRedis>();
                for(Node node : cluster.getNodes()){
                    map.put(node.getId(),transferClusterInfoToShangpinRedis(cluster, node));
                }
                applicationRedisMap.put(cluster.getId(), map);
            }
            this.applicationRedisMap = applicationRedisMap;
        }
    }

    private YiersanRedis transferClusterInfoToShangpinRedis(Clusters clusterInfo, Node node) {
        YiRedisCluster src = new YiRedisCluster();
        Set<HostAndPort> nodes = getNodes(node);
		GenericObjectPoolConfig poolConfig = getGenericObjectPoolConfig(clusterInfo);
        JedisCluster jc = new JedisCluster(nodes , poolConfig);
        src.setJedisCluster(jc);
        src.setExpireTime(clusterInfo.getExpiretime());
        return src;
    }

    private Set<HostAndPort> getNodes(Node node) {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		for(String host : node.getHostsAndPorts()){
			HostAndPort hostAndPort = new HostAndPort(host.substring(0, host.indexOf(":")), Integer.valueOf(host.substring(host.indexOf(":")+1)));
			nodes.add(hostAndPort);
		}
		return nodes;
	}

	private GenericObjectPoolConfig getGenericObjectPoolConfig(Clusters clusterInfo) {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(clusterInfo.getMaxidle());
		poolConfig.setMaxTotal(clusterInfo.getMaxtotal());
		poolConfig.setMaxWaitMillis(clusterInfo.getMaxwait());
		poolConfig.setTestOnBorrow(clusterInfo.isTestonborrow());
		poolConfig.setTestOnReturn(clusterInfo.isTestonreturn());
		return poolConfig;
	}

}
