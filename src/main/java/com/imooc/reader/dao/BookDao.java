package com.imooc.reader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;

import java.util.Map;


public interface BookDao extends BaseMapper<Book> {
   public void updateScore();
   public IPage<Map> getBookByPage(IPage page);
}
