package com.cb.security.filter;

import com.cb.config.JWTConfig;
import com.cb.security.entity.AuthUser;
import com.cb.security.utils.JWTTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: JWT权限认证
 * @author:
 * @create: 2020-11-11 15:13
 **/
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {
        //get token
        String token=request.getHeader(JWTConfig.tokenHeader);

        if(token!=null&& token.startsWith(JWTConfig.tokenPrefix)){
            AuthUser authUser= JWTTokenUtil.parseAccessToken(token);
            if(authUser != null){
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                        authUser,authUser.getId(),authUser.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}

