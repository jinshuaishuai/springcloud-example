package com.jin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
	
	@GetMapping("/sellercenter")
	public String fallback() {
		return "商家服务繁忙，请稍后重试";
	}
	
	@PostMapping("/sellercenter")
	public String postFallback() {
		return "商家服务繁忙，请稍后重试";
	}
}
