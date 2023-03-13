package com.dvaren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Article;
import com.dvaren.domain.entity.Picture;
import com.dvaren.enums.StatusCodeEnum;
import com.dvaren.service.IPictureService;
import com.dvaren.mapper.PictureMapper;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 025
* @description 针对表【t_picture】的数据库操作Service实现
* @createDate 2023-03-09 10:22:08
*/
@Service
public class IPictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements IPictureService {

    @Resource
    private PictureMapper pictureMapper;

    @Override
    public PageInfo<Picture> queryPictureList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize,"create_time desc");
        List<Picture> pictures = pictureMapper.selectList(null);
        return new PageInfo<Picture>(pictures);
    }

    @Override
    public PageInfo<Picture> queryPictureListByLabels(String labels,Integer pageNum,Integer pageSize) throws ApiException {
        if (TextUtil.isEmpty(labels)){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        PageHelper.startPage(pageNum, pageSize,"create_time desc");
        List<Picture> pictures = pictureMapper.selectList(new QueryWrapper<Picture>().like("labels", labels));
        return new PageInfo<>(pictures);
    }

    @Override
    public void addPicture(Picture picture) throws ApiException {
        if (TextUtil.isEmpty(picture.getUrl())){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        if (TextUtil.isEmpty(picture.getNickname())){
            picture.setNickname("匿名");
        }
        pictureMapper.insert(picture);
    }

    @Override
    public Picture queryPictureById(String id) throws ApiException {
        if (TextUtil.isEmpty(id)){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        return pictureMapper.selectById(id);
    }

    @Override
    public void updatePicture(String id,Picture picture) throws ApiException {
        if (TextUtil.isEmpty(id) || TextUtil.isEmpty(picture.getUrl()) || picture.getLabels().isEmpty()){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        picture.setId(id);
        int i = pictureMapper.updateById(picture);
        if (i <= 0){
            throw new ApiException(StatusCodeEnum.FAILED,"不存在该id");
        }
    }

    @Override
    public void deletePictureById(String id) throws ApiException {
        if (TextUtil.isEmpty(id)){
            throw new ApiException(StatusCodeEnum.ARGUMENTS_ERROR);
        }
        int i = pictureMapper.deleteById(id);
        if (i <= 0){
            throw new ApiException(StatusCodeEnum.FAILED,"不存在该id");
        }
    }
}




