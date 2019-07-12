package com.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableDiscoveryClient		//开启服务发现
@EnableScheduling			//开启定时任务扫描
public class ServerProviderApplication1 {
	public static void main(String[] args) {
		SpringApplication.run(ServerProviderApplication1.class, args);
	}
}
