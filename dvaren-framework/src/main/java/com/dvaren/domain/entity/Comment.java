package com.dvaren.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 评论id
     */
    private String ref;

    /**
     * 评论类型
     */
    private String refType;

    /**
     * 评论者头像
     */
    private String avatar;

    /**
     * 评论者
     */
    private String author;

    /**
     * 评论者邮箱
     */
    private String mail;

    /**
     * 评论者网站
     */
    private String url;

    /**
     * 
     */
    private String content;

    /**
     * 评论状态 0已发布 1已读 2未读 3垃圾箱
     */
    private Integer status;

    /**
     * 悄悄话 0否 1是
     */
    private Integer isWhispers;

    /**
     * 评论ip
     */
    private String sendIp;

    /**
     * 评论者定位
     */
    private String location;

    /**
     * 父评论id
     */
    private String parent;

    /**
     * 子评论
     */
    @TableField(exist = false)
    private List<Comment> children;
    /**
     * 评论index (评论楼层)
     */
    private Integer commentsIndex;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 更新时间
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}