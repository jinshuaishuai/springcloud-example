package com.jin.service;

import com.jin.service.impl.HelloSerciceHystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "server-provider", fallback = HelloSerciceHystrixImpl.class)		//name属性的值为服务的名称
public interface HelloService {
	@GetMapping("/hello")
	public String hello(@RequestParam String name);	//该方法格式和消费者的Controller方法名保持一致
}
