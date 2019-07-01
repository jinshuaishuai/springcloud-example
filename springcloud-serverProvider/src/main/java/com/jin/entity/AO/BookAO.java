package com.jin.entity.AO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class BookAO {
	
	@NotBlank(message = "书名不能为空\n")
	private String bookName;
	@NotNull(message = "图书价格不能为空")		//对于数值型类型，不能用@NotBlank和@NotEmpty，只能用@NotNull，不然会报
	/*
	 * "HV000030: No validator could be found for constraint 
	 * 'javax.validation.constraints.NotBlank' validating type 'java.lang.Integer'
	 * 这样的错误
	 */
	@Min(value = 20,message = "价格不能低于20")
	@Range(min = 10, max = 20)
	private Integer bookPrice;
	@NotEmpty(message = "收货地址不能为空\n")
	private String address;
	@NotBlank(message = "手机号不能为空\n")
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号不正确")
	private String phone;
	
	private String author;
	
	@NotBlank(message = "不能为空")
	@CreditCardNumber
	private String remark;
	
	private String press;
	
	private String createTime;
	
	private String updateTime;
	
}
