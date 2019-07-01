package com.jin.entity.DO;

import java.io.Serializable;

import lombok.Data;
@Data
public class BookDO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String bookName;
	
	private Integer bookPrice;
	
	private String address;
	
	private String phone;
	
	private String createTime;
	
	private String updateTime;
	
}
