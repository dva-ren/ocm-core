package com.dvaren.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName t_note
 */
@TableName(value ="t_note")
@NoArgsConstructor
@ToString
@Data
public class Note implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要，简介
     */
    private String summary;

    /**
     * 分类/专栏id
     */
    private String categoryId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 心情
     */
    private String mood;
    /**
     * 位置
     */
    private String position;

    /**
     * 天气
     */
    private String weather;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 音乐 id
     */
    private String musicId;

    /**
     * 是否置顶(0否 1是)
     */
    private Integer isTop;

    /**
     * 状态(0已发布, 1草稿)
     */
    private Integer status;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览量
     */
    private Long viewCount;

    /**
     * 是否允许评论（1是，0否）
     */
    private Integer allowComment;

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
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}