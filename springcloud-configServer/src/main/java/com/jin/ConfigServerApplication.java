package com.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
@SpringBootApplication				//启动SpringBoot注解
@EnableDiscoveryClient				//将服务注册到Eureka中，让Eureka发现该服务
@EnableConfigServer					//启动SpringCloud Config配置服务
public class ConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
