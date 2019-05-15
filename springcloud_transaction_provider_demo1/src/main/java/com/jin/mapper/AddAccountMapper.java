package com.jin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jin.entity.DO.AccountDO;

@Mapper
public interface AddAccountMapper {
	
	/**
	 * 加钱接口
	 * @param accountDO1
	 * @return
	 */
	Integer addAccountMoney(AccountDO accountDO);

}
