package com.example.taskmanager.veiwmodel;

import com.example.taskmanager.services.model.Task;

public class TaskViewModel {
    Task mTask;

    public String getTitle(){
        return mTask.getTitle();
    }

    public String getDescribe(){
        return mTask.getDescribe();
    }

    public String getState(){
        return mTask.getState().toString();
    }

    public String getDate(){
        return mTask.getDate().toString();
    }

}
