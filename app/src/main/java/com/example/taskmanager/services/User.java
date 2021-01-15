package com.example.taskmanager.services;

import java.util.UUID;

public class User {
    UUID Id;
    String userName;
    String password;
    int accessblity;

    public UUID getId() {
        return Id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessblity() {
        return accessblity;
    }

    public void setAccessblity(int accessblity) {
        this.accessblity = accessblity;
    }
}
