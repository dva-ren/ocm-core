package com.dvaren.mapper;

import com.dvaren.domain.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 47302
* @description 针对表【t_log】的数据库操作Mapper
* @createDate 2023-01-28 22:07:57
* @Entity com.dvaren.domain.entity.Log
*/
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}




