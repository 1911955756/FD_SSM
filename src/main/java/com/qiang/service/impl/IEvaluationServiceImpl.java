package com.qiang.service.impl;

import com.qiang.dao.IEvaluationDao;
import com.qiang.domain.Evaluation;
import com.qiang.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-01
 */
@Service("evaluationService")
public class IEvaluationServiceImpl implements IEvaluationService {
    @Autowired
    private IEvaluationDao evaluationDao;
    @Override
    public void saveEvaluation(Evaluation evaluation) {
        evaluationDao.saveEvaluation(evaluation);
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationDao.findAll();
    }

    @Override
    public List<Evaluation> findMine(String cs_id) {
        return evaluationDao.findMine(cs_id);
    }
}
