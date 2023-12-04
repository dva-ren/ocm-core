package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.dto.CommentDto;
import com.dvaren.domain.entity.Comment;
import com.dvaren.enums.StatusCodeEnum;
import com.dvaren.service.ICommentService;
import com.dvaren.mapper.CommentMapper;
import com.dvaren.utils.BeanCopUtils;
import com.dvaren.utils.IpUtil;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @author 47302
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2023-01-12 11:13:37
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Comment addComment(CommentDto commentDto, HttpServletRequest http) throws ApiException {
        if (TextUtil.isEmpty(commentDto.getRef()) || TextUtil.isEmpty(commentDto.getAuthor()) || TextUtil.isEmpty(commentDto.getContent())) {
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        Comment comment = BeanCopUtils.copyBean(commentDto, Comment.class);
        comment.setSendIp(IpUtil.getIpAddr(http));
        commentMapper.insert(comment);
        return commentMapper.selectById(comment.getId());
    }

    @Override
    public PageInfo<Comment> queryCommentByArticleId(String articleId,int pageNum, int pageSize) throws ApiException {
        if (TextUtil.isEmpty(articleId)) {
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getRef, articleId);
        commentLambdaQueryWrapper.orderByDesc(Comment::getCreateTime);
        commentLambdaQueryWrapper.ne(Comment::getStatus,SystemConstants.SPAM);
        List<Comment> comments = commentMapper.selectList(commentLambdaQueryWrapper);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);

        for (Comment comment : commentPageInfo.getList()) {
            if(TextUtil.isEmpty(comment.getParent())){
                List<Comment> children = comment.getChildren();
                if(children == null){
                    children = new ArrayList<Comment>();
                }
                for (Comment c : comments) {
                    if(comment.getId().equals(c.getParent())){
                        children.add(c);
                    }
                }
                comment.setChildren(children);
            }
        }
        return commentPageInfo;
    }

    @Override
    public PageInfo<Comment> queryComment(Integer status, int pageNum, int pageSize) throws ApiException {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(status != null && status != -1){
            commentLambdaQueryWrapper.eq(Comment::getStatus, status);
        }
        commentLambdaQueryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> comments = commentMapper.selectList(commentLambdaQueryWrapper);
        return new PageInfo<>(comments);
    }

    @Override
    public void changeCommentStatus(String id, Integer status) throws ApiException {
        if (TextUtil.isEmpty(id) || status == null || status < 0 || status >3) {
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        Comment comment = new Comment();
        comment.setId(id);
        comment.setStatus(status);
        int i = commentMapper.updateById(comment);
        if(i<=0){
            throw new ApiException(StatusCodeEnum.FAILED);
        }
    }

    @Override
    public void delete(String commentId) throws ApiException {
        if (TextUtil.isEmpty(commentId)){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        Comment comment = commentMapper.selectById(commentId);
        if (comment == null) {
            throw new ApiException(StatusCodeEnum.FAILED);
        }
        commentMapper.deleteById(commentId);

    }

    @Override
    @Transactional(rollbackFor = {ApiException.class})
    public void batchDeletion(List<String> ids) throws ApiException {
        for (String id : ids) {
            this.delete(id);
        }
    }
}




