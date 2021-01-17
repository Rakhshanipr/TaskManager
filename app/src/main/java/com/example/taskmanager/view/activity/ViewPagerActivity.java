package com.example.taskmanager.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.ActivityViewPagerBinding;
import com.example.taskmanager.veiwmodel.Main;

public class ViewPagerActivity extends AppCompatActivity {
//region defind static method and variable
    public static Intent newIntent(Context source){
        Intent intent=new Intent(source, ViewPagerActivity.class);
        return intent;
    }
    //endregion

    ActivityViewPagerBinding mActivityViewPagerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mActivityViewPagerBinding= DataBindingUtil.inflate(this.getLayoutInflater(),R.layout.activity_view_pager,null,false);
        mActivityViewPagerBinding.viewPager2ListTask.setAdapter(Main.createTaskViewPagerAdapter(this));
    }
}