package com.dvaren.domain.dto;
import lombok.Data;

/**
 * json字段信息
 *
 * @author LiTing
 * @date 2021/2/26 11:08
 **/
@Data
public class JsonFiledInfo {
    public static final String OBJECT_TYPE = "object";
    public static final String ARRAY_TYPE = "array";
    private String type = OBJECT_TYPE;
    private String field;
}
