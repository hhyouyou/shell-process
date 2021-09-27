package com.uos.shell.process.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * 统一response体
 *
 * @param <T> 数据类型
 * @author djx
 */
public class ServerResponse<T> implements Serializable {
    private int code;
    private boolean result;
    @JsonProperty(
            defaultValue = ""
    )
    private String message;
    @JsonProperty(
            defaultValue = ""
    )
    private String errMsg;
    private T data;

    private ServerResponse(int code) {
        this.code = code;
        this.result = this.isSuccess();
    }

    private ServerResponse(ResponseCode code) {
        this.code = code.getCode();
        this.result = this.isSuccess();
        this.message = code.getDesc();
    }

    private ServerResponse(ResponseCode code, String errMsg) {
        this.code = code.getCode();
        this.result = this.isSuccess();
        this.message = code.getDesc();
        this.errMsg = errMsg;
    }

    private ServerResponse(int code, T data) {
        this.code = code;
        this.result = this.isSuccess();
        this.data = data;
    }

    private ServerResponse(int code, String msg, T data) {
        this.code = code;
        this.result = this.isSuccess();
        this.message = msg;
        this.data = data;
    }

    private ServerResponse(int code, String msg) {
        this.code = code;
        this.result = this.isSuccess();
        this.message = msg;
    }

    public ServerResponse() {
    }

    public static <T> ServerResponse<T> ok() {
        return new ServerResponse(ResponseCode.SUCCESS);
    }

    public static <T> ServerResponse<T> ok(T data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> ok(String msg, T data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> fail() {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> fail(ResponseCode code) {
        return new ServerResponse(code);
    }

    public static <T> ServerResponse<T> fail(ResponseCode code, String errMsg) {
        ServerResponse<T> response = new ServerResponse();
        response.setCode(code.getCode());
        response.setErrMsg(errMsg);
        response.setMessage(code.getDesc());
        return response;
    }

    public static <T> ServerResponse<T> fail(String message) {
        return new ServerResponse(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> ServerResponse<T> fail(int errorCode, String message) {
        return new ServerResponse(errorCode, message);
    }

    public static <T> ServerResponse<T> build(int code, T data, String message) {
        return new ServerResponse(code, message, data);
    }

    public static <T> ServerResponse<T> fail(int code, String message, String errMsg) {
        ServerResponse<T> response = new ServerResponse();
        response.setCode(code);
        response.setErrMsg(errMsg);
        response.setMessage(message);
        return response;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public boolean getResult() {
        return this.isSuccess();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    @JsonProperty(
            defaultValue = ""
    )
    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    @JsonProperty(
            defaultValue = ""
    )
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
