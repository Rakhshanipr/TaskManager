package com.example.taskmanager.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.taskmanager.databinding.ActivityViewPagerBinding;
import com.example.taskmanager.veiwmodel.MainViewModel;

public class ViewPagerActivity extends AppCompatActivity {
    //region defind static method and variable
    public static Intent newIntent(Context source) {
        Intent intent = new Intent(source, ViewPagerActivity.class);
        return intent;
    }
    //endregion

    ActivityViewPagerBinding mActivityViewPagerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityViewPagerBinding = ActivityViewPagerBinding.inflate(getLayoutInflater());
        setContentView(mActivityViewPagerBinding.getRoot());
        setInitial();
    }

    private void setInitial() {
        mActivityViewPagerBinding.viewPager2ListTask.setAdapter(MainViewModel.createTaskViewPagerAdapter(this));
    }


}