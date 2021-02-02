package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.example.taskmanager.databinding.FragmentLoginBinding;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.view.fragment.UserFragment;

import java.util.Date;

public class LoginViewModel {
    //region defind variable

    FragmentLoginBinding mFragmentLoginBinding;

    UserRepository mUserRepository;
    TaskRepository mTaskRepository;

    //endregion
    public static MutableLiveData<String> name = new MutableLiveData<>();

    public LoginViewModel(Context context) {
        mUserRepository = UserRepository.getInstance(context);
        mTaskRepository=TaskRepository.getInstance(context);
        //region defind func to test
        User user=new User("ali", "123", 1);
        mUserRepository.add(user);
        UserRepository.setsOnlineUser(user);
        mTaskRepository.add(new Task("university"
                ,"complete exame math"
                , State.Done, new Date()
                ,UserRepository.getsOnlineUser().getId()));

        mTaskRepository.add(new Task("Maktab"
                ,"complete HW13"
                , State.Doing, new Date()
                ,UserRepository.getsOnlineUser().getId()));


        mTaskRepository.add(new Task("faghihi"
                ,"go to home's"
                , State.Doing, new Date()
                ,UserRepository.getsOnlineUser().getId()));


        mTaskRepository.add(new Task("taheri"
                ,"book sire piambar"
                , State.ToDo, new Date()
                ,UserRepository.getsOnlineUser().getId()));

        mTaskRepository.add(new Task("Haji"
                ,"asked question"
                , State.ToDo, new Date()
                ,UserRepository.getsOnlineUser().getId()));

        mTaskRepository.add(new Task("AmirHossen"
                ,"learn math for amir"
                , State.ToDo, new Date()
                ,UserRepository.getsOnlineUser().getId()));

        mTaskRepository.add(new Task("Neda"
                ,"Complete mobile network"
                , State.ToDo, new Date()
                ,UserRepository.getsOnlineUser().getId()));
        //endregion
    }




}