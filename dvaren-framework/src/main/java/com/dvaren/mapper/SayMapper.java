package com.dvaren.mapper;

import com.dvaren.domain.entity.Say;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 47302
* @description 针对表【t_say】的数据库操作Mapper
* @createDate 2023-01-07 11:57:07
* @Entity com.dvaren.domain.entity.Say
*/
@Mapper
public interface SayMapper extends BaseMapper<Say> {

}




