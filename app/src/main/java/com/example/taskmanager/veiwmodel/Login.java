package com.example.taskmanager.veiwmodel;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.example.taskmanager.databinding.FragmentLoginBinding;
import com.example.taskmanager.services.model.User;
import com.example.taskmanager.services.repository.UserRepository;

public class Login {
    //region defind variable

    FragmentLoginBinding mFragmentLoginBinding;

    UserRepository mUserRepository;

    ICallBacks mCallBacks;
    //endregion
    public static MutableLiveData<String> name=new MutableLiveData<>();
    public Login(){
        mUserRepository=UserRepository.getInstance();
        mUserRepository.add(new User("ali","123",1));
    }



    public void LoginClicked(Activity activity, String userName, String password){
        if (mUserRepository.isValid(userName,password)){

            mUserRepository.setsOnlineUser(mUserRepository.retValidUser(userName,password));
            /*((AppCompatActivity)activity).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_login, ListFragment.newInstance())
                    .commit();*/

            if (activity instanceof ICallBacks){
                mCallBacks=(ICallBacks)activity;
            }else{
                throw new ClassCastException("your activity must implemented ICallBacks");
            }
            mCallBacks.Logined(mUserRepository.getsOnlineUser());
        }
    }


    public interface ICallBacks{
        void Logined(User user);
    }

}