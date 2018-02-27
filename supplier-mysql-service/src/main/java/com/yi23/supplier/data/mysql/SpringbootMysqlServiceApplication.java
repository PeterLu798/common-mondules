package com.yi23.supplier.data.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootMysqlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMysqlServiceApplication.class, args);
	}
}
