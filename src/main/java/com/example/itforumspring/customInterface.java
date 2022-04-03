package com.example.itforumspring;

import com.example.itforumspring.bdclass.Quastion;

import java.util.List;

public interface customInterface
{
    long getMaxId();
    List<Quastion> findByTags(String tags);
}
