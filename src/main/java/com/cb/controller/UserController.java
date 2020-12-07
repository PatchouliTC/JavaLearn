package com.cb.controller;

import com.cb.model.entity.User;
import com.cb.security.entity.AuthUser;
import com.cb.service.UserService;
import com.cb.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author:
 * @create: 2020-11-11 14:40
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userSerivce;

    /**
     * 查询用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info")
    public ResponseUtil info() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = userSerivce.findUserById(authUser.getId());
        return ResponseUtil.success(user);
    }
}
