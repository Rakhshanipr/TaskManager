package com.example.taskmanager.veiwmodel;

import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;

public class ListRecyclerViewTaskViewModel {

    Task mTask;

    public ListRecyclerViewTaskViewModel(Task task) {
        mTask = task;
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

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

    public void deleteTask(){

    }

    public void editTask(){

    }

}
