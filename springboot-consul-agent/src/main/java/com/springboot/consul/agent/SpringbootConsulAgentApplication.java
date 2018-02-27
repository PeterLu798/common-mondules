package com.springboot.consul.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import com.springboot.consul.agent.conf.ClientConf;
import com.springboot.consul.agent.conf.FooConfiguration;

@SpringBootApplication(exclude = {FooConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = ClientConf.class)
@EnableAutoConfiguration
public class SpringbootConsulAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootConsulAgentApplication.class, args);
	}
}
