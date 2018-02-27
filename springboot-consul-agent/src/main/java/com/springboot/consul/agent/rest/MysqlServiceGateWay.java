package com.springboot.consul.agent.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.consul.agent.conf.FooConfiguration;

@FeignClient(name = "springboot-mysql-service",configuration = FooConfiguration.class)
public interface MysqlServiceGateWay {

	@RequestMapping(value="/mysql/test" ,method=RequestMethod.GET ,consumes = "application/json")
	public void test();
	
}
