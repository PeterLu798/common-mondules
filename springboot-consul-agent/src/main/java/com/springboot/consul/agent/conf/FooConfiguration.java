package com.springboot.consul.agent.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

@Configuration
public class FooConfiguration {

	@Bean
	public ILoadBalancer ribbonLoadBalancer(){
		return new ZoneAwareLoadBalancer<>();
	}
}
