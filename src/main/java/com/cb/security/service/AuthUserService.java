package com.cb.security.service;

import com.cb.model.entity.Role;
import com.cb.model.entity.User;
import com.cb.security.entity.AuthUser;
import com.cb.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author:
 * @create: 2020-11-11 14:53
 **/
@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserService userService;


    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);
        if (user != null) {
            AuthUser authUser = new AuthUser();
            BeanUtils.copyProperties(user, authUser);

            //如果查询到了这个用户，那么封装成认证用用户实体，在用户信息基础上获取对应的roles信息，并将这些信息添加进sps的Authorities内作为权限验证凭据
            Set<GrantedAuthority> authorities = new HashSet<>();

            List<Role> roleList = userService.findRoleByUserId(authUser.getId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });

            authUser.setAuthorities(authorities);

            return authUser;
        }
        return null;
    }


}
