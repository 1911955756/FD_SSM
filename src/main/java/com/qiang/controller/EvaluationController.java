package com.qiang.controller;

import com.qiang.domain.Evaluation;
import com.qiang.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-01
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;
    @RequestMapping("/saveEvaluation")
    public @ResponseBody void saveEvaluation(Evaluation evaluation){
        evaluationService.saveEvaluation(evaluation);
    }
    @RequestMapping("/findAll")
    public @ResponseBody List findAll(String cs_id,Integer type){
        List<Evaluation> list = null;
        if(type==0){
            list=evaluationService.findAll();
        }else{
            list=evaluationService.findMine(cs_id);
        }
        return list;
    }
}
