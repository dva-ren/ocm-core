package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Picture;
import com.dvaren.service.IPictureService;
import com.dvaren.utils.ResponseResult;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private IPictureService pictureService;

    @GetMapping
    public ResponseResult<PageInfo<Picture>> picture(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                                     @RequestParam(defaultValue = "20",value = "pageSize") Integer pageSize,
                                                     @RequestParam(defaultValue = "",value = "labels") String labels) throws ApiException {
        PageInfo<Picture> picturePageInfo;
        if (TextUtil.isEmpty(labels)){
            picturePageInfo = pictureService.queryPictureList(pageNum,pageSize);
        }else{
            picturePageInfo = pictureService.queryPictureListByLabels(labels,pageNum,pageSize);
        }
        return ResponseResult.ok(picturePageInfo);

    }
}
