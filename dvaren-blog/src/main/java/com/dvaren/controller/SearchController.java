package com.dvaren.controller;

import com.dvaren.domain.entity.Article;
import com.dvaren.domain.entity.Note;
import com.dvaren.service.IArticleService;
import com.dvaren.service.INoteService;
import com.dvaren.service.impl.ESNoteRepositoryImpl;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private INoteService noteService;

    @Resource
    private IArticleService articleService;

    @GetMapping
    public ResponseResult<Map<String,Object>> search(@RequestParam(value = "title",required = false) String title,
                                                     @RequestParam(value = "label",required = false) String label
    ){
        Map<String, Object> result = new HashMap<>();
        List<Note> notes = noteService.searchByTitleOrLabel(title, label);
        List<Article> articles = articleService.searchByTitleOrLabel(title, label);
        result.put("post",articles);
        result.put("note",notes);
        return ResponseResult.ok(result);
    }
}
