package com.example.taskmanager.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.ActivitySearchBinding;
import com.example.taskmanager.databinding.ActivityViewPagerBinding;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.veiwmodel.MainViewModel;
import com.example.taskmanager.view.fragment.SearchTaskFragment;

public class SearchActivity extends AppCompatActivity implements MainViewModel.ICallBacksMainViewModel {

    //region defind static method and variable

    public static Intent newIntent(Context source) {
        Intent intent = new Intent(source, SearchActivity.class);
        return intent;
    }

    //endregion

    ActivitySearchBinding mActivitySearchBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySearchBinding= ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mActivitySearchBinding.getRoot());

        setInitial();

    }

    private void setInitial() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_search,SearchTaskFragment.newInstance())
                .commit();
    }

    @Override
    public void startSahreActivity(Intent intet) {
        startActivity(intet);
    }

    @Override
    public void Logined(User user) {

    }

    @Override
    public void showActivity(Intent intent) {

    }
}