package com.cb.service;

import com.cb.model.entity.Role;

import java.util.List;

/**
 * @description:
 * @author:
 * @create: 2020-11-11 14:34
 **/
public interface RoleService {


    List<Role> getAllRoles();

    Role findRoleByName(String name);
}
