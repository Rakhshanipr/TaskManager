package com.example.taskmanager.services.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.taskmanager.services.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {


    //region defind stativ method and variable
    private static UserRepository sUserRepository;
    private static List<User> sUserList;

    public static User sOnlineUser;

    public static UserRepository getInstance(Context context) {
        if (sUserRepository == null)
            sUserRepository = new UserRepository(context);
        return sUserRepository;
    }

    //endregion

    TaskManagerDataBase mTaskManagerDataBase;

    private UserRepository(Context context) {
        sUserList = new ArrayList<>();
        mTaskManagerDataBase= Room.databaseBuilder(context
                ,TaskManagerDataBase.class
                ,"TaskManagerDataBase")
                .allowMainThreadQueries()
                .build();
    }

    public void add(User user){
        mTaskManagerDataBase.mUserDAO().add(user);
    }

    public static User getsOnlineUser() {
        return sOnlineUser;
    }

    public static void setsOnlineUser(User onlineUser) {
        sOnlineUser = onlineUser;
    }

    public void update(User user) {
        mTaskManagerDataBase.mUserDAO().update(user);
    }

    public void delete(User user) {
        mTaskManagerDataBase.mUserDAO().delete(user);
    }


    public List<User> getList() {
        return mTaskManagerDataBase.mUserDAO().getList();
    }

    public boolean isValid(String username,String password){
        return mTaskManagerDataBase.mUserDAO().isValid(username,password);
    }

    public User retValidUser(String username,String password){
        return mTaskManagerDataBase.mUserDAO().retValidUser(username,password);
    }

}
