package com.jin.service.yc.impl;

import com.jin.entity.AO.yc.UserLoginAO;
import com.jin.entity.DO.yc.UserLoginDO;
import com.jin.mapper.yc.BiUserLoginInfoMapper;
import com.jin.service.yc.UserLoginLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuai.jin
 * @description 用户登录业务实现类
 * @Date 2020/5/12 17:21
 */
@Service
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Autowired
    private BiUserLoginInfoMapper biUserLoginInfoMapper;

    @Override
    public void addUserLoginRecord(UserLoginAO userLoginAO) {
        UserLoginDO userLoginDO = new UserLoginDO();
        BeanUtils.copyProperties(userLoginAO, userLoginDO);
        biUserLoginInfoMapper.addUserLoginRecord(userLoginDO);
    }

    @Override
    public List<UserLoginDO> getUserLoginLogList(int uId) {
        return biUserLoginInfoMapper.getUserLoginLogList(uId);
    }
}
