package com.imooc.reader.service;

import com.imooc.reader.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    public List<Map> getEvaluationListByBookId(Integer bookId);
    public Evaluation addEvaluation(Integer memberId,Integer bookId,Integer score,String content);
    public Evaluation updateEnjoy(Integer evaluationId);
}
