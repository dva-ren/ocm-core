package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.domain.entity.Log;
import com.dvaren.service.ILogService;
import com.dvaren.mapper.LogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 47302
* @description 针对表【t_log】的数据库操作Service实现
* @createDate 2023-01-28 22:07:57
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void addLog(Log log) {
        logMapper.insert(log);
    }

    @Override
    public PageInfo<Log> queryLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Log> logs= logMapper.selectList(new LambdaQueryWrapper<Log>().orderByDesc(Log::getCreateTime));
        return new PageInfo<>(logs);
    }
}




