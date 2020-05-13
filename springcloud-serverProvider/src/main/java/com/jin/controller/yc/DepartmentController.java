package com.jin.controller.yc;

import com.jin.config.Rest;
import com.jin.config.RestResponse;
import com.jin.entity.AO.yc.DepartmentAO;
import com.jin.entity.DO.yc.DepartmentDO;
import com.jin.entity.query.yc.DepartmentQuery;
import com.jin.service.yc.DepartmentService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author shuai.jin
 * @description 部门信息处理类
 * @Date 2020/5/12 10:58
 */
@RestController
@RequestMapping(value = "/api/department")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "addDepartment")
    public RestResponse<String> addDepartment(@RequestBody DepartmentAO departmentAO) {
        log.info("请求入参为：------>{}", departmentAO);
        try {
            departmentService.addDepartment(departmentAO);
            return RestResponse.success();
        } catch (Exception e) {
            log.error("添加部门记录失败：------>{}", e.toString());
            return RestResponse.error("500", "添加部门失败");
        }
    }

    @PostMapping(value = "updateDepartment")
    public RestResponse<String> updateDepartment(@RequestBody DepartmentAO departmentAO) {
        log.info("请求入参为：------>{}", departmentAO);
        try {
            departmentService.updateDepartment(departmentAO);
            return RestResponse.success();
        } catch (Exception e) {
            log.error("修改部门记录失败：------>{}", e.toString());
            return RestResponse.error("500", "修改部门信息失败");
        }
    }

    @PostMapping(value = "getDepartmentList")
    public RestResponse<List<DepartmentDO>> getDepartmentList(@RequestBody DepartmentQuery departmentQuery) {
        log.info("请求入参为：------>{}", departmentQuery);
        try {
            List<DepartmentDO> result = departmentService.getDepartmentList(departmentQuery);
            return RestResponse.success(result);
        } catch (Exception e) {
            log.error("获取部门列表记录失败：------>{}", e.toString());
            return RestResponse.error("500", "获取部门列表记录失败");
        }
    }
}
