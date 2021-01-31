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
import com.example.taskmanager.databinding.FragmentDatePickerBinding;


public class DatePickerFragment extends DialogFragment {

    //region defind static method and variable

    public static final String BUNDLE_DATE_PICKER_FRAGMENT_YEAR = "com.example.taskmanager.view.fragment.yearOfDatePicker";
    public static final String BUNDLE_DATE_PICKER_FRAGMENT_MONTH = "com.example.taskmanager.view.fragment.monthOfDatePicker";
    public static final String BUNDLE_DATE_PICKER_FRAGMENT_DAY = "com.example.taskmanager.view.fragment.dayOfDatePicker";

    public static DatePickerFragment newInstance() {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    FragmentDatePickerBinding mFragmentDatePickerBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFragmentDatePickerBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_date_picker
                , container, false);

        setListners();

        return mFragmentDatePickerBinding.getRoot();
    }

    private void setListners() {
        mFragmentDatePickerBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        mFragmentDatePickerBinding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra(BUNDLE_DATE_PICKER_FRAGMENT_YEAR
                        , mFragmentDatePickerBinding.datePickerDate.getYear());

                intent.putExtra(BUNDLE_DATE_PICKER_FRAGMENT_MONTH
                        , mFragmentDatePickerBinding.datePickerDate.getMonth());

                intent.putExtra(BUNDLE_DATE_PICKER_FRAGMENT_DAY
                        , mFragmentDatePickerBinding.datePickerDate.getDayOfMonth());

                Fragment fragment = getTargetFragment();

                fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                getDialog().dismiss();
            }
        });

    }
}