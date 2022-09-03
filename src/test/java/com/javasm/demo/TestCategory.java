package com.javasm.demo;

import com.imooc.reader.dao.CategoryDao;
import com.imooc.reader.dao.EvaluationDao;
import com.imooc.reader.entity.Category;
import com.imooc.reader.service.CategoryService;
import com.imooc.reader.utils.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCategory {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private EvaluationDao evaluationDao;
    @Test
    public void testList(){
        List<Category> categoryList = categoryDao.getAllList();
        categoryList.forEach(System.out::println);
    }
    @Test
    public void testList1(){
        List<Map> evaluationList = evaluationDao.getEvaluationListByBookId(1);
        evaluationList.forEach(System.out::println);
    }
    @Test
    public void testMd5(){
        String md5Digest = Md5Util.md5Digest("hah", 123);
        System.out.println(md5Digest);
    }
}
