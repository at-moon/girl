package com.atmoon.vo;

/**
 * http请求返回对象
 */
public class Result<T> {

    private String msg;//返回提示信息

    private Integer code;//返回状态码

    private T data;//具体内容

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
