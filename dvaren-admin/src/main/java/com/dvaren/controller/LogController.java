package com.dvaren.controller;

import com.dvaren.domain.entity.Log;
import com.dvaren.service.ILogService;
import com.dvaren.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private ILogService logService;

    @GetMapping("")
    public ResponseResult<PageInfo<Log>> logs(
            @RequestParam(value = "ip",defaultValue = "") String ip,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize
    ){
        PageInfo<Log> logsPageInfo = logService.queryLogs(ip,pageNum, pageSize);
        return ResponseResult.ok(logsPageInfo);
    }

    @GetMapping("/ips")
    public ResponseResult<Set<String>> ips(){
        return ResponseResult.ok(logService.queryTodayIps());
    }

    @GetMapping("/clearIps")
    public ResponseResult clearIps(){
        logService.clearIps();
        return ResponseResult.ok();
    }
}
