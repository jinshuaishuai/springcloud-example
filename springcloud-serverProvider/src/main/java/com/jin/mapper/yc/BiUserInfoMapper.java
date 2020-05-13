package com.jin.mapper.yc;

import com.jin.entity.DO.yc.UserDO;
import com.jin.entity.query.yc.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuai.jin
 * @date 2020/05/12 10:46
 */
@Repository
public interface BiUserInfoMapper {

    void addUser(UserDO userDO);

    void updateUser(UserDO userDO);

    List<UserDO> getUserList(UserQuery userQuery);
}
