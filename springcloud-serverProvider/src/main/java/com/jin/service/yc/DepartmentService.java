package com.jin.service.yc;

import com.jin.entity.AO.yc.DepartmentAO;
import com.jin.entity.DO.yc.DepartmentDO;
import com.jin.entity.query.yc.DepartmentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author shuai.jin
 * @date 2020/05/12 11:00
 */
@Service
public interface DepartmentService {

    /**
     * 添加一条部门记录
     * @param departmentAO
     */
    void addDepartment(DepartmentAO departmentAO);

    /**
     * 更新一条部门记录
     * @param departmentAO
     */
    void updateDepartment(DepartmentAO departmentAO);

    /**
     * 获取部门记录列表
     * @param departmentQuery
     * @return
     */
    List<DepartmentDO> getDepartmentList(DepartmentQuery departmentQuery);
}
