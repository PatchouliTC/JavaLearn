package com.cb.security.handler;

import com.cb.constant.enums.errorEnum;
import com.cb.utils.IpUtil;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @description: 未登录处理
 * @author:
 * @create: 2020-11-11 17:58
 **/
@Component
@Slf4j
public class UserNotLoginHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e)
            throws IOException, ServletException {
        log.info(MessageFormat.format("未登录访问，来源:{0}", IpUtil.getIpAddr(request)));
        ResponseUtil.responseJson(response, ResponseUtil.response(errorEnum.NO_AUTH, e.getMessage()));
    }
}
