package com.cb.dao;


import com.cb.model.entity.Auth;
import com.cb.model.entity.Role;
import com.cb.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserDao extends BaseDao<User> {

    User findUserByName(String username);

    int changePassword(User user);

    int Authorization(User user);

    List<Auth> findAuthByUserId(long id);

    List<Role> findRoleByUserId(long id);

    int addRoletoUser(@Param("uid")long uid, @Param("rid")long rid);
}
