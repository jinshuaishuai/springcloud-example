package com.jin.entity.AO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 买手信息AO，用于封装上一层级传递过来的数据信息
 * @author shuai.jin
 *
 */
@Data
@ToString
@NoArgsConstructor
public class BuyerInfoAO {
	
	private Integer id;
	
	private String buyerNo;

	private String mainBuyerNo;

	private Integer buyerType;
	
	private String mobilePhone;
	
	private String password;
	
	private String avatar;
	
	private String buyerNickname;
	
	private Integer status;
	
	private Integer privacyPolicy;
	
	private Integer countryCode;
	
	private String countryName;
	
	private String token;
	
	private String settlementCurrency;
	
	private String goodsQuoteCurrency;
	
	private String createTime;
	
	private String updateTime;
	
}
