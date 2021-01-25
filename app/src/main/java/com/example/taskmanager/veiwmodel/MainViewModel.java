package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.adapter.TaskViewPagerAdapter;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;

import java.util.List;

public class MainViewModel {

    TaskRepository mTaskRepository;

    public static TaskViewPagerAdapter createTaskViewPagerAdapter(Activity activity){
        TaskViewPagerAdapter taskViewPagerAdapter=new TaskViewPagerAdapter((AppCompatActivity)activity);
        return taskViewPagerAdapter;
    }

    public MainViewModel() {
        mTaskRepository=TaskRepository.getInstance();
    }

}
