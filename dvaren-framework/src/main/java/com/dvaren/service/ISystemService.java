package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.vo.SystemStateVo;

import java.util.Map;

/**
 * @author 47302
 * @description 系统信息相关
 * @createDate 2023-01-07 11:57:07
 */

public interface ISystemService {

    SystemStateVo querySystemState();

    Map<String, Object> top(Integer size) throws ApiException;
}
