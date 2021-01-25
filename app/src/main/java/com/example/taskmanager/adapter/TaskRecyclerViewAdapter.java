package com.example.taskmanager.adapter;

import android.content.Context;
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

    public static final String TAG_SHOW_EDIT_TASK_FRAGMENT = "com.example.taskmanager.veiwmodel.show_edit_task_fragment";

    //region defind variable
    List<Task> mTaskList;
    Context mContext;
    Fragment mTargetFragment;
    FragmentManager mFragmentManager;
    //endregion

    public TaskRecyclerViewAdapter(Context context, List<Task> taskList,Fragment targetFragment,FragmentManager fragmentManager) {
        mTaskList = taskList;
        mContext=context.getApplicationContext();
        mTargetFragment=targetFragment;
        mFragmentManager=fragmentManager;
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
        TaskViewModel mTaskViewModel;

        public TaskHolder(ListItemTaskBinding listItemTaskBinding) {
            super(listItemTaskBinding.getRoot());
            mListItemTaskBinding=listItemTaskBinding;
            mTaskViewModel=new TaskViewModel();
            listItemTaskBinding.setListRecyclerViewTaskViewModel(new ListRecyclerViewTaskViewModel(mTask));
        }

        void bindTask(Task task){
            mTask=task;
            mListItemTaskBinding.getListRecyclerViewTaskViewModel().setTask(task);
            mListItemTaskBinding.constraintlayoutMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditTaskFragment editTaskFragment=EditTaskFragment.newInstance(task.getId());

                    editTaskFragment.setTargetFragment(mTargetFragment
                            , ListTaskFragment.REQUEST_CODE_SHOW_EDIT_TASK_FRAGMENT);

                    editTaskFragment.show(mFragmentManager, TAG_SHOW_EDIT_TASK_FRAGMENT);

                }
            });

        }
    }


}
