package com.cb.service.impl;

import com.cb.dao.RoleDao;
import com.cb.model.entity.Role;
import com.cb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author:
 * @create: 2020-11-11 14:34
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }
}
