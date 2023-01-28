package com.dvaren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.User;
import com.dvaren.domain.entity.UserLogin;

import javax.servlet.http.HttpServletRequest;


/**
* @author 47302
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-01-11 17:55:36
*/
public interface IUserService extends IService<User> {

//    List<User> queryAllUser();

    User queryUserById(String id) throws ApiException;

//    User queryUserByUserName(String username) throws ApiException;

//    void addUser(User user) throws ApiException;

    void updateUser(User user) throws ApiException;

    User login(UserLogin user, HttpServletRequest request) throws ApiException;

    void changePassword(String id,String password) throws ApiException;
}
