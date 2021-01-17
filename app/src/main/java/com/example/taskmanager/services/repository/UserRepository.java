package com.example.taskmanager.services.repository;

import com.example.taskmanager.services.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {

    public static User sOnlineUser;

    //region defind stativ method and variable
    private static UserRepository sUserRepository;
    private static List<User> sUserList;

    public static UserRepository getInstance() {
        if (sUserRepository == null)
            sUserRepository = new UserRepository();
        return sUserRepository;
    }

    //endregion

    private UserRepository() {
        sUserList = new ArrayList<>();
    }

    public void add(User user){
        sUserList.add(user);
    }

    public static User getsOnlineUser() {
        return sOnlineUser;
    }

    public static void setsOnlineUser(User onlineUser) {
        sOnlineUser = onlineUser;
    }

    public void insertList(List<User> users) {
        sUserList = users;
    }

    public void update(User user) {
        User oldUser=get(user.getId());
        oldUser.setUserName(user.getUserName());
        oldUser.setPassword(user.getPassword());
        oldUser.setAccessblity(user.getAccessblity());
    }

    public void delete(User user) {
        for (int i = 0; i < sUserList.size(); i++) {
            if (sUserList.get(i).getId() == user.getId())
                sUserList.remove(i);
        }
    }

    public void delete(UUID uuid) {
        for (int i = 0; i < sUserList.size(); i++) {
            if (sUserList.get(i).getId() == uuid)
                sUserList.remove(i);
        }
    }

    public User get(UUID uuid) {
        for (int i = 0; i < sUserList.size(); i++) {
            if (sUserList.get(i).getId() == uuid)
                return sUserList.get(i);
        }

        return null;
    }


    public List<User> getList() {
        return sUserList;
    }

    public boolean isValid(String username,String password){
        for (int i = 0; i< sUserList.size(); i++){
            if (sUserList.get(i).getUserName().equals(username)&& sUserList.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public User retValidUser(String username,String password){
        for (int i = 0; i< sUserList.size(); i++){
            if (sUserList.get(i).getUserName().equals(username)&& sUserList.get(i).getPassword().equals(password)){
                return sUserList.get(i);
            }
        }
        return null;
    }

}
