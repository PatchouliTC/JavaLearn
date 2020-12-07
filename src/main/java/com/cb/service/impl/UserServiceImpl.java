package com.cb.service.impl;

import com.cb.dao.UserDao;
import com.cb.model.entity.Auth;
import com.cb.model.entity.Role;
import com.cb.model.entity.User;
import com.cb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CommonBackground
 * @description: Auth 实现
 * @author: PatchouliTC
 * @create: 2020-11-04 16:00
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int save(User user) {
        return userDao.insert(user);
    }

    @Override
    public User findUserByUserName(String username) {
        User user= userDao.findUserByName(username);
        return user;
    }

    @Override
    public User findUserById(Long id) {
        return  userDao.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return userDao.findRoleByUserId(userId);
    }

    @Override
    public List<Auth> findAuthByUserId(Long userId) {
        return userDao.findAuthByUserId(userId);
    }

    @Override
    public int addRoletoUser(User user, Role role) {
        return userDao.addRoletoUser(user.getId(),role.getId());
    }
}
