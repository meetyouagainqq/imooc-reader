package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import com.imooc.reader.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtil getAllList(Integer categoryId, String order, Integer page) {
        IPage<Book> bookIPage = bookService.getPage(categoryId, order, page, 10);
        return new ResponseUtil().put("page", bookIPage);
    }
    @GetMapping("/id/{id}")
    public ResponseUtil getBookDetailById(@PathVariable("id") Integer bookId){
        Book book = bookService.getBookDetailById(bookId);
        return new ResponseUtil().put("book",book);
    }
}
