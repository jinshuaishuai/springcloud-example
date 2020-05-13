package com.jin.controller.yc;

import com.jin.config.Rest;
import com.jin.config.RestResponse;
import com.jin.entity.AO.yc.PositionAO;
import com.jin.entity.DO.yc.PositionDO;
import com.jin.service.yc.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shuai.jin
 * @description 职务处理类
 * @Date 2020/5/12 17:28
 */

@RestController
@RequestMapping(value = "/api/position")
@Slf4j

public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping(value = "/addPosition")
    public RestResponse<String> addPosition(@RequestBody PositionAO positionAO) {
        log.info("请求入参为：------>{}", positionAO);
        try {
            positionService.addPosition(positionAO);
            return RestResponse.success();
        } catch (Exception e) {
            log.error("新增职务信息失败：------>{}", e.toString());
            return RestResponse.error("500", "新增职务信息失败");
        }
    }

    @PostMapping(value = "updatePosition")
    public RestResponse<String> updatePosition(@RequestBody PositionAO positionAO) {
        log.info("请求入参为：------>{}", positionAO);
        try {
            positionService.updatePosition(positionAO);
            return RestResponse.success();
        } catch (Exception e) {
            log.error("更新职务信息失败：------>{}", e.toString());
            return RestResponse.error("500", "更新职务信息失败");
        }
    }

    @GetMapping(value = "getPositionList")
    public RestResponse<List<PositionDO>> getPositionList(@RequestParam int deptId) {
        log.info("请求入参为：------>{}", deptId);
        List<PositionDO> result = positionService.getPositionList(deptId);
        return RestResponse.success(result);
    }
}
