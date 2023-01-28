package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.User;
import com.dvaren.domain.entity.UserLogin;
import com.dvaren.service.IUserService;
import com.dvaren.mapper.UserMapper;
import com.dvaren.utils.IpUtil;
import com.dvaren.utils.TextUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
* @author 47302
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-01-11 17:55:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final static String SALT = "Tni-x";

    private final static String BE_HAPPY = "202212091125";

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserById(String id) throws ApiException {
        if(id == null){
            id = BE_HAPPY;
        }
        return userMapper.selectById(id);
    }

    /**
     * 更新用户信息
     * @param user
     * @throws ApiException
     */
    @Override
    public void updateUser(User user) throws ApiException {
        if(TextUtil.isEmpty(user.getId()) || TextUtil.isEmpty(user.getNickname()) || TextUtil.isEmpty(user.getAvatar())){
            throw new ApiException("参数错误");
        }
        User user1 = userMapper.selectById(user.getId());
        if (user1 == null){
            throw new ApiException("用户不存在");
        }
        userMapper.updateById(user);
    }

    @Override
    public User login(UserLogin user, HttpServletRequest request) throws ApiException {
        if(TextUtil.isEmpty(user.getPassword())){
            throw new ApiException("账号或密码不能为空");
        }
        User userInfo;
        if(TextUtil.isEmpty(user.getUsername())) {
            userInfo = userMapper.selectById(BE_HAPPY);
        }else{
            userInfo = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
        }
        String encodePassword = DigestUtils.md5DigestAsHex((user.getPassword() + SALT).getBytes());
        if (userInfo == null || !userInfo.getPassword().equals(encodePassword)){
            throw new ApiException("账号或密码错误");
        }
        User userForm = new User();
        userForm.setId(userInfo.getId());
        userForm.setLastLoginIp(IpUtil.getIpAddr(request));
        userForm.setLastLoginTime(new Date());
        userMapper.updateById(userForm);
        return userInfo;
    }

    @Override
    public void changePassword(String id,String password) throws ApiException {
        if(TextUtil.isEmpty(password) || password.length() < 4 || password.length() > 20){
            throw new ApiException("密码应该包含4-20个字符");
        }
        String encodePassword = DigestUtils.md5DigestAsHex((password + SALT).getBytes());
        User user = new User();
        user.setId(id);
        if(TextUtil.isEmpty(id)){
            user.setId(BE_HAPPY);
        }
        user.setPassword(encodePassword);
        userMapper.updateById(user);
    }


}




