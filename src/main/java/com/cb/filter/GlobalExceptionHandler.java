package com.cb.filter;

import com.cb.constant.enums.errorEnum;
import com.cb.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 全局异常拦截器
 * @author:
 * @create: 2020-11-10 17:17
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(NoHandlerFoundException.class)
    public void notFountHandler(HttpServletRequest request, HttpServletResponse resp,NoHandlerFoundException e){
        String path=request.getRequestURI();
        Map<String,String> data=new HashMap<>();
        data.put("url",request.getRequestURI());
        ResponseUtil.responseJson(resp,ResponseUtil.response(errorEnum.NOT_FOUND,data));
    }


    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(Exception e , HttpServletResponse resp) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log.error(MessageFormat.format("意外的异常出现！详情：{0}",sw.toString()));
        ResponseUtil.responseJson(resp,ResponseUtil.internalError(null));
    }

}
