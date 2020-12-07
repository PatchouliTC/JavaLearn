package com.cb.security.handler;

import com.cb.security.entity.AuthUser;
import com.cb.security.utils.JWTTokenUtil;
import com.cb.utils.IpUtil;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 登陆成功处理
 * @author:
 * @create: 2020-11-12 14:06
 **/
@Component
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        log.info(MessageFormat.format("登陆成功，来源:{0}", IpUtil.getIpAddr(httpServletRequest)));
        AuthUser authUser=(AuthUser)authentication.getPrincipal();
        String token= JWTTokenUtil.createAccessToken(authUser);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        ResponseUtil.responseJson(httpServletResponse,ResponseUtil.response(200,
                "login success",tokenMap));
    }
}
