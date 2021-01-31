package com.example.taskmanager.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentTimePickerBinding;


public class TimePickerFragment extends DialogFragment {

    //region defind staic method and variable
    public static final String BUNDLE_TIME_PICKER_FRAGMENT_HOUR = "com.example.taskmanager.view.fragment.TimePickerFragment.hourOfTimePicker";
    public static final String BUNDLE_TIME_PICKER_FRAGMENT_MINUTES = "com.example.taskmanager.view.fragment.TimePickerFragment.minutesOfTimePicker";


    public static TimePickerFragment newInstance() {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    FragmentTimePickerBinding mFragmentTimePickerBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentTimePickerBinding= DataBindingUtil.inflate(inflater
                ,R.layout.fragment_time_picker
                ,container,false);
        setListners();


        return mFragmentTimePickerBinding.getRoot();
    }

    private void setListners() {
        mFragmentTimePickerBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        mFragmentTimePickerBinding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra(BUNDLE_TIME_PICKER_FRAGMENT_HOUR
                        , mFragmentTimePickerBinding.timePickerTime.getHour());

                intent.putExtra(BUNDLE_TIME_PICKER_FRAGMENT_MINUTES
                        ,mFragmentTimePickerBinding.timePickerTime.getMinute());

                Fragment fragment = getTargetFragment();
                fragment.onActivityResult(getTargetRequestCode()
                        , Activity.RESULT_OK, intent);
                getDialog().dismiss();
            }
        });

    }
}