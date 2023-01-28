package com.dvaren.controller;

import com.dvaren.domain.entity.Category;
import com.dvaren.service.ICategoryService;
import com.dvaren.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ICategoryService ICategoryService;

    @GetMapping("")
    public ResponseResult<List<Category>> list(){
        return ResponseResult.ok(ICategoryService.queryCategoryList());
    }
}
