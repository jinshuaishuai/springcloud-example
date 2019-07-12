package com.jin.entity.AO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author shuai.jin
 *
 */
@Data
@ToString
@NoArgsConstructor
public class BuyerCorporateIdentityInfoAO {
	
	private String buyerNo;
	
	private String companyAddress;
	
	private String companyArea;
	
	private String companyName;
	
	private String companyPhoneNumber;
	
	private String businessLicenseNo;
	
	private String businessLicense;
	
	private String corporateIdentityCard;
	
	private String corporateName;
	
	private String corporatePhoneNumber;
	
	private Integer status;
	
	private Integer servicePolicy;
	
	private String createTime;
	
	private String updateTime;
	
}
