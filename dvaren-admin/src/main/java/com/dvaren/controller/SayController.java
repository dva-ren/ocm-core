package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Say;
import com.dvaren.service.SayService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/say")
public class SayController {

    @Resource
    private SayService sayService;

    @GetMapping("")
    public ResponseResult<PageInfo<Say>> list(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize
    ){
        PageInfo<Say> articlePageInfo = sayService.querySayList(pageNum, pageSize, 0);
        return ResponseResult.ok(articlePageInfo);
    }

    @PostMapping("")
    public ResponseResult<Object> Say(@RequestBody Say say) throws ApiException {
        sayService.addSay(say);
        return ResponseResult.ok();
    }
    @GetMapping("/{id}")
    public ResponseResult<Say> querySay(@PathVariable("id") String id) throws ApiException {
        Say say = sayService.querySay(id,true);
        return ResponseResult.ok(say);
    }

    @PostMapping("/{id}")
    public ResponseResult<Say> updateSay(@PathVariable("id") String id,@RequestBody Say say) throws ApiException {
        say.setId(id);
        Say sayRes = sayService.updateSay(say);
        return ResponseResult.ok(sayRes);
    }

    @PutMapping("/{id}")
    public ResponseResult<Object> deleteSay(@PathVariable("id") String id) throws ApiException {
        sayService.deleteSay(id);
        return ResponseResult.ok();
    }
}
