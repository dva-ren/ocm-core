package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Article;
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

    @PostMapping("")
    public ResponseResult<Object> Note(@RequestBody Note note) throws ApiException {
        noteService.addNote(note);
        return ResponseResult.ok();
    }
    @GetMapping("/{id}")
    public ResponseResult<Note> queryNote(@PathVariable("id") String id) throws ApiException {
        Note note = noteService.queryNote(id,true);
        return ResponseResult.ok(note);
    }

    @PostMapping("/{id}")
    public ResponseResult<Note> updateNote(@PathVariable("id") String id,@RequestBody Note note) throws ApiException {
        note.setId(id);
        Note noteRes = noteService.updateNote(note);
        return ResponseResult.ok(noteRes);
    }

    @PutMapping("/{id}")
    public ResponseResult<Object> deleteNote(@PathVariable("id") String id) throws ApiException {
        noteService.deleteNote(id);
        return ResponseResult.ok();
    }
}
