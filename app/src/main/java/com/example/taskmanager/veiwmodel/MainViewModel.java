package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.adapter.TaskViewPagerAdapter;

public class MainViewModel {

    public static TaskViewPagerAdapter createTaskViewPagerAdapter(Activity activity){
        TaskViewPagerAdapter taskViewPagerAdapter=new TaskViewPagerAdapter((AppCompatActivity)activity);
        return taskViewPagerAdapter;
    }
}
