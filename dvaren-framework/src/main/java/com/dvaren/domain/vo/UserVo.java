package com.dvaren.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class UserVo implements Serializable {
    private String id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 用户昵称
     */
    private String nickname;


    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * icp
     */
    private String icp;

    /**
     * 网站名称
     */
    private String siteName;


    /**
     * 签名
     */
    private String introduce;

    /**
     * 面板地址
     */
    private String url;

    /**
     * 社交账号
     */
    private String socialIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}