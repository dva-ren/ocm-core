package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.entity.Note;
import com.dvaren.service.INoteService;
import com.dvaren.mapper.NoteMapper;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
* @author 47302
* @description 针对表【t_note】的数据库操作Service实现
* @createDate 2023-01-07 11:57:44
*/
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public PageInfo<Note> queryNoteList(int pageNum, int pageSize,int status) {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<Note> noteLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(status != -1){
            noteLambdaQueryWrapper.eq(Note::getStatus, status);
        }
        noteLambdaQueryWrapper.orderByDesc(Note::getCreateTime);
        List<Note> notes = noteMapper.selectList(noteLambdaQueryWrapper);
        return new PageInfo<>(notes);
    }

    @Override
    public Note queryNote(String id,boolean includeHiding) throws ApiException {
        Note note = noteMapper.selectById(id);
        if(note == null || (!Objects.equals(note.getStatus(), SystemConstants.NORMAL) && !includeHiding)){
            throw new ApiException("该note不存在");
        }
        return note;
    }

    @Override
    public Note addNote(Note note) throws ApiException {
        if(TextUtil.isEmpty(note.getTitle()) || TextUtil.isEmpty(note.getContent())){
            throw new ApiException("参数错误");
        }
        noteMapper.insert(note);
        return note;
    }

    @Override
    public Note updateNote(Note note) throws ApiException {
        if(TextUtil.isEmpty(note.getTitle()) || TextUtil.isEmpty(note.getContent()) || TextUtil.isEmpty(note.getId())){
            throw new ApiException("参数错误");
        }
        noteMapper.updateById(note);
        return note;
    }

    @Override
    public void deleteNote(String id) throws ApiException {
        if(TextUtil.isEmpty(id)){
            throw new ApiException("参数错误");
        }
        int i = noteMapper.deleteById(id);
        if(i <= 0){
            throw new ApiException("删除失败");
        }
    }
}




