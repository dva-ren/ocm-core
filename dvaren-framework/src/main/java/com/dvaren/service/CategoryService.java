package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 47302
* @description 针对表【t_category】的数据库操作Service
* @createDate 2023-01-07 13:15:49
*/
public interface CategoryService extends IService<Category> {

    List<Category> queryCategoryList();

    Category queryCategory(String id) throws ApiException;

    Category addCategory(Category category) throws ApiException;

    Category updateCategory(Category category) throws ApiException;

    void deleteCategory(String id) throws ApiException;
}
