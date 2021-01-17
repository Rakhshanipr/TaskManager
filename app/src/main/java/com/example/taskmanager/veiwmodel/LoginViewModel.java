package com.example.taskmanager.veiwmodel;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.example.taskmanager.databinding.FragmentLoginBinding;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;

import java.util.Date;

public class LoginViewModel {
    //region defind variable

    FragmentLoginBinding mFragmentLoginBinding;

    UserRepository mUserRepository;
    TaskRepository mTaskRepository;
    ICallBacks mCallBacks;
    //endregion
    public static MutableLiveData<String> name = new MutableLiveData<>();

    public LoginViewModel() {
        mUserRepository = UserRepository.getInstance();
        mTaskRepository=TaskRepository.getInstance();
        //region defind func to test
        mUserRepository.add(new User("ali", "123", 1));

        mTaskRepository.add(new Task("university"
                ,"complete exame math"
                , State.Done, new Date()
                ,UserRepository.getsOnlineUser().getId()));

        mTaskRepository.add(new Task("Maktab"
                ,"complete HW13"
                , State.Doing, new Date()
                ,UserRepository.getsOnlineUser().getId()));


        mTaskRepository.add(new Task("taheri"
                ,"book sire piambar"
                , State.ToDo, new Date()
                ,UserRepository.getsOnlineUser().getId()));


        //endregion
    }


    public void LoginClicked(Activity activity, String userName, String password) {
        if (mUserRepository.isValid(userName, password)) {

            mUserRepository.setsOnlineUser(mUserRepository.retValidUser(userName, password));


            if (activity instanceof ICallBacks) {
                mCallBacks = (ICallBacks) activity;
            } else {
                throw new ClassCastException("your activity must implemented ICallBacks");
            }
            mCallBacks.Logined(mUserRepository.getsOnlineUser());
        }
    }


    public interface ICallBacks {
        void Logined(User user);
    }

}