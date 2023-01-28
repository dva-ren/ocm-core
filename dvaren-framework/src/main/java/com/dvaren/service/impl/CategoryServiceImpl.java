package com.dvaren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Category;
import com.dvaren.service.ICategoryService;
import com.dvaren.mapper.CategoryMapper;
import com.dvaren.utils.TextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 47302
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2023-01-07 13:15:49
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryList() {
        return categoryMapper.selectList(null);
    }

    @Override
    public Category queryCategory(String id) throws ApiException {
        if(TextUtil.isEmpty(id)){
            throw new ApiException("id不能为空");
        }
        Category category = categoryMapper.selectById(id);
        if(category == null)
            throw new ApiException("该分类不存在");
        return category;
    }

    @Override
    public Category addCategory(Category category) throws ApiException {
        if(TextUtil.isEmpty(category.getName())){
            throw new ApiException("参数错误");
        }
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) throws ApiException {
        if(TextUtil.isEmpty(category.getName()) || TextUtil.isEmpty(category.getId())){
            throw new ApiException("参数错误");
        }
        categoryMapper.updateById(category);
        return category;
    }

    @Override
    public void deleteCategory(String id) throws ApiException {
        if(TextUtil.isEmpty(id))
            throw new ApiException("id不能为空");
        int i = categoryMapper.deleteById(id);
        if(i <= 0){
            throw new ApiException("删除失败");
        }
    }
}




