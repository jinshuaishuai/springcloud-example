package com.jin.service.yc;

import com.jin.entity.AO.yc.UserLoginAO;
import com.jin.entity.DO.yc.UserLoginDO;

import java.util.List;

/**
 * @author shuai.jin
 * @description 用户登录日志记录接口
 * @Date 2020/5/12 17:16
 */
public interface UserLoginLogService {

    /**
     * 记录用户登录信息
     * @param userLoginAO
     */
    void addUserLoginRecord(UserLoginAO userLoginAO);

    /**
     * 根据用户id获取用户的登录日志信息
     * @param uId
     * @return
     */
    List<UserLoginDO> getUserLoginLogList(int uId);
}
