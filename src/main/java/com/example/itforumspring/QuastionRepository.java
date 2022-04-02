package com.example.itforumspring;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuastionRepository extends MongoRepository<Quastion, Long> {
    Quastion findById(long id);


    void deleteById(long id);
}
