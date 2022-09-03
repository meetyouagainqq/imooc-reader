package com.imooc.reader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.reader.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationDao extends BaseMapper<Evaluation> {
    public List<Map> getEvaluationListByBookId(Integer bookId);
}
