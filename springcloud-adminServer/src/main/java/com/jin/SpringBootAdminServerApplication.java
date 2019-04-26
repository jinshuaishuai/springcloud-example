package com.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableDiscoveryClient			//开启服务发现
@EnableAdminServer				//开启SpringBoot Admin 服务
public class SpringBootAdminServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerApplication.class, args);
	}
}
