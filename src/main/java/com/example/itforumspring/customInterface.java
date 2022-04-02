package com.example.itforumspring;

import java.util.List;

public interface customInterface
{
    long getMaxId();
    List<Quastion> findByTags(String tags);
}
