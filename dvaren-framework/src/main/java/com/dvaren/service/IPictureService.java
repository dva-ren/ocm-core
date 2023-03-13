package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author 025
* @description 针对表【t_picture】的数据库操作Service
* @createDate 2023-03-09 10:22:08
*/
public interface IPictureService extends IService<Picture> {

    PageInfo<Picture> queryPictureList(Integer pageNum, Integer pageSize);

    PageInfo<Picture> queryPictureListByLabels(String labels,Integer pageNum,Integer pageSize) throws ApiException;

    void addPicture(Picture picture) throws ApiException;

    Picture queryPictureById(String id) throws ApiException;

    void updatePicture(String id,Picture picture) throws ApiException;

    void deletePictureById(String id) throws ApiException;
}
