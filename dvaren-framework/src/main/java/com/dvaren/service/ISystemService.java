package com.dvaren.service;

import com.dvaren.domain.vo.SystemStateVo;

/**
 * @author 47302
 * @description 系统信息相关
 * @createDate 2023-01-07 11:57:07
 */

public interface ISystemService {

    SystemStateVo querySystemState();
}
