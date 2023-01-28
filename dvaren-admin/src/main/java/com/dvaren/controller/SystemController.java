package com.dvaren.controller;

import com.dvaren.Annotation.IgnoreAuth;
import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.User;
import com.dvaren.domain.entity.UserLogin;
import com.dvaren.domain.vo.SystemStateVo;
import com.dvaren.domain.vo.UserVo;
import com.dvaren.service.ISystemService;
import com.dvaren.service.IUserService;
import com.dvaren.utils.JWTUtil;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.dvaren.utils.BeanCopUtils.copyBean;

@RestController
@RequestMapping
public class SystemController {

    @Resource
    private ISystemService ISystemService;

    @Resource
    private IUserService IUserService;

    @GetMapping("/state")
    public ResponseResult<SystemStateVo> state(){
        return ResponseResult.ok(ISystemService.querySystemState());
    }

    @IgnoreAuth
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLogin userLogin, HttpServletRequest request) throws ApiException {
        User user = IUserService.login(userLogin,request);
        // 生成token
        Map<String, String> payload = new HashMap<>();
        String token = JWTUtil.generateToken(payload);
        Map<String,Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg","登录成功");
        res.put("token",token);
        res.put("data",user);
        return res;
    }
    @GetMapping("/master")
    public ResponseResult<User> master() throws ApiException {
        User user = IUserService.queryUserById(null);
        return ResponseResult.ok(user);
    }
    @PostMapping("/master")
    public ResponseResult<User> updateMaster(@RequestBody UserVo userVo) throws ApiException {
        User user = copyBean(userVo, User.class);
        IUserService.updateUser(user);
        return ResponseResult.ok(user);
    }

    @PostMapping("/password")
    public ResponseResult<SystemStateVo> password(@RequestBody UserLogin userLogin) throws ApiException {
        IUserService.changePassword(null,userLogin.getPassword());
        return ResponseResult.ok();
    }
}
