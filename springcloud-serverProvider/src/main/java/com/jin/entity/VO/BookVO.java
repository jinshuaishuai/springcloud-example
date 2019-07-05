package com.jin.entity.VO;

import lombok.Data;
@Data
public class BookVO{
	
	private Integer id;
	
	private String bookName;
	
	private Integer bookPrice;
	
	private String address;
	
	private String phone;
	
	private Byte isDelete;
	
	private String createTime;
	
	private String updateTime;
	
}
