package com.dvaren.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SystemStateVo implements Serializable {

    private Long allComments;

    private Long categories;

    private Long comments;

    private Long link_apply;

    private Long links;

    private Long notes;

    private Long pages;

    private Long posts;

    private Long says;

    private Long recently;

    private Long unreadComments;

    private Long online;

    private Long todayMaxOnline;

    private Long todayOnlineTotal;

    private Long callTime;

    private Long uv;

    private Long todayIpAccessCount;

    private Long unreadFriends;

    private Long friends;
}
