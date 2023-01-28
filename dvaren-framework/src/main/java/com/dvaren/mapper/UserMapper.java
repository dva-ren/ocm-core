package com.dvaren.mapper;

import com.dvaren.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 47302
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-01-11 17:55:36
* @Entity com.dvaren.domain/entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




