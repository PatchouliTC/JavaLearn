package com.cb.security.handler;

import com.cb.constant.enums.errorEnum;
import com.cb.utils.IpUtil;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @description: 登录失败处理
 * @author:
 * @create: 2020-11-12 14:09
 **/
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        log.info(MessageFormat.format("登录失败，来源:{0}", IpUtil.getIpAddr(httpServletRequest)));
        ResponseUtil.responseJson(httpServletResponse,ResponseUtil.response(errorEnum.FAILED,e.getMessage()));
    }
}
