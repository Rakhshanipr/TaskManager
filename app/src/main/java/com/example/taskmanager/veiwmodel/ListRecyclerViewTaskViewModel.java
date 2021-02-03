package com.example.taskmanager.veiwmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.view.fragment.EditTaskFragment;
import com.example.taskmanager.view.fragment.ListTaskFragment;

import java.util.Calendar;

public class ListRecyclerViewTaskViewModel {

    //region static methode and variable
    public static final String TAG_SHOW_EDIT_TASK_FRAGMENT = "com.example.taskmanager.veiwmodel.show_edit_task_fragment";
    //endregion\

    //region defind variable

    Task mTask;

    Activity mActivity;

    ICallBacksRecyclerViewAdapter mICallBacksRecyclerViewAdapter;

    Fragment mTargetFragment;
    FragmentManager mFragmentManager;

    TaskViewModel mTaskViewModel;
    MainViewModel mMainViewModel;
//endregion


    public ListRecyclerViewTaskViewModel(Task task, Fragment targetFragment, FragmentManager fragmentManager, Activity activity) {
        mTask = task;
        mTargetFragment = targetFragment;
        mFragmentManager = fragmentManager;
        mTaskViewModel = new TaskViewModel(activity.getApplicationContext());
        mActivity=activity;
        mMainViewModel = new MainViewModel(activity.getApplicationContext());
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

    public String getTitle() {
        return mTask.getTitle();
    }

    public String getDescribe() {
        return mTask.getDescribe();
    }

    public String getState() {
        return mTask.getState().toString();
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());
        String date = calendar.get(Calendar.YEAR) + " / " + calendar.get(Calendar.DAY_OF_MONTH) + " / " + calendar.get(Calendar.MONTH)
                + "     " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + ((calendar.get(Calendar.AM_PM) == 0) ? "AM" : "PM");
        ;


        return date;
    }

    public void deleteTask() {
        mTaskViewModel.deleteTask(mTask);
        if (mTargetFragment instanceof ICallBacksRecyclerViewAdapter) {
            mICallBacksRecyclerViewAdapter = (ICallBacksRecyclerViewAdapter) mTargetFragment;
            mICallBacksRecyclerViewAdapter.updateRecyclerView();
        }
    }

    public void editTask() {
        EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(mTask.getId());

        editTaskFragment.setTargetFragment(mTargetFragment
                , ListTaskFragment.REQUEST_CODE_SHOW_EDIT_TASK_FRAGMENT);

        editTaskFragment.show(mFragmentManager, TAG_SHOW_EDIT_TASK_FRAGMENT);

    }

    public void share() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mTask.getDescribe() + "---" + mTask.getState());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, mTask.getTitle());
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        mMainViewModel.startShareActivity(shareIntent,mActivity);
    }

    public interface ICallBacksRecyclerViewAdapter {
        void updateRecyclerView();
    }

}
