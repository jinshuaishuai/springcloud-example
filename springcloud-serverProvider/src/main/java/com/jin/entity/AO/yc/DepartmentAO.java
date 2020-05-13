package com.jin.entity.AO.yc;

import lombok.Data;

import java.util.Date;

/**
 * @author shuai.jin
 * @date 2020/05/12 9:58
 */
@Data
public class DepartmentAO {

    private Integer recId;

    private Integer parentDeptId;

    private String deptName;

    private Integer companyId;

    private Byte status;

    private Byte isDelete;

}