package com.jin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jin.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/transferAccount")
	public String transferAccount(@RequestParam int money) {
		log.info("transferAccount接口入参为:---------------->{}", money);
		accountService.transferAccount(money);
		return "方法处理成功";
	}
	
}
