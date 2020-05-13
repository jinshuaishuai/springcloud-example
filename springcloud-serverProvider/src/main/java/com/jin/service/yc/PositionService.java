package com.jin.service.yc;

import com.jin.entity.AO.yc.PositionAO;
import com.jin.entity.DO.yc.PositionDO;

import java.util.List;

/**
 * @author shuai.jin
 * @description 职务业务接口
 * @Date 2020/5/12 17:52
 */
public interface PositionService {

    /**
     * 新增一条职务记录
     * @param positionAO
     */
    void addPosition(PositionAO positionAO);

    /**
     * 修改职务记录
     * @param positionAO
     */
    void updatePosition(PositionAO positionAO);

    /**
     * 根据部门id获取职务列表
     * @param deptId
     * @return
     */
    List<PositionDO> getPositionList(int deptId);
}
