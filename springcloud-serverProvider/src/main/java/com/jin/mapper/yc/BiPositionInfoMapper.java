package com.jin.mapper.yc;

import com.jin.entity.DO.yc.PositionDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuai.jin
 * @description 无
 * @Date 2020/5/12 17:59
 */
@Repository
public interface BiPositionInfoMapper {

    void addPosition(PositionDO positionDO);

    void updatePosition(PositionDO positionDO);

    List<PositionDO> getPositionList(int deptId);
}
