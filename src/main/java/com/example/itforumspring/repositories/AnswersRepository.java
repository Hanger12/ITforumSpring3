package com.example.itforumspring.repositories;

import com.example.itforumspring.bdclass.Answers;
import com.example.itforumspring.bdclass.Quastion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AnswersRepository extends MongoRepository<Answers, Long> {
    @Query(value="{'question.$id':?0}")
    List<Answers> findAnswersbyQuestionId(long questionId);
}
