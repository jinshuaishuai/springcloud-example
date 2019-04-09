package com.jin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jin.service.impl.HelloSerciceImpl;

@FeignClient(name = "server-provider", fallback = HelloSerciceImpl.class)		//name熟悉的值为消费者的项目名称
public interface HelloService {
	@GetMapping("/hello")
	public String hello(@RequestParam String name);	//该方法格式和消费者的Controller方法名保持一致
}
