package com.dvaren.mapper;

import com.dvaren.domain.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 47302
* @description 针对表【t_note】的数据库操作Mapper
* @createDate 2023-01-07 11:57:44
* @Entity com.dvaren.domain.entity.Note
*/
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}




