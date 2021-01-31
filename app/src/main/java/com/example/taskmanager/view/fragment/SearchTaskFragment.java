package com.example.taskmanager.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.TaskRecyclerViewAdapter;
import com.example.taskmanager.databinding.FragmentSearchTaskBinding;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.veiwmodel.ListRecyclerViewTaskViewModel;
import com.example.taskmanager.veiwmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SearchTaskFragment extends Fragment implements ListRecyclerViewTaskViewModel.ICallBacksRecyclerViewAdapter {

    //region defind static method and variable
    public static final int REQUEST_CODE_DATE_PIKER_FRAGMENT_SEARCH_FROM = 0;

    public static final int REQUEST_CODE_TIME_PIKER_FRAGMENT_SEARCH_TO = 3;
    public static final int REQUEST_CODE_DATE_PIKER_FRAGMENT_SEARCH_TO = 2;

    public static final int REQUEST_CODE_TIME_PIKER_FRAGMENT_SEARCH_FROM = 1;

    public static final String TAG_SHOW_TIME_PICKER_FRAGMENT_SERACH_FROM = "com.example.taskmanager.view.fragment.searchTaskFragment_showTimePickerSearch_from";
    public static final String TAG_SHOW_DATE_PICKER_FRAGMENT_SERACH_FROM = "com.example.taskmanager.view.fragment.searchTaskFragment_showDatePickerSearch_from";

    public static final String TAG_SHOW_TIME_PICKER_FRAGMENT_SERACH_TO = "com.example.taskmanager.view.fragment.searchTaskFragment_showTimePickerSearch_to";
    public static final String TAG_SHOW_DATE_PICKER_FRAGMENT_SERACH_TO = "com.example.taskmanager.view.fragment.searchTaskFragment_showDatePickerSearch_to";


    //endregion

    //region defind variable
    FragmentSearchTaskBinding mFragmentSearchTaskBinding;

    Calendar mCalendarFrom;
    Calendar mCalendarTo;

    TaskViewModel mTaskViewModel;

    TaskRecyclerViewAdapter mTaskRecyclerViewAdapter;

    //endregion

    public static SearchTaskFragment newInstance() {
        SearchTaskFragment fragment = new SearchTaskFragment();
        Bundle args = new Bundle();
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

        mFragmentSearchTaskBinding= DataBindingUtil.inflate(inflater
                ,R.layout.fragment_search_task
                ,container
                ,false);

        setInitial();

        setListners();
        return mFragmentSearchTaskBinding.getRoot();
    }

    private void setInitial() {
        mFragmentSearchTaskBinding.recylerViewListTaskSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        mTaskRecyclerViewAdapter=new TaskRecyclerViewAdapter(getActivity()
                ,new ArrayList<Task>()
                ,SearchTaskFragment.this
                ,getFragmentManager());
        mFragmentSearchTaskBinding.recylerViewListTaskSearch.setAdapter(mTaskRecyclerViewAdapter);
    }

    private void setListners() {

        mFragmentSearchTaskBinding.buttonFromSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment=DatePickerFragment.newInstance();
                datePickerFragment.setTargetFragment(SearchTaskFragment.this
                        , REQUEST_CODE_DATE_PIKER_FRAGMENT_SEARCH_FROM);

                datePickerFragment.show(getFragmentManager()
                        , TAG_SHOW_DATE_PICKER_FRAGMENT_SERACH_FROM);
            }
        });

        mFragmentSearchTaskBinding.buttonFromSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment=TimePickerFragment.newInstance();
                timePickerFragment.setTargetFragment(SearchTaskFragment.this
                        , REQUEST_CODE_TIME_PIKER_FRAGMENT_SEARCH_FROM);

                timePickerFragment.show(getFragmentManager(), TAG_SHOW_TIME_PICKER_FRAGMENT_SERACH_FROM);
            }
        });

        mFragmentSearchTaskBinding.buttonToSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment=DatePickerFragment.newInstance();
                datePickerFragment.setTargetFragment(SearchTaskFragment.this
                        , REQUEST_CODE_DATE_PIKER_FRAGMENT_SEARCH_TO);

                datePickerFragment.show(getFragmentManager()
                        , TAG_SHOW_DATE_PICKER_FRAGMENT_SERACH_TO);
            }
        });

        mFragmentSearchTaskBinding.buttonToSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment=TimePickerFragment.newInstance();
                timePickerFragment.setTargetFragment(SearchTaskFragment.this
                        , REQUEST_CODE_TIME_PIKER_FRAGMENT_SEARCH_TO);

                timePickerFragment.show(getFragmentManager(), TAG_SHOW_TIME_PICKER_FRAGMENT_SERACH_TO);

            }
        });

        mFragmentSearchTaskBinding.floatingActionButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecyclerView();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){

            if (requestCode==REQUEST_CODE_DATE_PIKER_FRAGMENT_SEARCH_FROM){
                mCalendarFrom.set(Calendar.YEAR
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_YEAR,0));

                mCalendarFrom.set(Calendar.MONTH
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_MONTH,0));

                mCalendarFrom.set(Calendar.DAY_OF_MONTH
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_DAY,0));

            }

            else if (requestCode==REQUEST_CODE_TIME_PIKER_FRAGMENT_SEARCH_FROM){
                mCalendarFrom.set(Calendar.HOUR
                        ,data.getIntExtra(TimePickerFragment.BUNDLE_TIME_PICKER_FRAGMENT_HOUR,0));

                mCalendarFrom.set(Calendar.MINUTE
                        ,data.getIntExtra(TimePickerFragment.BUNDLE_TIME_PICKER_FRAGMENT_MINUTES,0));

            }

            else if (requestCode==REQUEST_CODE_DATE_PIKER_FRAGMENT_SEARCH_TO){
                mCalendarTo.set(Calendar.YEAR
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_YEAR,0));

                mCalendarTo.set(Calendar.MONTH
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_MONTH,0));

                mCalendarTo.set(Calendar.DAY_OF_MONTH
                        ,data.getIntExtra(DatePickerFragment.BUNDLE_DATE_PICKER_FRAGMENT_DAY,0));


            }

            else if (requestCode==REQUEST_CODE_TIME_PIKER_FRAGMENT_SEARCH_TO){
                mCalendarTo.set(Calendar.HOUR
                        ,data.getIntExtra(TimePickerFragment.BUNDLE_TIME_PICKER_FRAGMENT_HOUR,0));

                mCalendarTo.set(Calendar.MINUTE
                        ,data.getIntExtra(TimePickerFragment.BUNDLE_TIME_PICKER_FRAGMENT_MINUTES,0));

            }

        }
    }

    @Override
    public void updateRecyclerView() {
    mTaskRecyclerViewAdapter.setTaskList(
            mTaskViewModel.getListTask(mCalendarFrom.getTime()
                    ,mCalendarTo.getTime()
                    ,mFragmentSearchTaskBinding.editTextTextTitle.getText().toString()
                    ,mFragmentSearchTaskBinding.editTextTextDescription.getText().toString())
    );

    mTaskRecyclerViewAdapter.notifyDataSetChanged();
    }
}