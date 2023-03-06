package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.User;
import com.dvaren.service.ISystemService;
import com.dvaren.service.IUserService;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping
public class SystemController {

    @Resource
    private IUserService IUserService;

    @Resource
    private ISystemService systemService;

    @GetMapping("/master")
    public ResponseResult<User> master() throws ApiException {
        User user = IUserService.queryUserById(null);
        return ResponseResult.ok(user);
    }

    @GetMapping("/top")
    public ResponseResult<Map<String, Object>> top(@RequestParam(value = "size", defaultValue = "4") Integer size) throws ApiException {
        Map<String, Object> top = systemService.top(size);
        return ResponseResult.ok(top);
    }
}
