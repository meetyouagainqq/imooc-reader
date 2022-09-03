package com.imooc.reader.service.impl;

import com.imooc.reader.dao.EvaluationDao;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public List<Map> getEvaluationListByBookId(Integer bookId) {
        List<Map> evaluationList = evaluationDao.getEvaluationListByBookId(bookId);
        return evaluationList;
    }

    @Override
    public Evaluation addEvaluation(Integer memberId, Integer bookId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setMemberId(memberId);
        evaluation.setBookId(bookId);
        evaluation.setContent(content);
        evaluation.setCreateTime(new Date());
        evaluation.setScore(score);
        evaluation.setEnjoy(0);
        evaluation.setState("enable");
        evaluationDao.insert(evaluation);
        return evaluation;
    }

    @Override
    public Evaluation updateEnjoy(Integer evaluationId) {
        Evaluation evaluation = evaluationDao.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationDao.updateById(evaluation);
        return evaluation;
    }
}
