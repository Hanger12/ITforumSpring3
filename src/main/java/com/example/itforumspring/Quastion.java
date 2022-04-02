package com.example.itforumspring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Quastion")
public class Quastion {
    @Id
    private String id;
    @Field(value = "NameUser")
    private String NameUser;

    @Field(value = "quastion")
    private String Quastion;

    @Field(value = "Tags")
    private String tags;

    @Field(value = "votes")
    private int votes;

    @Field(value = "views")
    private int views;

    @Field(value = "DatePublish")
    private String DatePublish;

    @Field(value = "AvatarUser")
    private String AvatarUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String nameUser) {
        NameUser = nameUser;
    }

    public String getQuastion() {
        return Quastion;
    }

    public void setQuastion(String quastion) {
        this.Quastion = quastion;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDatePublish() {
        return DatePublish;
    }

    public void setDatePublish(String datePublish) {
        DatePublish = datePublish;
    }

    public String getAvatarUser() {
        return AvatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        AvatarUser = avatarUser;
    }
}
