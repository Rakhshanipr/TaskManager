package com.example.taskmanager.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taskmanager.R;

public class ViewPagerActivity extends AppCompatActivity {
//region defind static method and variable
    public static Intent newIntent(Context source){
        Intent intent=new Intent(source, ViewPagerActivity.class);
        return intent;
    }
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
    }
}