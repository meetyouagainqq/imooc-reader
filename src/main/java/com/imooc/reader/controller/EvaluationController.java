package com.imooc.reader.controller;

import com.imooc.reader.service.EvaluationService;
import com.imooc.reader.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/list")
    public ResponseUtil getEvaluationList(Integer bookId) {
        List<Map> evaluationList = evaluationService.getEvaluationListByBookId(bookId);
        return new ResponseUtil().put("list", evaluationList);
    }
}
