package com.example.taskmanager.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentAddTaskBinding;
import com.example.taskmanager.databinding.FragmentListTaskBinding;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.veiwmodel.TaskViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddTaskFragment extends DialogFragment {

    //region defind static method and variable
    public static final int REQUEST_CODE_DATE_PICKER_FRAGMENT = 0;
    public static final int REQUEST_CODE_TIME_PICKER_FRAGMENT = 1;

    public static final String TAG_SET_DATE_FRAGMENT = "com.example.taskmanager.view.fragment.show_DatePickerFragment";
    public static final String TAG_SET_TIME_FRAGMENT = "com.example.taskmanager.view.fragment.show_DateTimeFragment";


    public static AddTaskFragment newInstance() {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    //endregion

    //region defind variable
    FragmentAddTaskBinding mFragmentAddTaskBinding;
    TaskViewModel mTaskViewModel;
    Calendar mCalender;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFragmentAddTaskBinding= DataBindingUtil.inflate(inflater
                ,R.layout.fragment_add_task
                ,container,false);

        initial();
        
        setListenrs();

        return mFragmentAddTaskBinding.getRoot();

    }

    private void setListenrs() {

        mFragmentAddTaskBinding.buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment=DatePickerFragment.newInstance();
                datePickerFragment.setTargetFragment(AddTaskFragment.this, REQUEST_CODE_DATE_PICKER_FRAGMENT);
                datePickerFragment.show(getFragmentManager(), TAG_SET_DATE_FRAGMENT);
            }
        });


        mFragmentAddTaskBinding.buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment=TimePickerFragment.newInstance();
                timePickerFragment.setTargetFragment(AddTaskFragment.this, REQUEST_CODE_TIME_PICKER_FRAGMENT);
                timePickerFragment.show(getFragmentManager(), TAG_SET_TIME_FRAGMENT);
            }
        });

        mFragmentAddTaskBinding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region add task
                State state=State.ToDo;
                if (mFragmentAddTaskBinding.radioButtonDoing.isChecked())
                    state=State.Doing;
                else if (mFragmentAddTaskBinding.radioButtonDone.isChecked())
                    state=State.Done;

                Task task=new Task();
                task.setDate(mCalender.getTime());

                task.setDescribe(mFragmentAddTaskBinding.editTextDescribe.getText().toString());
                task.setTitle(mFragmentAddTaskBinding.editTextTitle.getText().toString());
                task.setUser(UserRepository.getsOnlineUser().getId());
                task.setState(state);
                mTaskViewModel.addTask(task);

                ListTaskFragment listTaskFragment=(ListTaskFragment) getTargetFragment();
                listTaskFragment.onActivityResult(ListTaskFragment.REQUEST_CODE_SHOW_ADD_TASK_FRAGMENT,Activity.RESULT_OK,null);
                //endregion
                getDialog().dismiss();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==REQUEST_CODE_DATE_PICKER_FRAGMENT){
                mCalender.set(Calendar.YEAR
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_YEAR,0));

                mCalender.set(Calendar.MONTH
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_MONTH,0));

                mCalender.set(Calendar.DAY_OF_MONTH
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_DAY,0));

            }

            else if (requestCode==REQUEST_CODE_TIME_PICKER_FRAGMENT){

                mCalender.set(Calendar.HOUR
                        ,data.getIntExtra(TimePickerFragment.BUNDLE_TIME_PICKER_FRAGMENT_HOUR,0));

                mCalender.set(Calendar.MINUTE
                        ,data.getIntExtra(TimePickerFragment.BUNDLE_TIME_PICKER_FRAGMENT_MINUTES,0));


            }
        }
    }

    private void initial() {
        mCalender=Calendar.getInstance();
        mTaskViewModel=new TaskViewModel();
    }
}