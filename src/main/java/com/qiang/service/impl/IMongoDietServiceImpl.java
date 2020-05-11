package com.qiang.service.impl;
import com.qiang.domain.Mongodiet;
import com.qiang.service.IMongoDietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


/**
 * @author Mr.锵
 * date 2020-05-08
 */
@Service("mongodietService")
public class IMongoDietServiceImpl implements IMongoDietService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Mongodiet> findAll(String name,Integer start,Integer size) {
        org.springframework.data.mongodb.core.query.Query  query=new org.springframework.data.mongodb.core.query.Query();
        if(name !=null){
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        query=new org.springframework.data.mongodb.core.query.Query(Criteria.where("title").regex(pattern));
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"time")));
        List<Mongodiet> all = mongoTemplate.find(query.skip(start).limit(size),Mongodiet.class);
        return all;
    }
    @Override
    public Long counttotal(String name) {
        org.springframework.data.mongodb.core.query.Query  query=new org.springframework.data.mongodb.core.query.Query();
        if(name !=null){
            String regex = String.format("%s%s%s", "^.*", name, ".*$");
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            query=new org.springframework.data.mongodb.core.query.Query(Criteria.where("title").regex(pattern));
        }
        return mongoTemplate.count(query, Mongodiet.class);
    }
}
