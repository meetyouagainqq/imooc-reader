package com.imooc.reader.service.impl;

import com.imooc.reader.dao.CategoryDao;
import com.imooc.reader.entity.Category;
import com.imooc.reader.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAllList() {
        return categoryDao.getAllList();
    }
}
