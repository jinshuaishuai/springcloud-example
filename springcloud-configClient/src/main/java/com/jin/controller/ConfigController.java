package com.jin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope		//这个必须要加
@RequestMapping("/config")
public class ConfigController {
	
	@Value("${name}")
	private String name;
	
	@Value("${address}")
	private String address;
	
	@GetMapping("/getName")
	public String getName() {
		return name + "  " + address;
	}
}
