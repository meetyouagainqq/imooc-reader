package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.dao.BookDao;
import com.imooc.reader.dao.EvaluationDao;
import com.imooc.reader.dao.MemberReadStateDao;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private EvaluationDao evaluationDao;
    @Autowired
    private MemberReadStateDao memberReadStateDao;

    @Override
    public IPage<Book> getPage(Integer categoryId, String order, Integer page, Integer rows) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        IPage<Book> bookIPage = new Page<>(page, rows);
        if (categoryId != null && categoryId != -1) {
            wrapper.eq("category_id", categoryId);
        }
        if (order != null) {
            if (order.equals("quantity")) {
                wrapper.orderByDesc("evaluation_quantity");
            } else if (order.equals("score")) {
                wrapper.orderByDesc("evaluation_score");
            }
        }else {
            wrapper.orderByDesc("evaluation_quantity");
        }
        bookIPage = bookDao.selectPage(bookIPage, wrapper);
        return bookIPage;
    }

    @Override
    public Book getBookDetailById(Integer bookId) {
        Book book = bookDao.selectById(bookId);
        return book;
    }

    @Override
    public void updateScore() {
        bookDao.updateScore();
    }

    @Override
    public IPage<Map> getBookByPage(Integer page, Integer rows) {
        IPage bookIPage=new Page(page,rows);
        IPage<Map> bookByPage = bookDao.getBookByPage(bookIPage);
        return bookByPage;
    }

    @Override
    public Book addBook(Book book) {
        bookDao.insert(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        bookDao.updateById(book);
        return book;
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookDao.deleteById(bookId);
        QueryWrapper  wrapper=new QueryWrapper();
        wrapper.eq("book_id",bookId);
        evaluationDao.delete(wrapper);
        QueryWrapper  wrapper1=new QueryWrapper();
        wrapper1.eq("book_id",bookId);
        memberReadStateDao.delete(wrapper1);
    }
}
