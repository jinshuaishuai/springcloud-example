package com.jin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jin.entity.DO.AccountDO;
import com.jin.entity.DO.WrongAccountDO;

@Mapper
public interface SubAccountMapper {
	
	/**
	 * 减钱接口
	 * @param wrongAccountDO
	 * @return
	 */
	Integer subAccountMoneyWrong(WrongAccountDO wrongAccountDO);
	
	/**
	 * 
	 * @param accountDO
	 * @return
	 */
	Integer subAccountMoneyRight(AccountDO accountDO);

}
