package com.dvaren.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.domain.entity.Friends;
import com.dvaren.service.FriendsService;
import com.dvaren.mapper.FriendsMapper;
import org.springframework.stereotype.Service;

/**
* @author 025
* @description 针对表【t_friends】的数据库操作Service实现
* @createDate 2023-03-06 18:11:26
*/
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
    implements FriendsService{

}




