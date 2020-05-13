package com.jin.entity.AO.yc;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author shuai.jin
 * @date  2020/5/11 16:28
 */
@Data
public class UserAO {

    private Integer recId;

    @NotNull(message = "公司id不能为空")
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

}
