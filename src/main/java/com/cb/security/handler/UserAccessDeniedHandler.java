package com.cb.security.handler;

import com.cb.constant.enums.errorEnum;
import com.cb.utils.IpUtil;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @description: 无权限处理
 * @author:
 * @create: 2020-11-11 17:53
 **/
@Component
@Slf4j
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException{
        log.info(MessageFormat.format("访问被拒绝-无权访问，来源:{0}", IpUtil.getIpAddr(request)));
        ResponseUtil.responseJson(response, ResponseUtil.response(errorEnum.NO_PERMISSION, accessDeniedException.getMessage()));

    }
}
