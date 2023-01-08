package com.dvaren.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

public class ArticleVo implements Serializable {

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
     * 文章内容
     */
    private String content;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 分类id
     */
    private String categoryName;

    /**
     * 标签
     */
    private String label;

    /**
     * 封面图
     */
    private String cover;

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
