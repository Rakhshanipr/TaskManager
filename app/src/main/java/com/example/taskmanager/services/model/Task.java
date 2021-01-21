package com.example.taskmanager.services.model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;

    private String mTitle;

    private String mDescribe;

    private State mState;

    private Date mDate;

    private UUID mUser;

    public Task() {
        mId=UUID.randomUUID();
    }

    public Task(String title, String describe, State state, Date date, UUID user) {
        mTitle = title;
        mDescribe = describe;
        mState = state;
        mDate = date;
        mUser = user;
        mId=UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescribe() {
        return mDescribe;
    }

    public void setDescribe(String describe) {
        mDescribe = describe;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getUser() {
        return mUser;
    }

    public void setUser(UUID user) {
        mUser = user;
    }
}
