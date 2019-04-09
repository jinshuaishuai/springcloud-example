package com.jin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {
	
	@Value("${name}")
	private String name;
	
	@Value("${address}")
	private String address;
	
	@PostMapping("/getName")
	public String getName() {
		return name + "  " + address;
	}
}
