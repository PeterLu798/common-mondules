package com.yi23.commons.redis.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisProperties {

	/**
	 * 最大的活动连接
	 */
	private Integer maxtotal;
	/**
	 * 最大的空闲连接
	 */
	private Integer maxidle;
	/**
	 * 最大的等待时间
	 */
	private Long maxwait;
	/**
	 * 过期时间
	 */
	private Long expiretime;
	/**
	 * 默认超时时间
	 */
	private Long defaulttimeout;
	/**
	 * brrow时提前进行alidate操作
	 */
	private boolean testonborrow;
	/**
	 * return时提前进行alidate操作
	 */
	private boolean testonreturn;
	/**
	 * 集群节点
	 */
	private List<String> nodes;
	
	
	public Integer getMaxtotal() {
		return maxtotal;
	}
	public void setMaxtotal(Integer maxtotal) {
		this.maxtotal = maxtotal;
	}
	public Integer getMaxidle() {
		return maxidle;
	}
	public void setMaxidle(Integer maxidle) {
		this.maxidle = maxidle;
	}
	public Long getMaxwait() {
		return maxwait;
	}
	public void setMaxwait(Long maxwait) {
		this.maxwait = maxwait;
	}
	public Long getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(Long expiretime) {
		this.expiretime = expiretime;
	}
	public Long getDefaulttimeout() {
		return defaulttimeout;
	}
	public void setDefaulttimeout(Long defaulttimeout) {
		this.defaulttimeout = defaulttimeout;
	}
	public boolean isTestonborrow() {
		return testonborrow;
	}
	public void setTestonborrow(boolean testonborrow) {
		this.testonborrow = testonborrow;
	}
	public boolean isTestonreturn() {
		return testonreturn;
	}
	public void setTestonreturn(boolean testonreturn) {
		this.testonreturn = testonreturn;
	}
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}	
	
}
