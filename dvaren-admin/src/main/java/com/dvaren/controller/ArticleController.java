package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Article;
import com.dvaren.service.IArticleService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService IArticleService;

    @GetMapping("")
    public ResponseResult<PageInfo<Article>> list(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "",value = "category") String category,
            @RequestParam(defaultValue = "-1",value = "status") Integer status
    ){
        PageInfo<Article> articlePageInfo = IArticleService.queryArticleList(category,status,pageNum, pageSize);
        return ResponseResult.ok(articlePageInfo);
    }

    @PostMapping("")
    public ResponseResult<Object> article(@RequestBody Article article) throws ApiException {
        IArticleService.addArticle(article);
        return ResponseResult.ok();
    }
    @GetMapping("/{id}")
    public ResponseResult<Article> queryArticle(@PathVariable("id") String id) throws ApiException {
        Article article = IArticleService.queryArticle(id,true);
        return ResponseResult.ok(article);
    }

    @PostMapping("/{id}")
    public ResponseResult<Article> updateArticle(@PathVariable("id") String id,@RequestBody Article article) throws ApiException {
        article.setId(id);
        Article article1 = IArticleService.updateArticle(article);
        return ResponseResult.ok(article1);
    }

    @PutMapping("/{id}")
    public ResponseResult<Object> deleteArticle(@PathVariable("id") String id) throws ApiException {
        IArticleService.deleteArticle(id);
        return ResponseResult.ok();
    }
}
