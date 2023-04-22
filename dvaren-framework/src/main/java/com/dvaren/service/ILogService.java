package com.dvaren.service;

import com.dvaren.domain.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.Set;

/**
* @author 47302
* @description 针对表【t_log】的数据库操作Service
* @createDate 2023-01-28 22:07:57
*/
public interface ILogService extends IService<Log> {

    void addLog(Log log);

    PageInfo<Log> queryLogs(String ip, int pageNum, int pageSize);

    Set<String> queryTodayIps();

    void clearIps();
}
