package com.example.itforumspring.repositories;
import com.example.itforumspring.bdclass.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepository extends MongoRepository<Users,String> {
    Users findByEmail(String email);
}
