package com.example.taskmanager.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentListTaskBinding;
import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.veiwmodel.MainViewModel;
import com.example.taskmanager.veiwmodel.TaskViewModel;
import com.google.android.material.tabs.TabLayoutMediator;


public class ListTaskFragment extends Fragment {

    //region defind static method and variable
    public static final String ARGS_INT_STATE = "com.example.taskmanager.view.fragment.ListTaskFragment.int_state";
    public static final String TAG_SHOW_FRAGMENT_ADD_TASK = "com.example.taskmanager.view.fragment.show_add_task_fragment";
    public static final int REQUEST_CODE_SHOW_ADD_TASK_FRAGMENT = 0;

    public static ListTaskFragment newInstance(int state) {
        ListTaskFragment fragment = new ListTaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_INT_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    //region defind variable
    FragmentListTaskBinding mFragmentListTaskBinding;
    State mState;
    TaskViewModel mTaskViewModel;

    //endregion
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentListTaskBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_task, container, false);
        setInitial();

        setListners();

        return mFragmentListTaskBinding.getRoot();

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==REQUEST_CODE_SHOW_ADD_TASK_FRAGMENT){
                mTaskViewModel.updateAllRecyclerAdapter(getFragmentManager());
            }
        }
    }

    private void setListners() {
        mFragmentListTaskBinding.floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskFragment addTaskFragment = AddTaskFragment.newInstance();
                addTaskFragment.setTargetFragment(ListTaskFragment.this, REQUEST_CODE_SHOW_ADD_TASK_FRAGMENT);
                addTaskFragment.show(getFragmentManager(), TAG_SHOW_FRAGMENT_ADD_TASK);
            }
        });
    }

    private void setInitial() {
        mTaskViewModel=new TaskViewModel();

        int state = getArguments().getInt(ARGS_INT_STATE);
        mState = State.values()[state];


        mFragmentListTaskBinding.recyclerviewListTask.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mFragmentListTaskBinding.recyclerviewListTask.setAdapter(
                mTaskViewModel.createTaskRecyclerViewAdapter(getContext()
                        , mState));
    }

    public void updateAdapter(){
        int state = getArguments().getInt(ARGS_INT_STATE);
        mState = State.values()[state];

        mFragmentListTaskBinding.recyclerviewListTask.setAdapter(
                mTaskViewModel.createTaskRecyclerViewAdapter(getContext()
                        , mState));
    }
}