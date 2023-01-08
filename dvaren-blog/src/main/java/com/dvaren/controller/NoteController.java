package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Note;
import com.dvaren.service.NoteService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @GetMapping("")
    public ResponseResult<PageInfo<Note>> list(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize
    ){
        PageInfo<Note> articlePageInfo = noteService.queryNoteList(pageNum, pageSize, 0);
        return ResponseResult.ok(articlePageInfo);
    }

    @GetMapping("/{id}")
    public ResponseResult<Note> queryNote(@PathVariable("id") String id) throws ApiException {
        Note note = noteService.queryNote(id,false);
        return ResponseResult.ok(note);
    }
}
