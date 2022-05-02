package com.example.itforumspring.Service;

import com.example.itforumspring.bdclass.Quastion;
import com.example.itforumspring.bdclass.Users;
import com.example.itforumspring.repositories.QuastionRepository;
import com.example.itforumspring.repositories.customInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
@Service
public class CustomQuestionService {
    @Autowired
    private customInterface quastioninterface;
    @Autowired
    private QuastionRepository quastionRepository;

    public void saveQuestion(Quastion quastion,Users users){
        quastion.setId(quastioninterface.getMaxId()+1);
        quastion.setUser(new HashSet<>(List.of(users)));
        Date data = new Date();
        quastion.setDatePublish(data);
        quastionRepository.save(quastion);
    }
    public List<Quastion> sortByDeskId()
    {
       return quastioninterface.SortByID();
    }
    public Quastion findByIdQuestion(long id)
    {
        return quastionRepository.findById(id);
    }
    public List<Quastion> findQuestionByTags(String tags)
    {
        return quastioninterface.findByTags(tags);
    }
}
