package com.jin.controller.yc;

import com.jin.config.RestResponse;
import com.jin.entity.AO.yc.UserAO;
import com.jin.entity.DO.yc.UserDO;
import com.jin.entity.query.yc.UserQuery;
import com.jin.service.yc.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author shuai.jin
 * @description 用户控制类
 * @Date 2020/5/11 16:27
 */
@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "addUser")
    public RestResponse<String> addUser(@RequestBody UserAO userAO) {
        log.info("请求入参为：------>{}", userAO);
        userService.addUser(userAO);
        return RestResponse.success();
    }

    @PostMapping(value = "updateUser")
    public RestResponse<String> updateUser(@RequestBody UserAO userAO) {
        log.info("请求入参为：--->{}", userAO);
        userService.updateUser(userAO);
        return RestResponse.success();
    }

    @PostMapping(value = "getUserList")
    public RestResponse<List<UserDO>> getUserList(@RequestBody UserQuery userQuery) {
        log.info("请求入参为：------>{}", userQuery);
        List<UserDO> result = userService.getUserList(userQuery);
        return RestResponse.success(result);
    }
}
