package com.jin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jin.entity.AO.BuyerAssociationInfoAO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/provider1")
@Slf4j
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
		return "hello " + name + "            11111111111111"; 
	}
	
	@PostMapping(value = "/getAO", produces = "application/json")
	public BuyerAssociationInfoAO getAO(@RequestBody BuyerAssociationInfoAO associationInfoAO) {
		log.info("请求入参为：------->{}",associationInfoAO);
		return associationInfoAO;
	}
	
}
