package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Note;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
* @author 47302
* @description 针对表【t_note】的数据库操作Service
* @createDate 2023-01-07 11:57:44
*/
public interface NoteService extends IService<Note> {
    PageInfo<Note> queryNoteList(int pageNum, int pageSize,int status);

    Note queryNote(String id,boolean includeHiding) throws ApiException;

    Note addNote(Note note) throws ApiException;

    Note updateNote(Note note) throws ApiException;

    void deleteNote(String id) throws ApiException;
}
