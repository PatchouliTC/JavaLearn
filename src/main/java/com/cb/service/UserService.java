package com.cb.service;

import com.cb.model.entity.Auth;
import com.cb.model.entity.Role;
import com.cb.model.entity.User;

import java.util.List;

/**
 * @program: CommonBackground
 * @description: auth
 * @author: PatchouliTC
 * @create: 2020-11-04 15:54
 **/
public interface UserService {


    int save(User user);

    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    User findUserByUserName(String username);
    User findUserById(Long id);

    List<User> getAllUsers();
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    List<Auth> findAuthByUserId(Long userId);

    int addRoletoUser(User user,Role role);

}
