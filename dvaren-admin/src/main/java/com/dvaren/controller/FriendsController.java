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
    public ResponseResult<List<Friends>> allFriends(@RequestParam(value = "state", defaultValue = "-1") Integer state){
        List<Friends> friends = friendsService.queryFriends(state);
        return ResponseResult.ok(friends);
    }

    @PostMapping
    public ResponseResult addFriend(@RequestBody Friends friends) throws ApiException {
        friendsService.addFriend(friends);
        return ResponseResult.ok();
    }

    @PostMapping("/update")
    public ResponseResult updateFriend(@RequestBody Friends friends) throws ApiException {
        friendsService.updateFriend(friends);
        return ResponseResult.ok();
    }

    @PostMapping("delete/{id}")
    public ResponseResult removeFriend(@PathVariable String id) throws ApiException {
        friendsService.deleteFriend(id);
        return ResponseResult.ok();
    }

    @PostMapping("change")
    public ResponseResult changeState(@RequestBody Friends friends) throws ApiException {
        friendsService.changeState(friends);
        return ResponseResult.ok();
    }
}
