package com.example.taskmanager.veiwmodel;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.view.fragment.ListTaskFragment;

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

    public TaskRecyclerViewAdapter createTaskRecyclerViewAdapter(Context context, State state){
        return new TaskRecyclerViewAdapter(context,mTaskRepository.getList(state, UserRepository.getsOnlineUser()));
    }

    public void updateAllRecyclerAdapter(FragmentManager fragmentManager){
        for (Fragment fg:fragmentManager.getFragments()
             ) {
            if (fg instanceof ListTaskFragment){
                ((ListTaskFragment)fg).updateAdapter();
            }
        }
    }
}
