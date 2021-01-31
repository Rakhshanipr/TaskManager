package com.example.taskmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.ListItemTaskBinding;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.veiwmodel.ListRecyclerViewTaskViewModel;
import com.example.taskmanager.veiwmodel.TaskViewModel;
import com.example.taskmanager.view.fragment.EditTaskFragment;
import com.example.taskmanager.view.fragment.ListTaskFragment;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskHolder> {


    //region defind variable
    List<Task> mTaskList;
    Activity mActivity;
    Fragment mTargetFragment;
    FragmentManager mFragmentManager;
    //endregion

    public TaskRecyclerViewAdapter(Activity activity, List<Task> taskList, Fragment targetFragment, FragmentManager fragmentManager) {
        mTaskList = taskList;
        mActivity = activity;
        mTargetFragment = targetFragment;
        mFragmentManager = fragmentManager;
    }

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);

        ListItemTaskBinding mListItemTaskBinding = DataBindingUtil.inflate(
                inflater
                , R.layout.list_item_task
                , parent
                , false
        );
        return new TaskHolder(mListItemTaskBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.bindTask(mTaskList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        ListItemTaskBinding mListItemTaskBinding;
        Task mTask;

        public TaskHolder(ListItemTaskBinding listItemTaskBinding) {
            super(listItemTaskBinding.getRoot());
            mListItemTaskBinding = listItemTaskBinding;
            listItemTaskBinding.setListRecyclerViewTaskViewModel(
                    new ListRecyclerViewTaskViewModel(mTask
                            , mTargetFragment
                            , mFragmentManager
                            , mActivity));
        }

        void bindTask(Task task) {
            mTask = task;
            mListItemTaskBinding.getListRecyclerViewTaskViewModel().setTask(task);
        }
    }
}
