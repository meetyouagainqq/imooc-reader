package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;

import java.util.Map;

public interface BookService {
     //order代表排序规则
    public IPage<Book> getPage(Integer categoryId,String order,Integer page,Integer rows);
    public Book getBookDetailById(Integer bookId);
    public void updateScore();
    public IPage<Map> getBookByPage(Integer page,Integer rows);
    public Book addBook(Book book);
    public Book updateBook(Book book);
     public void deleteBookById(Integer bookId);
}
