package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.entity.Friends;
import com.dvaren.enums.StatusCodeEnum;
import com.dvaren.service.FriendsService;
import com.dvaren.mapper.FriendsMapper;
import com.dvaren.utils.TextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
* @author 025
* @description 针对表【t_friends】的数据库操作Service实现
* @createDate 2023-03-06 18:11:26
*/
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
    implements FriendsService{

    @Resource
    private FriendsMapper friendsMapper;

    @Override
    public List<Friends> queryFriends(Integer state) {
        LambdaQueryWrapper<Friends> lambdaQueryWrapper = new LambdaQueryWrapper<Friends>();
        if(!Objects.equals(state, SystemConstants.IGNORE)){
            lambdaQueryWrapper.eq(Friends::getState, state);
        }
        lambdaQueryWrapper.orderByDesc(Friends::getCreateTime);
        return friendsMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void addFriend(Friends friends) throws ApiException {
        if(TextUtil.isEmpty(friends.getUrl()) || TextUtil.isEmpty(friends.getName())){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        friends.setState(SystemConstants.HIDDEN);
        friendsMapper.insert(friends);
    }

    @Override
    public void updateFriend(Friends friends) throws ApiException {
        if(TextUtil.isEmpty(friends.getId()) || TextUtil.isEmpty(friends.getUrl()) || TextUtil.isEmpty(friends.getName())){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }

        int i = friendsMapper.updateById(friends);
        if(i <=0 ){
            throw new ApiException("更新失败，该ID不存在");
        }
    }

    @Override
    public void deleteFriend(String id) throws ApiException {
        if(TextUtil.isEmpty(id)){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        int i = friendsMapper.deleteById(id);
        if(i <=0 ){
            throw new ApiException("删除失败，该ID不存在");
        }
    }

    @Override
    public void changeState(Friends f) throws ApiException {
        if(TextUtil.isEmpty(f.getId())){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        Friends friend = new Friends();
        friend.setId(f.getId());
        friend.setState(f.getState());
        int i = friendsMapper.updateById(friend);
        if(i <=0 ){
            throw new ApiException("更新失败，该ID不存在");
        }
    }
}




