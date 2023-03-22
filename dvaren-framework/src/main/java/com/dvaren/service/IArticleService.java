package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author 47302
* @description 针对表【t_article】的数据库操作Service
* @createDate 2023-01-07 11:57:44
*/
public interface IArticleService extends IService<Article> {

    PageInfo<Article> queryArticleList(String categoryId,int status,int pageNum, int pageSize);

    Article queryArticle(String id,boolean includeHiding) throws ApiException;

    Article addArticle(Article article) throws ApiException;

    Article updateArticle(Article article) throws ApiException;

    void deleteArticle(String id) throws ApiException;

    List<Article> searchByTitleOrLabel(String title, String label,int status);
}
