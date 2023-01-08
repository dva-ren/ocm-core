package com.dvaren.utils;

import com.dvaren.enums.StatusCodeEnum;
import lombok.Data;

@Data
public class ResponseResult<T> {

    private int code;
    private String msg;
    private T data;

    public ResponseResult() {
    }
    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <O> ResponseResult<O> ok(){
        return new ResponseResult<>(StatusCodeEnum.SUCCESS.getCode(),StatusCodeEnum.SUCCESS.getMsg(),null);
    }

    public static <O> ResponseResult<O> ok(O data){
        return new ResponseResult<O>(StatusCodeEnum.SUCCESS.getCode(),StatusCodeEnum.SUCCESS.getMsg(),data);
    }

    public static <O> ResponseResult<O> failed(){
        return new ResponseResult<>(StatusCodeEnum.FAILED.getCode(),StatusCodeEnum.FAILED.getMsg(),null);
    }

}
