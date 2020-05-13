package com.jin.service.yc;

import com.jin.entity.AO.yc.UserAO;
import com.jin.entity.DO.yc.UserDO;
import com.jin.entity.query.yc.UserQuery;

import java.util.List;

/**
 * @author shuai.jin
 * @date 2020/5/11 16:44
 */
public interface UserService {

    /**
     * 添加一条用户记录
     * @param userAO
     */
    void addUser(UserAO userAO);

    /**
     * 更新用户信息
     * @param userAO
     */
    void updateUser(UserAO userAO);

    /**
     * 条件查询用户列表
     * @param userQuery
     * @return
     */
    List<UserDO> getUserList(UserQuery userQuery);
}
