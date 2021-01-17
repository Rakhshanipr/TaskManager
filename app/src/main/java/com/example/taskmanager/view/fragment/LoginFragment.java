package com.example.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.taskmanager.R;
import com.example.taskmanager.databinding.FragmentLoginBinding;
import com.example.taskmanager.veiwmodel.Login;

public class LoginFragment extends Fragment {

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    FragmentLoginBinding mFragmentLoginBinding;
    Login mLoginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        mFragmentLoginBinding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_login,container,false);
        setListners();
        mLoginViewModel=new Login();
        return mFragmentLoginBinding.getRoot();
    }

    private void setListners() {
        mFragmentLoginBinding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (mLoginViewModel.isValid(mFragmentLoginBinding.editTextUserName.getText().toString()
                        ,mFragmentLoginBinding.editTextPassword.getText().toString())){
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.container_login, LoginFragment.newInstance())
                            .commit();
                }*/

                mLoginViewModel.LoginClicked(getActivity(),
                        mFragmentLoginBinding.editTextUserName.getText().toString()
                        ,mFragmentLoginBinding.editTextPassword.getText().toString());


            }
        });
    }


}