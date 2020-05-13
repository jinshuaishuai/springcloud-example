package com.jin.entity.AO.yc;

import lombok.Data;

import java.util.Date;

/**
 * @author shuai.jin
 * @date 2020/05/12 10:01
 */
@Data
public class PositionAO {

    private Integer recId;

    private String positionName;

    private Integer deptId;

    private Byte status;

}