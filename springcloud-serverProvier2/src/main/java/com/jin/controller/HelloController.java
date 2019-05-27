package com.jin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/provider1")
@Slf4j
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
		log.info("请求入参name值为：--------------->{}", name);
		return "hello " + name + "   22222222222"; 
	}
	
	@GetMapping("/getProperty")
	public String getProperty(@RequestParam String foo) {
		return foo + "             2222222222222";
	}
	
	@PostMapping("/context")
	public String context() {
		return "可以 22222222222";
	}
}
