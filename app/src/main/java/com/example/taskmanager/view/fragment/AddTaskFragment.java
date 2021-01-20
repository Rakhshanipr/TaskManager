package com.example.taskmanager.view.fragment;

import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentListTaskBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {

    //region defind variable
    FragmentListTaskBinding mFragmentListTaskBinding;
    //endregion

    //region defind static method and variable
    public static AddTaskFragment newInstance() {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    //endregion\

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFragmentListTaskBinding= DataBindingUtil.inflate(inflater
                ,R.layout.fragment_add_task
                ,container,false);

        initial();
        
        setListenrs();
        

        return mFragmentListTaskBinding.getRoot();

    }

    private void setListenrs() {

    }

    private void initial() {
        
    }
}