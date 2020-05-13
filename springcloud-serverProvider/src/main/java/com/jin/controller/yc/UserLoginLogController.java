package com.jin.controller.yc;

import com.jin.config.RestResponse;
import com.jin.entity.AO.yc.UserLoginAO;
import com.jin.entity.DO.yc.UserLoginDO;
import com.jin.service.yc.UserLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shuai.jin
 * @description 用户登录日志处理类
 * @Date 2020/5/12 17:09
 */
@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserLoginLogController {

    @Autowired
    private UserLoginLogService userLoginLogService;

    @PostMapping(value = "addUserLoginRecord")
    public RestResponse<String> addUserLoginRecord(@RequestBody UserLoginAO userLoginAO) {
        log.info("请求入参为：------>{}", userLoginAO);
        try {
            userLoginLogService.addUserLoginRecord(userLoginAO);
            return RestResponse.success();
        } catch (Exception e) {
            log.error("记录用户登录信息失败：------>{}", userLoginAO);
            return RestResponse.error("500", "用户登录信息记录失败");
        }
    }

    @GetMapping(value = "getUserLoginLogList")
    public RestResponse<List<UserLoginDO>> getUserLoginLogList(@RequestParam int uId) {
        log.info("请求入参为：------>{}", uId);
        List<UserLoginDO> result = userLoginLogService.getUserLoginLogList(uId);
        return RestResponse.success(result);
    }

}
