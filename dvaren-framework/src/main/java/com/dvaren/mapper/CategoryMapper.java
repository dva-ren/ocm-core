package com.dvaren.mapper;

import com.dvaren.domain.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 47302
* @description 针对表【t_category】的数据库操作Mapper
* @createDate 2023-01-07 13:15:49
* @Entity com.dvaren.domain.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




