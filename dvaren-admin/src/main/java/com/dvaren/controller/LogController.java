package com.dvaren.controller;

import com.dvaren.domain.entity.Log;
import com.dvaren.service.ILogService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private ILogService logService;

    @GetMapping("")
    public ResponseResult<PageInfo<Log>> logs(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize
    ){
        PageInfo<Log> logsPageInfo = logService.queryLogs(pageNum, pageSize);
        return ResponseResult.ok(logsPageInfo);
    }
}
