package com.example.taskmanager.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.taskmanager.R;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.veiwmodel.LoginViewModel;
import com.example.taskmanager.view.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity implements LoginViewModel.ICallBacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.container_login);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.container_login, LoginFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void Logined(User user) {
        Intent viewPagerActivity=ViewPagerActivity.newIntent(this);
        startActivity(viewPagerActivity);
    }
}