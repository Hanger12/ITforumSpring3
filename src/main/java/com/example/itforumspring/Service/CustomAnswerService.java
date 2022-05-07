package com.example.itforumspring.Service;

import com.example.itforumspring.bdclass.Answers;
import com.example.itforumspring.bdclass.Quastion;
import com.example.itforumspring.bdclass.Users;
import com.example.itforumspring.repositories.AnswersRepository;
import com.example.itforumspring.repositories.AnswersRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class CustomAnswerService {
    @Autowired
    private AnswersRepositoryCustom answersRepositoryCustom;
    @Autowired
    private AnswersRepository answersRepository;
    public void SaveAnswer(Answers answers, Quastion quastion, Users users){
        answers.setId(answersRepositoryCustom.getMaxId()+1);
        answers.setUser(new HashSet<>(List.of(users)));
        Date data = new Date();
        answers.setDatePublishAnswer(data);
        answers.setQuestion(new HashSet<>(List.of(quastion)));
        answers.setCorrect(false);
        answersRepository.save(answers);
    }
    public List<Answers> findAnswersbyQuestion(long questionId) {
        return answersRepository.findAnswersbyQuestionId(questionId);
    }
    public List<Answers> findAllAnswers()
    {
        return answersRepository.findAll();
    }
    public void deleteAnswers(Answers answers)
    {
        answersRepository.delete(answers);
    }
    public List<Answers> findAnswersbyUser(long idUser)
    {
       return answersRepository.findAnswersbyUsersId(idUser);
    }
    public Answers findAnswersbyId(long idAnswer)
    {
        return answersRepository.findById(idAnswer);
    }
    public void UpdateAnswers(long id)
    {
       answersRepositoryCustom.update(id);
    }
}
