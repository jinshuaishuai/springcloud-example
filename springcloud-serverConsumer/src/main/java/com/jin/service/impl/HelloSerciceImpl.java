package com.jin.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.jin.service.HelloService;
@Component
public class HelloSerciceImpl implements HelloService {

	@Override
	public String hello(@RequestParam(value = "name") String name) {
		return "server fail " + name;
	}

}
