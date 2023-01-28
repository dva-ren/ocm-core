package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Comment;
import com.dvaren.service.ICommentService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @GetMapping()
    public ResponseResult<PageInfo<Comment>> comment(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize,
                                     @RequestParam(value="status",defaultValue = "-1") Integer status) throws ApiException {
        PageInfo<Comment> commentPageInfo = commentService.queryComment(status, pageNum, pageSize);
        return ResponseResult.ok(commentPageInfo);
    }

    @PostMapping("/status/{id}/{status}")
    public ResponseResult<Comment> changeStateComment(@PathVariable String id, @PathVariable Integer status) throws ApiException {
        commentService.changeCommentStatus(id,status);
        return ResponseResult.ok();
    }

    @PostMapping("/delete/{id}")
    public ResponseResult<Comment> deleteComment(@PathVariable String id) throws ApiException {
        commentService.delete(id);
        return ResponseResult.ok();
    }
}
