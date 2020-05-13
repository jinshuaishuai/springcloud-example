package com.jin.entity.query.yc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shuai.jin
 * @description 部门信息查询类
 * @Date 2020/5/12 11:11
 */
@Data
public class DepartmentQuery implements Serializable {

    private static final long serialVersionUID = 3951240443436563683L;

    private Integer companyId;

    private Integer recId;

}
