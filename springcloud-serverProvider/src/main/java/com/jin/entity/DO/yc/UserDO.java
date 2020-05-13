package com.jin.entity.DO.yc;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author shuai.jin
 * @description 用户实体类
 * @Date 2020/5/11 16:59
 */
@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = -6138267321932475980L;

    private Integer recId;

    private Integer companyId;

    private Integer deptId;

    private Integer postId;

    private String username;

    private String realName;

    private String password;

    private String phone;

    private String sex;

    private String email;

    private Byte status;

    private Byte isDelete;

    private String createTime;

    private String updateTime;
}
