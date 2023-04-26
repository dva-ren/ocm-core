package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.entity.Friends;
import com.dvaren.service.FriendsService;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Resource
    private FriendsService friendsService;

    @GetMapping
    public ResponseResult<List<Friends>> allFriends(){
        List<Friends> friends = friendsService.queryFriends(SystemConstants.NORMAL);
        return ResponseResult.ok(friends);
    }

    @PostMapping
    public ResponseResult addFriend(@RequestBody Friends friends) throws ApiException {
        friendsService.addFriend(friends);
        return ResponseResult.ok();
    }
}
