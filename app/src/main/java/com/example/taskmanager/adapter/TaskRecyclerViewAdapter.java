package com.example.taskmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.ListItemTaskBinding;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.veiwmodel.ListRecyclerViewTaskViewModel;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskHolder> {

    //region defind variable
    List<Task> mTaskList;
    Context mContext;
    //endregion

    public TaskRecyclerViewAdapter(Context context, List<Task> taskList) {
        mTaskList = taskList;
        mContext=context.getApplicationContext();
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);

        ListItemTaskBinding mListItemTaskBinding= DataBindingUtil.inflate(
                inflater
                ,R.layout.list_item_task
                ,parent
                ,false
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

    class TaskHolder extends RecyclerView.ViewHolder{

        ListItemTaskBinding mListItemTaskBinding;
        Task mTask;

        public TaskHolder(ListItemTaskBinding listItemTaskBinding) {
            super(listItemTaskBinding.getRoot());
            mListItemTaskBinding=listItemTaskBinding;

            listItemTaskBinding.setListRecyclerViewTaskViewModel(new ListRecyclerViewTaskViewModel(mTask));
        }

        void bindTask(Task task){
            mTask=task;
            mListItemTaskBinding.getListRecyclerViewTaskViewModel().setTask(task);
//            mSoundBinding.executePendingBindings();

        }
    }


}
