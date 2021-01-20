package com.example.taskmanager.veiwmodel;

import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.UserRepository;

public class UserViewModel {

    //region defind variable
    UserRepository mUserRepository;
    //endregion


    public UserViewModel() {
        mUserRepository = UserRepository.getInstance();
    }

    public void addUser( String userName,String password, int accessblity) {
        mUserRepository.add(new User(userName, password, accessblity));
    }
}
