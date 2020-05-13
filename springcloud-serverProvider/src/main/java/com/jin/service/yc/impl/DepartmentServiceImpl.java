package com.jin.service.yc.impl;

import com.jin.entity.AO.yc.DepartmentAO;
import com.jin.entity.DO.yc.DepartmentDO;
import com.jin.entity.query.yc.DepartmentQuery;
import com.jin.mapper.yc.BiDepartmentInfoMapper;
import com.jin.service.yc.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author shuai.jin
 * @description 部门信息实现类
 * @Date 2020/5/12 11:15
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private BiDepartmentInfoMapper departmentMapper;

    @Override
    public void addDepartment(DepartmentAO departmentAO) {
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentAO, departmentDO);
        log.info("departmentDO的值为：------>{}", departmentDO);
        departmentMapper.addDepartment(departmentDO);
    }

    @Override
    public void updateDepartment(DepartmentAO departmentAO) {
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentAO, departmentDO);
        log.info("departmentDO的值为：------>{}", departmentDO);
        departmentMapper.updateDepartment(departmentDO);
    }

    @Override
    public List<DepartmentDO> getDepartmentList(DepartmentQuery departmentQuery) {
        List<DepartmentDO> departmentDOList = departmentMapper.getDepartmentList(departmentQuery);
        return departmentDOList;
    }
}
