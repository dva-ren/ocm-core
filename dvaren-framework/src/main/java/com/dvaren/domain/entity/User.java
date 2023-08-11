package com.dvaren.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 角色
     */
    private String roles;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * 签名
     */
    private String introduce;

    /**
     * 网站地址
     */
    private String url;
    /**
     * 网站名称
     */
    private String siteName;
    /**
     * 注册ip
     */
    private Date lastLoginTime;

    /**
     * 登录Ip
     */
    private String lastLoginIp;

    /**
     * ICP号
     */
    private String icp;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 是否删除 0正常 1已删除
     */
    //@JsonIgnore
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}