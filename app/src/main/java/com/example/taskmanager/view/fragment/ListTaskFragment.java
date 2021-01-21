package com.example.taskmanager.view.fragment;

import android.os.Bundle;

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



public class ListTaskFragment extends Fragment {

    //region defind static method and variable
    public static final String ARGS_INT_STATE = "com.example.taskmanager.view.fragment.ListTaskFragment.int_state";
    public static final String TAG_SHOW_FRAGMENT_ADD_TASK = "com.example.taskmanager.view.fragment.show_add_task_fragment";

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
    TaskRepository mTaskRepository;
    State mState;

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

    private void setListners() {
        mFragmentListTaskBinding.floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskFragment addTaskFragment= AddTaskFragment.newInstance();
                addTaskFragment.show(getFragmentManager(), TAG_SHOW_FRAGMENT_ADD_TASK);
            }
        });
    }

    private void setInitial() {
        int state = getArguments().getInt(ARGS_INT_STATE);
        mState = State.values()[state];


        mTaskRepository = TaskRepository.getInstance();
        mFragmentListTaskBinding.recyclerviewListTask.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mFragmentListTaskBinding.recyclerviewListTask.setAdapter(
                MainViewModel.createTaskRecyclerViewAdapter(getContext()
                        , mTaskRepository.getList(mState, UserRepository.getsOnlineUser())));
    }
}