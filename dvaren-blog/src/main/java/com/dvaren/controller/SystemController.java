package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.User;
import com.dvaren.service.IUserService;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class SystemController {

    @Resource
    private IUserService IUserService;

    @GetMapping("/master")
    public ResponseResult<User> master() throws ApiException {
        User user = IUserService.queryUserById(null);
        return ResponseResult.ok(user);
    }
}
