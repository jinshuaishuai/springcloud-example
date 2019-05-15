package com.jin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jin.entity.DO.AccountDO;
import com.jin.mapper.AddAccountMapper;
import com.jin.mapper.SubAccountMapper;
import com.jin.service.AccountService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AddAccountMapper addAccountMapper;
	
	@Autowired
	private SubAccountMapper subAccountMapper;
	
	@Transactional
	@Override
	public void transferAccount(int money) {
		log.info("接口入参为:----------------------->{}", money);
		/**
		 * 利用select @@tx_isolation查询的全局事务隔离级别为REPEATABLE-READ,加@Transactional注解
		 * 情况1:第一条成功,第二条失败,是不是全部都会回滚,会回滚
		 * 情况2:第一条失败,第二条成功,是不是全部都回滚,会回滚
		 * 
		 */
		
		AccountDO accountDO = new AccountDO();
		accountDO.setName("a");
		accountDO.setMoney(money);
		Integer reflectRow1 = addAccountMapper.addAccountMoney(accountDO);
		
		try {
			Thread.sleep(300000);				//睡一分钟,模拟网络延迟
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}			//线程睡一分钟
		
		accountDO.setName("b");
		Integer reflectRow2 = subAccountMapper.subAccountMoneyRight(accountDO);
		
		log.info("reflectRow1和reflectRow2的值分别为:----------------->{},{}",reflectRow1, reflectRow2);
	}

}
