package com.example.taskmanager.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.ActivityViewPagerBinding;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.veiwmodel.MainViewModel;
import com.example.taskmanager.veiwmodel.TaskViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

public class ViewPagerActivity extends AppCompatActivity
        implements MainViewModel.ICallBacksMainViewModel {

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

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);

    }

    private void setInitial() {
        mActivityViewPagerBinding.viewPager2ListTask.setAdapter(
                MainViewModel.createTaskViewPagerAdapter(this));
        String[] titles={"ToDo","Doing","Done"};
        // attaching tab mediator
        new TabLayoutMediator(mActivityViewPagerBinding.tabLayout
                , mActivityViewPagerBinding.viewPager2ListTask,
                (tab, position) -> tab.setText(titles[position])).attach();
    }


    @Override
    public void startSahreActivity(Intent intet) {
        startActivity(intet);
    }


    @Override
    public void Logined(User user) {
        Intent viewPagerActivity=ViewPagerActivity.newIntent(this);
        startActivity(viewPagerActivity);
    }

    @Override
    public void showActivity(Intent intent) {
        startActivity(intent);
    }


}