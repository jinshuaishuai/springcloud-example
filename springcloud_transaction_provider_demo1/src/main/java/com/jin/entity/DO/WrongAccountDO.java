package com.jin.entity.DO;

import java.io.Serializable;

import lombok.Data;
@Data
public class WrongAccountDO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String money;
	
	
}
