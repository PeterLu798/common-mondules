package com.yi23.supplier.data.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
//@EnableDiscoveryClient
public class SpringbootMysqlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMysqlServiceApplication.class, args);
	}
}
