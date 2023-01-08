package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Article;
import com.dvaren.service.ArticleService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("")
    public ResponseResult<PageInfo<Article>> list(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "",value = "category") String category
    ){
        PageInfo<Article> articlePageInfo = articleService.queryArticleList(category,0,pageNum, pageSize);
        return ResponseResult.ok(articlePageInfo);
    }

    @GetMapping("/{id}")
    public ResponseResult<Article> queryArticle(@PathVariable("id") String id) throws ApiException {
        Article article = articleService.queryArticle(id,false);
        return ResponseResult.ok(article);
    }
}
