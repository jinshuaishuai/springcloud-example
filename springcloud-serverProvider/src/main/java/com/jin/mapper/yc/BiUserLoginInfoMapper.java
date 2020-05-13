package com.jin.mapper.yc;

import com.jin.entity.DO.yc.UserLoginDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuai.jin
 * @description 无
 * @Date 2020/5/12 17:24
 */
@Repository
public interface BiUserLoginInfoMapper {

    void addUserLoginRecord(UserLoginDO userLoginDO);

    List<UserLoginDO> getUserLoginLogList(int uId);
}
