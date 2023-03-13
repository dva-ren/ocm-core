package com.dvaren.controller;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Picture;
import com.dvaren.service.IPictureService;
import com.dvaren.utils.ResponseResult;
import com.dvaren.utils.TextUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping
    public ResponseResult addPicture(@RequestBody Picture picture) throws ApiException {
        pictureService.addPicture(picture);
        return ResponseResult.ok();
    }

    @PostMapping("/delete/{id}")
    public ResponseResult deletePicture(@PathVariable String id) throws ApiException {
        pictureService.deletePictureById(id);
        return ResponseResult.ok();
    }

    @PostMapping("/update/{id}")
    public ResponseResult updatePicture(@PathVariable String id ,@RequestBody Picture picture) throws ApiException {
        pictureService.updatePicture(id,picture);
        return ResponseResult.ok();
    }
}
