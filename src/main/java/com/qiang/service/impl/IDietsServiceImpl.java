package com.qiang.service.impl;

import com.qiang.domain.Diets;
import com.qiang.service.IDietsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-05-09
 */
@Service("dietsService")
public class IDietsServiceImpl implements IDietsService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Diets> findDietsBytitle(String title) {
        org.springframework.data.mongodb.core.query.Query  query=new org.springframework.data.mongodb.core.query.Query(Criteria.where("title").is(title));
        List<Diets> all = mongoTemplate.find(query,Diets.class);
        return all;
    }

}
