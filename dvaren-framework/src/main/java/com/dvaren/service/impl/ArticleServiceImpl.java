package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.entity.Article;
import com.dvaren.domain.entity.Category;
import com.dvaren.mapper.CategoryMapper;
import com.dvaren.service.IArticleService;
import com.dvaren.mapper.ArticleMapper;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author 47302
* @description 针对表【t_article】的数据库操作Service实现
* @createDate 2023-01-07 11:57:44
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageInfo<Article> queryArticleList(String categoryId,int status,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        if(status != -1){
            articleQueryWrapper.eq(Article::getStatus,status);
        }
        if(!TextUtil.isEmpty(categoryId)){
            articleQueryWrapper.eq(Article::getCategoryId,categoryId);
        }
        articleQueryWrapper.orderByDesc(Article::getCreateTime);
        List<Article> articles = articleMapper.selectList(articleQueryWrapper);
        Map<String,Category> categoryTemp = new HashMap<>();
        List<Article> articleList = articles.stream().map(new Function<Article, Article>() {
            @Override
            public Article apply(Article article) {
                String categoryId = article.getCategoryId();
                if (categoryTemp.containsKey(categoryId)) {
                    article.setCategoryName(categoryTemp.get(categoryId).getName());
                } else {
                    Category category = categoryMapper.selectById(categoryId);
                    if(category == null) return article;
                    article.setCategoryName(category.getName());
                    categoryTemp.put(categoryId, category);
                }
                return article;
            }
        }).collect(Collectors.toList());
        return new PageInfo<>(articleList);
    }

    @Override
    public Article queryArticle(String id,boolean includeHiding) throws ApiException {
        Article article = articleMapper.selectById(id);
        if(article == null || ((!Objects.equals(article.getStatus(), SystemConstants.NORMAL) && !includeHiding))){
            throw new ApiException("文章不存在");
        }
        try {
            article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName());
            return article;
        }catch (Exception ignored){
            return article;
        }
    }

    @Override
    @Transient
    public Article addArticle(Article article) throws ApiException {
        if (TextUtil.isEmpty(article.getTitle()) || TextUtil.isEmpty(article.getContent()) || TextUtil.isEmpty(article.getCategoryId())){
            throw new ApiException("参数错误");
        }
        articleMapper.insert(article);
        return article;
    }

    @Override
    public Article updateArticle(Article article) throws ApiException {
        if (TextUtil.isEmpty(article.getTitle()) || TextUtil.isEmpty(article.getContent()) || TextUtil.isEmpty(article.getCategoryId())){
            throw new ApiException("参数错误");
        }
        articleMapper.updateById(article);
        return article;
    }

    @Override
    public void deleteArticle(String id) throws ApiException {
        if (TextUtil.isEmpty(id)){
            throw new ApiException("id不能为空");
        }
        int i = articleMapper.deleteById(id);
        if(i <= 0){
            throw new ApiException("删除失败");
        }
    }
}




