package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Say;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author 47302
* @description 针对表【t_say】的数据库操作Service
* @createDate 2023-01-07 11:57:07
*/
public interface SayService extends IService<Say> {
    PageInfo<Say> querySayList(int pageNum, int pageSize,int status);

    Say querySay(String id,boolean includeHiding) throws ApiException;

    Say addSay(Say say) throws ApiException;

    Say updateSay(Say say) throws ApiException;

    void deleteSay(String id) throws ApiException;
}
