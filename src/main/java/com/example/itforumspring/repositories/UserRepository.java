package com.example.itforumspring.repositories;
import com.example.itforumspring.bdclass.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users,String> {
    Users findByEmail(String email);
    Users findById(long id);
}
