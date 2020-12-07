package com.cb.security;

import com.cb.model.entity.Auth;
import com.cb.security.entity.AuthUser;
import com.cb.security.service.AuthUserService;
import com.cb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 用户权限注解处理
 * @author:
 * @create: 2020-11-11 15:07
 **/
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {


    @Autowired
    private UserService userService;

    /**
     * @Description: 判断是否具有权限
     * @param authentication 身份数据
     * @param o 目标路径
     * @param o1  路径权限
     *
     * @return: boolean
     * @Date: 2020/11/11
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        AuthUser authUser=(AuthUser)authentication.getPrincipal();
        Set<String> permissions=new HashSet<String>(); //权限组

        List<Auth> authList=userService.findAuthByUserId(authUser.getId());
        authList.forEach(auth -> {
            permissions.add(auth.getPermission());
        });


        //判断
        if (permissions.contains(permissions.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
