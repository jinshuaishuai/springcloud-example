package com.jin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jin.common.config.RestResponse;
import com.jin.common.util.JedisCacheUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于测试自己封装的redis工具类是否可用
 * @author shuai.jin
 * @date	2019.07.23 16:58
 * 
 * @see		
 * @since	1.0.0
 *
 */
@RestController
@RequestMapping("/redis")
@Slf4j
@Api(value = "redis服务接口", tags = "redis服务接口", produces = "http")
public class RedisController {
	/*
	@Autowired
	private JedisCacheUtil jedis;
	
	@ApiOperation(value = "保存一个字符串")
	@GetMapping(value = "/setValue", produces = "application/json;charset=utf-8")
	public void setValue(@RequestParam String str) {
		log.info("入参为：--->{}", str);
		jedis.set(str, str, 0);
	}
	
	@ApiOperation(value = "获取redis中的key保存的值")
	@GetMapping(value = "/getValue", produces = "application/json;charset=utf-8")
	public RestResponse<String> getValue(@RequestParam String str) {
		log.info("入参为：--->{}", str);
		log.info("jedis---->{}", jedis);
		String value = jedis.get(str, 0);
		log.info("value的值为：------->{}", value);
		return RestResponse.success(value);
	}

	 */
}
