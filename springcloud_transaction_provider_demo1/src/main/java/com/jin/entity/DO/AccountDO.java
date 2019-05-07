package com.jin.entity.DO;

import java.io.Serializable;

import lombok.Data;
@Data
public class AccountDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer money;
	
}
