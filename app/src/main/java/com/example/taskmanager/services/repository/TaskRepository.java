package com.example.taskmanager.services.repository;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.InverseMethod;
import androidx.room.Room;

import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskRepository {


    //region defind static method and variable
    private static List<Task> sTaskList;

    public static TaskRepository sTaskRepository;

    public static TaskRepository getInstance(Context context) {
        if (sTaskRepository == null)
            sTaskRepository = new TaskRepository(context);
        return sTaskRepository;
    }

    //endregion

    TaskManagerDataBase mTaskManagerDataBase;

    private TaskRepository(Context context) {
        sTaskList = new ArrayList<>();
        mTaskManagerDataBase= Room.databaseBuilder(context
                ,TaskManagerDataBase.class
                ,"TaskManagerDataBase")
                .allowMainThreadQueries()
                .build();
    }

    public void add(Task task) {
        mTaskManagerDataBase.mTaskDAO().add(task);
    }

    public void update(Task e) {
        mTaskManagerDataBase.mTaskDAO().update(e);
    }

    public void delete(Task task) {
        mTaskManagerDataBase.mTaskDAO().delete(task);
    }

    public Task get(UUID uuid) {
        return mTaskManagerDataBase.mTaskDAO().get(uuid);
    }

    public List<Task> getList() {
        return mTaskManagerDataBase.mTaskDAO().getList();
    }

    public List<Task> getList(State state, User user) {
        return mTaskManagerDataBase.mTaskDAO().getList(state,user.getId());
    }

    public List<Task> getList(User user, java.util.Date dateFrom, Date dateTo
            , String title, String describe) {

        List<Task> taskListResult=new ArrayList<>();

        for (Task task:
                getList()) {
            if (task.getDate().compareTo(dateFrom)>=0 && task.getDate().compareTo(dateTo)<=0
                    && task.getTitle().contains(title) && task.getDescribe().contains(describe)
            ){
                taskListResult.add(task);
            }
        }
        return taskListResult;
    }
}
