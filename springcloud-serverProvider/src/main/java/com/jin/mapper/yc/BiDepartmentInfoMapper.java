package com.jin.mapper.yc;

import com.jin.entity.DO.yc.DepartmentDO;
import com.jin.entity.query.yc.DepartmentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuai.jin
 * @date 2020/05/12 11:18
 */
@Repository
public interface BiDepartmentInfoMapper {

    void updateDepartment(DepartmentDO departmentDO);

    List<DepartmentDO> getDepartmentList(DepartmentQuery departmentQuery);

    void addDepartment(DepartmentDO departmentDO);
}

