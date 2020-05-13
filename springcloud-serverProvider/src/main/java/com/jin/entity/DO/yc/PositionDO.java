package com.jin.entity.DO.yc;

import lombok.Data;

/**
 * @author shuai.jin
 * @date 2020/05/12 10:01
 */
@Data
public class PositionDO {

    private Integer recId;

    private String positionName;

    private Integer deptId;

    private Byte status;

    private Byte isDelete;

    private String createTime;

    private String updateTime;

}