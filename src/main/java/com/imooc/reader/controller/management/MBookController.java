package com.imooc.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import com.imooc.reader.utils.ResponseUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/management/book")
public class MBookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtil getBookList(Integer page, Integer rows) {
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        ResponseUtil response = null;
        try {
            IPage<Map> book = bookService.getBookByPage(page, rows);
            response = new ResponseUtil().put("list", book.getRecords()).put("count", book.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }

    @PostMapping("/upload")
    public Map uploadFile(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        String filePath = request.getServletContext().getResource("/").getPath() + "/upload/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(filePath + fileName + suffix));
        Map map = new LinkedHashMap();
        map.put("errno", 0);
        map.put("data", new String[]{"/upload/" + fileName + suffix});
        return map;
    }

    @PostMapping("/create")
    public ResponseUtil addBook(Book book) {
        ResponseUtil response;
        try {
            Document document = Jsoup.parse(book.getDescription());
            Elements img = document.select("img");
            if (img.size() == 0) {
                response = new ResponseUtil("ImageNotFoundError", "封面未包含图片");
                return response;
            }
            String cover = img.first().attr("src");
            book.setCover(cover);
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            bookService.addBook(book);
            response = new ResponseUtil().put("book", book);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }

    @PostMapping("/update")
    public ResponseUtil updateBook(Book book) {
        ResponseUtil response;
        try {
            Document document = Jsoup.parse(book.getDescription());
            Elements img = document.select("img");
            if (img.size() == 0) {
                response = new ResponseUtil("ImageNotFoundError", "封面未包含图片");
                return response;
            }
            String cover = img.first().attr("src");
            Book bookDetail = bookService.getBookDetailById(book.getBookId());
            bookDetail.setCover(cover);
            bookDetail.setBookName(book.getBookName());
            bookDetail.setSubTitle(book.getSubTitle());
            bookDetail.setCategoryId(book.getCategoryId());
            bookDetail.setAuthor(book.getAuthor());
            bookDetail.setDescription(book.getDescription());
            bookService.updateBook(bookDetail);
            response = new ResponseUtil().put("book", bookDetail);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }
    @PostMapping("/delete")
    public ResponseUtil deleteBookById(Integer bookId){
        ResponseUtil response;
        try {
            bookService.deleteBookById(bookId);
            response=new ResponseUtil();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }
}
