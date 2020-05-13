package com.jin.entity.AO.yc;

import lombok.Data;

import java.util.Date;

@Data
public class UserLoginAO {

    private Integer recId;

    private Integer uId;

    private String username;

    private String loginTime;

    private String loginIp;

}