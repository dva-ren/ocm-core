package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.dto.CommentDto;
import com.dvaren.domain.entity.Comment;
import com.dvaren.service.ICommentService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @GetMapping("/{id}")
    public ResponseResult<PageInfo<Comment>> allComment(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize,
                                     @PathVariable("id") String id) throws ApiException {
        PageInfo<Comment> commentPageInfo = commentService.queryCommentByArticleId(id, pageNum, pageSize);
        return ResponseResult.ok(commentPageInfo);
    }

    @PostMapping
    public ResponseResult<Comment> addComment(@RequestBody CommentDto commentDto, HttpServletRequest request) throws ApiException {
        Comment comment = commentService.addComment(commentDto, request);
        return ResponseResult.ok(comment);
    }
}
