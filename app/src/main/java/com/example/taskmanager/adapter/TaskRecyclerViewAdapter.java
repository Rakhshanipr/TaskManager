package com.example.taskmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.ListItemTaskBinding;
import com.example.taskmanager.services.model.Task;

import java.util.List;
import java.util.zip.Inflater;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.taskHolder> {

    //region defind variable
    List<Task> mTaskList;
    ListItemTaskBinding mListItemTaskBinding;
    Context mContext;
    //endregion


    public TaskRecyclerViewAdapter(Context context, List<Task> taskList) {
        mTaskList = taskList;
        mContext=context.getApplicationContext();
    }

    @NonNull
    @Override
    public taskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);

        mListItemTaskBinding= DataBindingUtil.inflate(
                inflater
                ,R.layout.list_item_task
                ,parent
                ,false
        );
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull taskHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }




    class taskHolder extends RecyclerView.ViewHolder{

        public taskHolder(@NonNull View itemView) {
            super(itemView);
        }

    }


}
