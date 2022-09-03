package com.imooc.reader.controller;

import com.imooc.reader.entity.Category;
import com.imooc.reader.service.CategoryService;
import com.imooc.reader.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResponseUtil getAllList(){
        List<Category> categoryList = categoryService.getAllList();
        return new ResponseUtil().put("list",categoryList);
    }
}
