package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.entity.Say;
import com.dvaren.service.ISayService;
import com.dvaren.mapper.SayMapper;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
* @author 47302
* @description 针对表【t_say】的数据库操作Service实现
* @createDate 2023-01-07 11:57:07
*/
@Service
public class SayServiceImpl extends ServiceImpl<SayMapper, Say> implements ISayService {

    @Resource
    private SayMapper sayMapper;

    @Override
    public PageInfo<Say> querySayList(int pageNum, int pageSize,int status) {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<Say> sayLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(status != -1){
            sayLambdaQueryWrapper.eq(Say::getStatus,status);
        }
        sayLambdaQueryWrapper.orderByDesc(Say::getCreateTime);
        List<Say> says = sayMapper.selectList(sayLambdaQueryWrapper);
        return new PageInfo<>(says);
    }

    @Override
    public Say querySay(String id,boolean includeHiding) throws ApiException {
        if (TextUtil.isEmpty(id)){
            throw new ApiException("参数错误");
        }
        Say say = sayMapper.selectById(id);
        if(say == null || (Objects.equals(say.getStatus(), SystemConstants.NORMAL) && !includeHiding)){
            throw new ApiException("查询不到该id");
        }
        return say;
    }

    @Override
    public Say addSay(Say say) throws ApiException {
        if (TextUtil.isEmpty(say.getContent()) || TextUtil.isEmpty(say.getAuthor()) ){
            throw new ApiException("参数错误");
        }
        sayMapper.insert(say);
        return say;
    }

    @Override
    public Say updateSay(Say say) throws ApiException {
        if (TextUtil.isEmpty(say.getId()) || TextUtil.isEmpty(say.getContent()) || TextUtil.isEmpty(say.getAuthor())){
            throw new ApiException("参数错误");
        }
        sayMapper.updateById(say);
        return say;
    }

    @Override
    public void deleteSay(String id) throws ApiException {
        if (TextUtil.isEmpty(id)){
            throw new ApiException("id不能为空");
        }
        int i = sayMapper.deleteById(id);
        if(i <= 0){
            throw new ApiException("删除失败");
        }
    }
}




