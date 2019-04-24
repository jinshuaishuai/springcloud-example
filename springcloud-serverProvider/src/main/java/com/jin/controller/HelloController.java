package com.jin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider1")
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
		return "hello " + name + "            11111111111111"; 
	}
	
	@GetMapping("/getProperty")
	public String getProperty(@RequestParam String foo) {
		return foo + "                        1111111111111111111";
	}
}
