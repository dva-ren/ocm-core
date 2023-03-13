package com.dvaren.mapper;

import com.dvaren.domain.entity.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 025
* @description 针对表【t_picture】的数据库操作Mapper
* @createDate 2023-03-09 10:22:08
* @Entity com.dvaren.domain.entity.Picture
*/
@Mapper
public interface PictureMapper extends BaseMapper<Picture> {

}




