package com.imooc.reader.task;

import com.imooc.reader.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ComputerTask {
    private Logger logger= LoggerFactory.getLogger(ComputerTask.class);
    @Autowired
    private BookService bookService;
    @Scheduled(cron = "0 * * * * ?")
    public void updateScore(){
        bookService.updateScore();
        logger.info("已定时更新图书评分");
    }
}
