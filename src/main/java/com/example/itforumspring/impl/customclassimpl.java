package com.example.itforumspring.impl;

import com.example.itforumspring.bdclass.Quastion;
import com.example.itforumspring.repositories.customInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class customclassimpl implements customInterface
{
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Quastion> SortByID() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "_id"));
        return mongoTemplate.find(query,Quastion.class);
    }

    @Override
    public long getMaxId() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "_id"));
        query.limit(1);
        Quastion maxObject = mongoTemplate.findOne(query, Quastion.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
    @Override
    public List<Quastion> findByTags(String tags) {
        String criteria = "{ Tags: { $all : [";

        String[] tagArray = tags.split(" ");
        for (String tag : tagArray){
            criteria += " /^" + tag + "$/i,";
        }

        criteria += "] } }";
        System.out.println(criteria + " ||||| " + tagArray.length);

        BasicQuery basicQuery = new BasicQuery(criteria);
        return mongoTemplate.find(basicQuery, Quastion.class);
    }
}
