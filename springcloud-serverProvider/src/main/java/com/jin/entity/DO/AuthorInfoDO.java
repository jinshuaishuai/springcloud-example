package com.jin.entity.DO;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthorInfoDO implements Serializable {

	private static final long serialVersionUID = 3406154078884182110L;
	
	private String name;

    private Byte age;
	
    private String phone;

    private String address;

    private Byte isDelete;

    private String createTime;

    private String updateTime;
}
