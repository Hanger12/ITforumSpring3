package com.example.itforumspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class customclassimpl implements customInterface
{
    //тест исправил репозиторий
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long getMaxId() {
        return 0;
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
