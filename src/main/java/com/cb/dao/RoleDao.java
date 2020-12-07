package com.cb.dao;

import com.cb.model.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    Role findRoleByName(String name);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}