package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.view.fragment.EditTaskFragment;
import com.example.taskmanager.view.fragment.ListTaskFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskViewModel {

    //region defind variable
    TaskRepository mTaskRepository;

    static FragmentManager mFragmentManager;
    //endregion

    public TaskViewModel(Context context) {
        mTaskRepository=TaskRepository.getInstance(context);
    }

    public void addTask(Task task){
        mTaskRepository.add(task);
    }

    public TaskRecyclerViewAdapter createTaskRecyclerViewAdapter(Activity context, State state,Fragment target,FragmentManager fragmentManager){
        return new TaskRecyclerViewAdapter(context,mTaskRepository.getList(state, UserRepository.getsOnlineUser()),target,fragmentManager);
    }


    public TaskRecyclerViewAdapter createTaskRecyclerViewAdapter(Activity activity, Fragment target
            , FragmentManager fragmentManager, Date dateFrom, Date dateTo
            , String title, String describe){


        List<Task> taskListResult=mTaskRepository.getList(UserRepository.getsOnlineUser()
        ,dateFrom,dateTo,title,describe);

        return new TaskRecyclerViewAdapter(activity,taskListResult,target,fragmentManager);
    }

    public void updateAllRecyclerAdapter(FragmentManager fragmentManager){
        mFragmentManager=fragmentManager;
        for (Fragment fg:fragmentManager.getFragments()
             ) {
            if (fg instanceof ListRecyclerViewTaskViewModel.ICallBacksRecyclerViewAdapter){
                ((ListRecyclerViewTaskViewModel.ICallBacksRecyclerViewAdapter)fg).updateRecyclerView();
            }
        }
    }

    public Task getTask(UUID uuid){
        return mTaskRepository.get(uuid);
    }

    public void updateTask(Task task){
        mTaskRepository.update(task);
    }

    public void deleteTask(Task task){
        mTaskRepository.delete(task);
    }

    public List<Task> getListTask( Date dateFrom, Date dateTo
            , String title, String describe){
        return mTaskRepository.getList(UserRepository.getsOnlineUser(),dateFrom,dateTo,title,describe);
    }



}
