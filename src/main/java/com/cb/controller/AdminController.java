package com.cb.controller;

import com.cb.model.entity.Role;
import com.cb.model.entity.User;
import com.cb.service.RoleService;
import com.cb.service.UserService;
import com.cb.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author:
 * @create: 2020-11-12 15:29
 **/
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize(value="hasRole('superadmin')")
    @RequestMapping(value = "/list")
    public ResponseUtil list(){
        List<User> userList=userService.getAllUsers();
        return ResponseUtil.success(userList);
    }

    @PreAuthorize(value = "hasAnyRole('admin','superadmin')")
    @RequestMapping(value = "/userrole")
    public ResponseUtil userrole(Long id) {
        if(id==null){
            return ResponseUtil.fail("未提供用戶ID！");
        }
        List<Role> roleList = userService.findRoleByUserId(id);
        return ResponseUtil.success(roleList);
    }

    @PreAuthorize(value = "hasRole('superadmin')")
    @RequestMapping(value = "/roles")
    public ResponseUtil roles() {
        List<Role> roleList = roleService.getAllRoles();
        return ResponseUtil.success(roleList);
    }
}
