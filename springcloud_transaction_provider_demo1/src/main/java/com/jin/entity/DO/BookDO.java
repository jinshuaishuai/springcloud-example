package com.jin.entity.DO;

import java.io.Serializable;

import lombok.Data;
@Data
public class BookDO implements Serializable {
	
	private static final long serialVersionUID = -478330033118894071L;

	private String bookName;
	
	private Integer bookPrice;
	
	private String address;
	
	private String phone;
	
}
