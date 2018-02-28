package com.yi23.commons.redis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCluster;

@Component
public class YiRedisTemplate {
	
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key){
		return jedisCluster.get(key);
	} 
	/**
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 */
	public void setex(String key,int seconds, String value){
		jedisCluster.setex(key, seconds, value);
	} 
	/**
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key,String field, String value){
		jedisCluster.hset(key, field, value);
	}
	/**
	 * 
	 * @param key
	 * @param hash
	 */
	public void hmset(String key, Map<String, String> hash){
		jedisCluster.hmset(key, hash);
	}
	/**
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field){
		return jedisCluster.hget(key, field);
	}
	/**
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget (String key, String... fields){
		return jedisCluster.hmget(key, fields);
	}	
}
