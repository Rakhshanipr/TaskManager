package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.adapter.TaskViewPagerAdapter;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.view.activity.SearchActivity;

import java.util.List;

public class MainViewModel {

    TaskRepository mTaskRepository;
    ICallBacksMainViewModel mCallBacks;
    UserRepository mUserRepository;
    Context mContext;

    public static TaskViewPagerAdapter createTaskViewPagerAdapter(Activity activity){
        TaskViewPagerAdapter taskViewPagerAdapter=new TaskViewPagerAdapter((AppCompatActivity)activity);
        return taskViewPagerAdapter;
    }

    public MainViewModel(Context context) {
        mTaskRepository=TaskRepository.getInstance(context);
        mUserRepository=UserRepository.getInstance(context);
        mContext=context;
    }

    public void startShareActivity(Intent intent,Activity activity){
        if (activity instanceof ICallBacksMainViewModel){
            mCallBacks=(ICallBacksMainViewModel)activity;
            mCallBacks.startSahreActivity(intent);
        }else{
            throw new ClassCastException("implement ICallbacks");
        }
    }

    public void SearchButtonClicked(Activity firstActivity){
        if (firstActivity instanceof ICallBacksMainViewModel){
            mCallBacks=(ICallBacksMainViewModel)firstActivity;
            mCallBacks.showActivity(SearchActivity.newIntent(firstActivity));
        }else{
            throw new ClassCastException("implement ICallbacks");
        }
    }


    public void LoginButtonClicked(Activity activity, String userName, String password) {
        if (mUserRepository.isValid(userName, password)) {

            mUserRepository.setsOnlineUser(mUserRepository.retValidUser(userName, password));


            if (activity instanceof ICallBacksMainViewModel) {
                mCallBacks = (ICallBacksMainViewModel) activity;
            } else {
                throw new ClassCastException("your activity must implemented ICallBacks");
            }
            mCallBacks.Logined(mUserRepository.getsOnlineUser());
        }
    }

    public interface ICallBacksMainViewModel {
        void startSahreActivity(Intent intet);
        void Logined(User user);
        void showActivity(Intent intent);
    }
}
