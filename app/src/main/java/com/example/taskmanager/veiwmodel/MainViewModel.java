package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.adapter.TaskViewPagerAdapter;
import com.example.taskmanager.services.model.Task;

import java.util.List;

public class MainViewModel {

    public static TaskViewPagerAdapter createTaskViewPagerAdapter(Activity activity){
        TaskViewPagerAdapter taskViewPagerAdapter=new TaskViewPagerAdapter((AppCompatActivity)activity);
        return taskViewPagerAdapter;
    }

    public static TaskRecyclerViewAdapter createTaskRecyclerViewAdapter(Context context, List<Task> list){
        return new TaskRecyclerViewAdapter(context,list);
    }
}
