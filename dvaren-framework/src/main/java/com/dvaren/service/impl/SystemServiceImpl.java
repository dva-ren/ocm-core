package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dvaren.constants.SystemConstants;
import com.dvaren.domain.entity.Comment;
import com.dvaren.domain.vo.SystemStateVo;
import com.dvaren.mapper.*;
import com.dvaren.service.ISystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemServiceImpl implements ISystemService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private NoteMapper noteMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SayMapper sayMapper;

    @Resource
    private CommentMapper commentMapper;

    /**
     * 查询系统状态信息
     * @return 系统状态
     */
    @Override
    public SystemStateVo querySystemState() {
        SystemStateVo systemStateVo = new SystemStateVo();
        systemStateVo.setPosts(articleMapper.selectCount(null));
        systemStateVo.setNotes(noteMapper.selectCount(null));
        systemStateVo.setCategories(categoryMapper.selectCount(null));
        systemStateVo.setSays(sayMapper.selectCount(null));
        systemStateVo.setComments(commentMapper.selectCount(null));
        systemStateVo.setUnreadComments(commentMapper.selectCount(new LambdaQueryWrapper<Comment>().eq(Comment::getStatus, SystemConstants.UNREAD)));
        return systemStateVo;
    }


}
