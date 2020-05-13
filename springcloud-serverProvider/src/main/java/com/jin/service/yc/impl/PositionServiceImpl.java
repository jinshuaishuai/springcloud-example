package com.jin.service.yc.impl;

import com.jin.entity.AO.yc.PositionAO;
import com.jin.entity.DO.yc.PositionDO;
import com.jin.mapper.yc.BiPositionInfoMapper;
import com.jin.service.yc.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuai.jin
 * @description TODO
 * @Date 2020/5/12 17:56
 */

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {

    @Autowired
    private BiPositionInfoMapper biPositionInfoMapper;

    @Override
    public void addPosition(PositionAO positionAO) {
        PositionDO positionDO = new PositionDO();
        BeanUtils.copyProperties(positionAO, positionDO);
        log.info("positionDO的值为：------>{}", positionDO);
        biPositionInfoMapper.addPosition(positionDO);
    }

    @Override
    public void updatePosition(PositionAO positionAO) {
        PositionDO positionDO = new PositionDO();
        BeanUtils.copyProperties(positionAO, positionDO);
        log.info("positionDO的值为：------>{}", positionDO);
        biPositionInfoMapper.updatePosition(positionDO);
    }

    @Override
    public List<PositionDO> getPositionList(int deptId) {
        return biPositionInfoMapper.getPositionList(deptId);
    }
}
