package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Say;
import com.dvaren.service.ISayService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/say")
public class SayController {

    @Resource
    private ISayService ISayService;

    @GetMapping("")
    public ResponseResult<PageInfo<Say>> list(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize
    ){
        PageInfo<Say> articlePageInfo = ISayService.querySayList(pageNum, pageSize, 0);
        return ResponseResult.ok(articlePageInfo);
    }

    @GetMapping("/{id}")
    public ResponseResult<Say> querySay(@PathVariable("id") String id) throws ApiException {
        Say say = ISayService.querySay(id,false);
        return ResponseResult.ok(say);
    }
}
