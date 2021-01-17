package com.example.taskmanager.services.model;

import java.util.UUID;

public class User {
    UUID mId;
    String mUserName;
    String mPassword;
    int mAccessblity;

    public User(String userName, String password, int accessblity) {
        this.mUserName = userName;
        this.mPassword = password;
        this.mAccessblity = accessblity;
        mId=UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public int getAccessblity() {
        return mAccessblity;
    }

    public void setAccessblity(int accessblity) {
        this.mAccessblity = accessblity;
    }
}
