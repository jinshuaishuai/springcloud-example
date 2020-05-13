package com.jin.entity.DO.yc;

import lombok.Data;

@Data
public class UserLoginDO {

    private Integer recId;

    private Integer uId;

    private String username;

    private String loginTime;
    
    private String loginIp;

    private Byte isDelete;

    private String createTime;

    private String updateTime;
}