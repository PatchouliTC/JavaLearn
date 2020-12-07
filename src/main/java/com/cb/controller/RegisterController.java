package com.cb.controller;

import com.cb.constant.enums.errorEnum;
import com.cb.model.entity.Role;
import com.cb.model.entity.User;
import com.cb.service.RoleService;
import com.cb.service.UserService;
import com.cb.utils.IpUtil;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * @description:
 * @author:
 * @create: 2020-11-10 17:59
 **/
@RestController
@RequestMapping(value = "/register",method = RequestMethod.GET)
@Slf4j
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/user")
    public ResponseUtil user(HttpServletRequest request,
                             @RequestParam(value="username",required = true) String name,
                             @RequestParam(value="password",required = true) String password){
        if (name == null || password ==null){
            return ResponseUtil.fail("错误的参数类型");
        }
        if(userService.findUserByUserName((name))!=null){
            return ResponseUtil.fail("用户名已被使用");
        }

        User user =new User();
        user.setUsername(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setLoginIp(IpUtil.getIpAddr(request));
        try{
            userService.save(user);
        }catch (Exception e){
            log.error(MessageFormat.format("error happened while save user,{0}",e.getMessage()));
            return ResponseUtil.fail("内部错误");
        }


        if (user.getUsername()=="superadmin"){
            Role role=roleService.findRoleByName("superadmin");
            userService.addRoletoUser(user,role);
        }else{
            Role role=roleService.findRoleByName("normal");
            userService.addRoletoUser(user,role);
        }

        return ResponseUtil.success("用户建立成功");
    }

}
