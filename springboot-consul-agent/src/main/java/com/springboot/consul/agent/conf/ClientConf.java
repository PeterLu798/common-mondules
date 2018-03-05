package com.springboot.consul.agent.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class ClientConf {

	@Bean
	Retryer retryer(){
		return Retryer.NEVER_RETRY;
	}
	
}
