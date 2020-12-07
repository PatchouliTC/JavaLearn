package com.cb.utils;

import com.alibaba.fastjson.JSON;
import com.cb.constant.enums.errorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @program: CommonBackground
 * @description: 请求响应Util
 * @author: PatchouliTC
 * @create: 2020-11-04 17:45
 **/
@Slf4j
@Data
@AllArgsConstructor
public class ResponseUtil {
    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 响应时间
     */
    private String timestamp;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * Response输出Json格式
     *
     * @param response
     * @param data     返回数据
     */
    public static void responseJson(ServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
            out.flush();
        } catch (Exception e) {
            log.error("Response输出Json异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 返回信息
     *
     * @param code 返回编码
     * @param msg  返回消息
     * @param data 返回数据
     * @return
     */
    public static ResponseUtil response(Integer code, String msg, Object data) {
        return new ResponseUtil(code, msg,TimeFormatUtil.localDateTimeNow(),data);
    }

    /**
     * @Description:
     * @param respenum 返回信息
     * @param data 参数负载
     *
     * @return: com.cb.utils.ResponseUtil
     * @Date: 2020/11/10
     */
    public static ResponseUtil response(errorEnum respenum, Object data){
        return new ResponseUtil(respenum.getErrorCode(), respenum.getErrorMsg(),TimeFormatUtil.localDateTimeNow(), data);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseUtil success(Object data) {
        return ResponseUtil.response(errorEnum.SUCCESS, data);
    }

    /**
     * 返回失败
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseUtil fail(Object data) {
        return ResponseUtil.response(errorEnum.FAILED, data);
    }


    public static ResponseUtil internalError(Object data){
        return ResponseUtil.response(errorEnum.INTERNAL_SERVER_ERROR,data);
    }
}
