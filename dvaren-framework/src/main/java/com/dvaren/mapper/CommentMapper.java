package com.dvaren.mapper;

import com.dvaren.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 47302
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2023-01-12 11:13:37
* @Entity com.dvaren.domain.entity.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




