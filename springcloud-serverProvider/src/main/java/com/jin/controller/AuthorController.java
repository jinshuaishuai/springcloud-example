package com.jin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jin.config.RestResponse;
import com.jin.entity.AO.AuthorInfoAO;
import com.jin.service.AuthorInfoServer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author shuai.jin
 * @datetime 2019.07.04 18:11
 * @description 图书作者服务
 *
 */
@RestController
@RequestMapping("/author")
@Slf4j
@Api(value = "图书作者接口",tags = "图书作者接口",protocols = "http")
public class AuthorController {
	
	@Autowired
	private AuthorInfoServer authorInfoService;
	
	@ApiOperation(value = "保存图书作者信息",notes = "")
	@PostMapping(value = "/saveAuthorInfo", produces = "application/json;charset=utf-8")
	public RestResponse<String> saveAuthorInfo(@RequestBody @Valid AuthorInfoAO authorInfoAO,BindingResult result) {
		log.info("请求入参为：---->{}",authorInfoAO);
		
		if(result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for(ObjectError error : result.getAllErrors()) {
				sb.append(error.getDefaultMessage());
			}
			log.error("sb--------->{}",sb.toString());
			return RestResponse.error("500", sb.toString());
		}
		authorInfoService.saveAuthorInfo(authorInfoAO);
		return RestResponse.success("SUCCESS");
	}
}
