package com.example.itforumspring.repositories;

import com.example.itforumspring.bdclass.Quastion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuastionRepository extends MongoRepository<Quastion, Long> {
    Quastion findById(long id);
    @Query(value="{'user.$id':?0}")
    List<Quastion> findQuestionbyUser(long idUser);
}
