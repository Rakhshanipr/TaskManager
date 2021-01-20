package com.example.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentLoginBinding;
import com.example.taskmanager.veiwmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    //region static method and variable
    public static final String TAG_USER_FRAGMENT = "com.example.taskmanager.view.fragment.tag_user_fragment";
    //endregion
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    FragmentLoginBinding mFragmentLoginBinding;
    LoginViewModel mLoginViewModelViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentLoginBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_login, container, false);
        setListners();
        mLoginViewModelViewModel = new LoginViewModel();
        return mFragmentLoginBinding.getRoot();
    }

    private void setListners() {
        mFragmentLoginBinding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginViewModelViewModel.LoginButtonClicked(getActivity(),
                        mFragmentLoginBinding.editTextUserName.getText().toString()
                        , mFragmentLoginBinding.editTextPassword.getText().toString());
            }
        });

        mFragmentLoginBinding.buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginViewModelViewModel.UserButtonClicked(getFragmentManager(), TAG_USER_FRAGMENT);
            }
        });
    }


}