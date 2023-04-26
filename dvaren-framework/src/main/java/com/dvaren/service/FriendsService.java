package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Friends;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
* @author 025
* @description 针对表【t_friends】的数据库操作Service
* @createDate 2023-03-06 18:11:26
*/
public interface FriendsService extends IService<Friends> {
    List<Friends> queryFriends(Integer state);

    void addFriend(Friends friends) throws ApiException;

    void updateFriend(Friends friends) throws ApiException;

    void deleteFriend(String id) throws ApiException;

    void changeState(Friends friends) throws ApiException;
}
