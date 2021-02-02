package com.example.taskmanager.veiwmodel;

import android.content.Context;

import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.UserRepository;

public class UserViewModel {

    //region defind variable
    UserRepository mUserRepository;
    //endregion


    public UserViewModel(Context context) {
        mUserRepository = UserRepository.getInstance(context);
    }

    public void addUser( String userName,String password, int accessblity) {
        mUserRepository.add(new User(userName, password, accessblity));
    }
}
