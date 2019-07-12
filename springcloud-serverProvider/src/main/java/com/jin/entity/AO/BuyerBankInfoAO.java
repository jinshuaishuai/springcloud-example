package com.jin.entity.AO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 买手银行信息AO，用于接收上一层级传递过来的买手银行信息
 * @author rose
 *
 */
@Data
@ToString
@NoArgsConstructor
public class BuyerBankInfoAO {
	
	private String buyerNo;
	
	private String accountName;
	
	private String accountIdNumber;
	
	private String bankName;
	
	private String bankLocation;
	
	private String swiftCode;
	
	private String openAccountCurrency;
	
	private Integer display;
	
	private Integer status;
	
	private String updateTime;
	
	private String createTime;
	
}
