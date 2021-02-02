package com.example.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentUserBinding;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.UserRepository;
import com.example.taskmanager.veiwmodel.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends DialogFragment {

    //region defind variable
    FragmentUserBinding mFragmentUserBinding;
    UserViewModel mUserViewModel;
    //endregion

    //region defind static method and variable
    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentUserBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_user,container,false);
        initial();
        setListners();

        return mFragmentUserBinding.getRoot();

    }

    private void initial() {
        mUserViewModel=new UserViewModel(getContext());
    }

    private void setListners() {
        mFragmentUserBinding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=mFragmentUserBinding.editTextTextUserName.getText().toString();
                String password=mFragmentUserBinding.editTextPassword.getText().toString();
                mUserViewModel.addUser(userName,password,0);
                getDialog().cancel();
            }
        });

        mFragmentUserBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
    }
}