package com.atmoon.enums;

public enum ResultEnum {

    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    HIGH_SCHOOL(100,"你还在上高中吧."),
    WORK(101,"你可能在工作了吧.")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
