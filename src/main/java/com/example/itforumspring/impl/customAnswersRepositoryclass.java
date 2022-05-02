package com.example.itforumspring.impl;

import com.example.itforumspring.bdclass.Answers;
import com.example.itforumspring.bdclass.Users;
import com.example.itforumspring.repositories.AnswersRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class customAnswersRepositoryclass implements AnswersRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public long getMaxId() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "_id"));
        query.limit(1);
        Answers maxObject = mongoTemplate.findOne(query, Answers.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
