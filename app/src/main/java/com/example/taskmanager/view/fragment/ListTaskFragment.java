package com.example.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.services.model.State;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListTaskFragment extends Fragment {


    public static final String ARGS_INT_STATE = "om.example.taskmanager.view.fragment.ListTaskFragment.int_state";

    public static ListTaskFragment newInstance(int state) {
        ListTaskFragment fragment = new ListTaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_INT_STATE,state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_task, container, false);
    }
}