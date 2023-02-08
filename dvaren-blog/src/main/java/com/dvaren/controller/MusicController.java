package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Song;
import com.dvaren.service.IMusicService;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    private IMusicService musicService;

    @GetMapping("/song")
    public ResponseResult<Song> song(@RequestParam(defaultValue = "",value = "id") String id) throws ApiException {
        return ResponseResult.ok(musicService.getSong(id));
    }
}
