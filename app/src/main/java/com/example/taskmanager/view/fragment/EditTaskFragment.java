package com.example.taskmanager.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentEditTaskBinding;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.veiwmodel.ListRecyclerViewTaskViewModel;
import com.example.taskmanager.veiwmodel.TaskViewModel;

import java.util.Calendar;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTaskFragment extends DialogFragment {

    //region defind static method and variable
    public static final int REQUEST_CODE_DATE_PICKER_FRAGMENT = 0;
    public static final int REQUEST_CODE_TIME_PICKER_FRAGMENT = 1;

    public static final String TAG_SET_DATE_FRAGMENT = "com.example.taskmanager.view.fragment.show_DatePickerFragment";
    public static final String TAG_SET_TIME_FRAGMENT = "com.example.taskmanager.view.fragment.show_DateTimeFragment";
    public static final String ARGS_SET_TASK_ID = "com.example.taskmanager.view.fragment.EditTaskFragment.args_put_id";


    public static EditTaskFragment newInstance(UUID id) {
        EditTaskFragment fragment = new EditTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_SET_TASK_ID,id.toString());
        fragment.setArguments(args);
        return fragment;
    }
    //endregion

    //region defind variable
    FragmentEditTaskBinding mFragmentEditTaskBinding;
    TaskViewModel mTaskViewModel;
    Calendar mCalender;

    UUID mTaskId;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFragmentEditTaskBinding = DataBindingUtil.inflate(inflater
                ,R.layout.fragment_edit_task
                ,container,false);

        initial();

        setListenrs();

        return mFragmentEditTaskBinding.getRoot();
    }

    private void setListenrs() {
        mFragmentEditTaskBinding.buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment=DatePickerFragment.newInstance();
                datePickerFragment.setTargetFragment(EditTaskFragment.this, REQUEST_CODE_DATE_PICKER_FRAGMENT);
                datePickerFragment.show(getFragmentManager(), TAG_SET_DATE_FRAGMENT);
            }
        });


        mFragmentEditTaskBinding.buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment=TimePickerFragment.newInstance();
                timePickerFragment.setTargetFragment(EditTaskFragment.this, REQUEST_CODE_TIME_PICKER_FRAGMENT);
                timePickerFragment.show(getFragmentManager(), TAG_SET_TIME_FRAGMENT);
            }
        });

        mFragmentEditTaskBinding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region edit task
                State state=State.ToDo;
                if (mFragmentEditTaskBinding.radioButtonDoing.isChecked())
                    state=State.Doing;
                else if (mFragmentEditTaskBinding.radioButtonDone.isChecked())
                    state=State.Done;

                Task task=mTaskViewModel.getTask(mTaskId);
                task.setDate(mCalender.getTime());

                task.setDescribe(mFragmentEditTaskBinding.editTextDescribe.getText().toString());
                task.setTitle(mFragmentEditTaskBinding.editTextTitle.getText().toString());
                task.setUser(UserRepository.getsOnlineUser().getId());
                task.setState(state);
                mTaskViewModel.updateTask(task);

                if (getTargetFragment() instanceof
                        ListRecyclerViewTaskViewModel.ICallBacksRecyclerViewAdapter)
                {
                    ((ListRecyclerViewTaskViewModel.ICallBacksRecyclerViewAdapter)getTargetFragment()).updateRecyclerView();
                }

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
        mTaskViewModel=new TaskViewModel(getContext());

        UUID uuid=UUID.fromString(getArguments().getString(ARGS_SET_TASK_ID,""));
        Task task=mTaskViewModel.getTask(uuid);
        mTaskId=task.getId();

        mFragmentEditTaskBinding.editTextDescribe.setText(task.getDescribe());
        mFragmentEditTaskBinding.editTextTitle.setText(task.getTitle());

        if (task.getState()==State.Doing){
            mFragmentEditTaskBinding.radioButtonDoing.setChecked(true);
        }

        if (task.getState()==State.Done){
            mFragmentEditTaskBinding.radioButtonDone.setChecked(true);
        }else{
            mFragmentEditTaskBinding.radioButtonToDo.setChecked(true);
        }
    }
}