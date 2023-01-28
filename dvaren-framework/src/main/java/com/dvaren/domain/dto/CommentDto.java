package com.dvaren.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName t_comment
 */
@Data
public class CommentDto implements Serializable {
    /**
     * 
     */
    @TableId
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
     * 父评论id
     */
    private String parent;

    /**
     * 
     */
    private String content;
    /**
     * 悄悄话 0否 1是
     */
    private Integer isWhispers;
    /**
     * 评论index (评论楼层)
     */
    private Integer commentsIndex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}