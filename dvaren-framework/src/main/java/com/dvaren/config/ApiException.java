package com.dvaren.config;

import com.dvaren.enums.StatusCodeEnum;
import lombok.Getter;

@Getter
public class ApiException extends Throwable {

    static final long serialVersionUID = -338751123124229948L;

    private int code;
    private String message;

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public ApiException(String message) {
        this.code = StatusCodeEnum.FAILED.getCode();
        this.message = message;
    }

    public ApiException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getMsg();
    }
}
