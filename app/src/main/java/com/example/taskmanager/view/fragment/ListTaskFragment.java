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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListTaskFragment extends Fragment {

    //region defind static method and variable
    public static final String ARGS_INT_STATE = "com.example.taskmanager.view.fragment.ListTaskFragment.int_state";

    public static ListTaskFragment newInstance(int state) {
        ListTaskFragment fragment = new ListTaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_INT_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    //regiom defind variable
    FragmentListTaskBinding mFragmentListTaskBinding;
    TaskRepository mTaskRepository;
//    UserRepository mUserRepository;
    State mState;
    //endregion
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentListTaskBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_list_task,container,false);
        setInitial();
        return mFragmentListTaskBinding.getRoot();

    }

    private void setInitial() {
        int state=getArguments().getInt(ARGS_INT_STATE);
        mState=State.values()[state];


        mTaskRepository= TaskRepository.getInstance();
//        mUserRepository=UserRepository.getInstance();
mFragmentListTaskBinding.recyclerviewListTask.setLayoutManager(new GridLayoutManager(getContext(),1));
        mFragmentListTaskBinding.recyclerviewListTask.setAdapter(
                MainViewModel.createTaskRecyclerViewAdapter(getContext()
                ,mTaskRepository.getList(mState,UserRepository.getsOnlineUser())));
    }
}