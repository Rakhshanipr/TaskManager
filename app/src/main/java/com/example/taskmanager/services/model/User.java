package com.example.taskmanager.services.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.UUID;

@Entity
public class User {

    @ColumnInfo(name = "Id")
    UUID mId;

    @ColumnInfo(name = "UserName")
    String mUserName;

    @ColumnInfo(name = "Password")
    String mPassword;

    @ColumnInfo(name = "Accessblity")
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
