package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.domain.entity.Log;
import com.dvaren.service.ILogService;
import com.dvaren.mapper.LogMapper;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
* @author 47302
* @description 针对表【t_log】的数据库操作Service实现
* @createDate 2023-01-28 22:07:57
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Resource
    private LogMapper logMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final static String IPS = "ips";

    @Override
    public void addLog(Log log) {
        if(!stringRedisTemplate.hasKey(IPS)){
            stringRedisTemplate.opsForSet().add(IPS, log.getIp());
            stringRedisTemplate.expire(log.getIp(),TextUtil.getSecondsNextEarlyMorning(2) , TimeUnit.SECONDS);
        }
        else{
            stringRedisTemplate.opsForSet().add(IPS, log.getIp());
        }
        Set<String> resultSet = stringRedisTemplate.opsForSet().members(log.getIp());
        if(!stringRedisTemplate.hasKey(log.getIp())){
            stringRedisTemplate.opsForSet().add(log.getIp(), log.getPath());
            stringRedisTemplate.expire(log.getIp(),30 , TimeUnit.SECONDS);
        }
        else{
            stringRedisTemplate.opsForSet().add(log.getIp(), log.getPath());
        }
        if(!resultSet.contains(log.getPath())){
            logMapper.insert(log);
        }
    }

    @Override
    public Set<String> queryTodayIps() {
        Set<String> resultSet = stringRedisTemplate.opsForSet().members(IPS);
        return resultSet;
    }

    @Override
    public PageInfo<Log> queryLogs(String ip, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<Log> logLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!TextUtil.isEmpty(ip)){
            logLambdaQueryWrapper.eq(Log::getIp,ip);
        }
        logLambdaQueryWrapper.orderByDesc(Log::getCreateTime);
        List<Log> logs= logMapper.selectList(logLambdaQueryWrapper);
        return new PageInfo<>(logs);
    }
}




