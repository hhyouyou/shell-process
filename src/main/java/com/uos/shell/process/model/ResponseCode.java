package com.uos.shell.process.model;

/**
 * @author djx
 * @date 2021/3/23 下午5:15
 */
public enum ResponseCode {
    /**
     * 响应信息
     */
    SUCCESS(200, "请求成功"),
    ERROR(-1, "ERROR"),
    UNAUTHORIZED(401, " Unauthorized"),
    PERMISSION_NOT_ALLOWED(40101, " 权限不足"),
    TOKEN_TIMEOUT(40101, "登录超时"),
    FORBIDDEN(403, "无法执行该请求"),
    NOT_FOUND(404, "URL未找到"),
    NOT_FOUND_RESOURCE(40401, "资源未找到"),
    METHOD_ERROR(405, "不支持的请求方法"),
    PARAMETER_ERROR(406, "输入参数有误"),
    NUMBER_FORMAT(407, "数据类型转换错误"),
    GONE(410, "该资源不可用"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的 content type"),
    INTERNAL_ERROR(500, "系统异常"),
    INTERNAL_SERVICE_ERROR(501, "内服务错误");

    private final int code;
    private final String desc;

    private ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
