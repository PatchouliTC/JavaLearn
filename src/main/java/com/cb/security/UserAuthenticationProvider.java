package com.cb.security;

import com.cb.security.entity.AuthUser;
import com.cb.security.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @description: 用户登录验证处理
 * @author:
 * @create: 2020-11-11 14:59
 **/
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthUserService authUserService;

    /**
     * @Description: 用户验证
     * @param authentication 认证数据
     *
     * @return: org.springframework.security.core.Authentication
     * @Date: 2020/11/11
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal(); // 用户名
        String password = (String) authentication.getCredentials(); // 密码

        AuthUser authUser=authUserService.loadUserByUsername(username);
        if (authUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        if (!new BCryptPasswordEncoder().matches(password, authUser.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }
        if (!authUser.getAvailable()) {
            throw new LockedException("用户已禁用");
        }
        return new UsernamePasswordAuthenticationToken(authUser, password, authUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
