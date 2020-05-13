package com.jin.entity.DO.yc;

import lombok.Data;

/**
 * @author shuai.jin
 * @date 2020/05/12 9:58
 */
@Data
public class DepartmentDO {

    private Integer recId;

    private Integer parentDeptId;

    private String deptName;

    private Integer companyId;

    private Byte status;

    private Byte isDelete;

    private String createTime;

    private String updateTime;

}