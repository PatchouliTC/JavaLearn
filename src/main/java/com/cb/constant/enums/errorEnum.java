package com.cb.constant.enums;

/**
 * @description: 异常返回信息
 * @author:
 * @create: 2020-11-10 16:53
 **/
public enum errorEnum {
    // 数据操作错误定义
    SUCCESS(200, "成功"),
    FAILED(500,"失败"),
    NO_PERMISSION(403,"无权限"),
    NO_AUTH(401,"需要登陆"),
    NOT_FOUND(404, "未找到对应内容"),
    INTERNAL_SERVER_ERROR(500, "内部错误"),
    ;

    /** 错误码 */
    private Integer errorCode;

    /** 错误信息 */
    private String errorMsg;

    errorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
