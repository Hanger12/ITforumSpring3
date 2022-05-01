package com.example.itforumspring;

import com.example.itforumspring.bdclass.Users;
import com.example.itforumspring.repositories.UserRepository;
import com.example.itforumspring.repositories.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class customUserRepositoryClass implements UserRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public long getMaxId() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "_id"));
        query.limit(1);
        Users maxObject = mongoTemplate.findOne(query, Users.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
