package com.example.taskmanager.veiwmodel;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.view.fragment.EditTaskFragment;
import com.example.taskmanager.view.fragment.ListTaskFragment;

import java.util.UUID;

public class TaskViewModel {

    //region defind variable
    TaskRepository mTaskRepository;

    static FragmentManager mFragmentManager;
    //endregion

    public TaskViewModel() {
        mTaskRepository=TaskRepository.getInstance();
    }

    public void addTask(Task task){
        mTaskRepository.add(task);
    }

    public TaskRecyclerViewAdapter createTaskRecyclerViewAdapter(Context context, State state,Fragment target,FragmentManager fragmentManager){
        return new TaskRecyclerViewAdapter(context,mTaskRepository.getList(state, UserRepository.getsOnlineUser()),target,fragmentManager);
    }

    public void updateAllRecyclerAdapter(FragmentManager fragmentManager){
        mFragmentManager=fragmentManager;
        for (Fragment fg:fragmentManager.getFragments()
             ) {
            if (fg instanceof ListTaskFragment){
                ((ListTaskFragment)fg).updateAdapter();
            }
        }
    }

    public Task getTask(UUID uuid){
        return mTaskRepository.get(uuid);
    }

    public void updateTask(Task task){
        mTaskRepository.update(task);
    }

    public void showEditTaskFragment(UUID uuid){

    }
}
