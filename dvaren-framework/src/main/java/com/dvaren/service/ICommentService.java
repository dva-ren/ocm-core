package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.dto.CommentDto;
import com.dvaren.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 47302
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2023-01-12 11:13:37
*/
public interface ICommentService extends IService<Comment> {
    Comment addComment(CommentDto comment, HttpServletRequest http) throws ApiException;

    PageInfo<Comment> queryCommentByArticleId(String articleId,int pageNum, int pageSize) throws ApiException;

    PageInfo<Comment> queryComment(Integer status,int pageNum, int pageSize) throws ApiException;

    void changeCommentStatus(String id, Integer status) throws ApiException;

    void delete(String commentId) throws ApiException;

    void batchDeletion(List<String> ids) throws ApiException;
}
