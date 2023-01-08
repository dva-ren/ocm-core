package com.dvaren.enums;

import lombok.Getter;

@Getter
public enum StatusCodeEnum {

    SUCCESS(200,"请求成功"),
    FAILED(400,"请求失败"),
    SERVER_ERROR(500, "服务器错误");

    private final int code;
    private final String msg;

    StatusCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
