package com.example.itforumspring.repositories;

import com.example.itforumspring.bdclass.Quastion;

import java.util.List;

public interface customInterface
{
    public List<Quastion> SortByID();
    long getMaxId();
    List<Quastion> findByTags(String tags);
}
