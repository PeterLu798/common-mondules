package com.yi23.commons.redis.conf;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class RedisClusterConf {

	@Autowired
	private RedisProperties redisProperties;
	
	@Bean
	public JedisCluster getJedisCluster() {
		Set<HostAndPort> nodes = getNodes();
		GenericObjectPoolConfig poolConfig = getGenericObjectPoolConfig();
		return new JedisCluster(nodes , poolConfig);
	}

	private Set<HostAndPort> getNodes() {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		for(String node : redisProperties.getNodes()){
			HostAndPort hostAndPort = new HostAndPort(node.substring(0, node.indexOf(":")), Integer.valueOf(node.substring(node.indexOf(":")+1)));
			nodes.add(hostAndPort);
		}
		return nodes;
	}

	private GenericObjectPoolConfig getGenericObjectPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(redisProperties.getMaxidle());
		poolConfig.setMaxTotal(redisProperties.getMaxtotal());
		poolConfig.setMaxWaitMillis(redisProperties.getMaxwait());
		poolConfig.setTestOnBorrow(redisProperties.isTestonborrow());
		poolConfig.setTestOnReturn(redisProperties.isTestonreturn());
		return poolConfig;
	}
}
