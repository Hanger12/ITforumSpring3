package com.example.itforumspring.bdclass;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document(collection="Answers")
public class Answers {
    @Id
    private long id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String descriptionAnswers;
    private String codeAnswers;
    private Date DatePublishAnswer;
    private Boolean correct;
    @DBRef
    private Set<Users> user;
    @DBRef
    private Set<Quastion> question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getDescriptionAnswers() {
        return descriptionAnswers;
    }

    public void setDescriptionAnswers(String descriptionAnswers) {
        this.descriptionAnswers = descriptionAnswers;
    }

    public String getCodeAnswers() {
        return codeAnswers;
    }

    public void setCodeAnswers(String codeAnswers) {
        this.codeAnswers = codeAnswers;
    }

    public Date getDatePublishAnswer() {
        return DatePublishAnswer;
    }

    public void setDatePublishAnswer(Date datePublishAnswer) {
        DatePublishAnswer = datePublishAnswer;
    }

    public Set<Users> getUser() {
        return user;
    }

    public void setUser(Set<Users> user) {
        this.user = user;
    }

    public Set<Quastion> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Quastion> question) {
        this.question = question;
    }
}
