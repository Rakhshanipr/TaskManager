package com.example.taskmanager.veiwmodel;

import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;

public class TaskViewModel {

    //region defind variable
    TaskRepository mTaskRepository;

    //endregion


    public TaskViewModel() {
        mTaskRepository=TaskRepository.getInstance();
    }

    public void addTask(Task task){
        mTaskRepository.add(task);
    }

}
