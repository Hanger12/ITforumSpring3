package com.example.itforumspring.repositories;

import com.example.itforumspring.bdclass.Answers;

public interface AnswersRepositoryCustom {
    long getMaxId();
    void update(long id);
}
