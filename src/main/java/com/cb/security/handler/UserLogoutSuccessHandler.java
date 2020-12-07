package com.cb.security.handler;

import com.cb.utils.IpUtil;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @description: 登出成功处理
 * @author:
 * @create: 2020-11-12 14:11
 **/
@Component
@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info(MessageFormat.format("登出成功，来源:{0}", IpUtil.getIpAddr(httpServletRequest)));
        SecurityContextHolder.clearContext();
        ResponseUtil.responseJson(httpServletResponse,ResponseUtil.response(200,"Logout success",null));
    }
}
