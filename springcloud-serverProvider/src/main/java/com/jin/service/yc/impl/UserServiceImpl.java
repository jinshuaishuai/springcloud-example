package com.jin.service.yc.impl;

import com.jin.entity.AO.yc.UserAO;
import com.jin.entity.DO.yc.UserDO;
import com.jin.entity.query.yc.UserQuery;
import com.jin.mapper.yc.BiUserInfoMapper;
import com.jin.service.yc.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuai.jin
 * @description no
 * @Date 2020/5/11 16:49
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private BiUserInfoMapper userMapper;

    @Override
    public void addUser(UserAO userAO) {

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userAO, userDO);
        userMapper.addUser(userDO);
    }

    @Override
    public void updateUser(UserAO userAO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userAO, userDO);
        log.info("userDO的值为：------>{}", userDO);
        userMapper.updateUser(userDO);
    }

    @Override
    public List<UserDO> getUserList(UserQuery userQuery) {
        return userMapper.getUserList(userQuery);
    }
}
