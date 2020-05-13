package com.jin.entity.query.yc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shuai.jin
 * @description 用户信息查询类
 * @Date 2020/5/12 10:50
 */
@Data
public class UserQuery implements Serializable {

    private static final long serialVersionUID = 2500214970529304229L;

    private Integer companyId;

    private Integer deptId;

    private Integer postId;
}
