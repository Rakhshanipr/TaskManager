package com.example.taskmanager.services.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskmanager.services.model.User;

import java.util.List;
import java.util.UUID;

@Dao
public interface UserDAO {

    @Insert
    void add(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM [user]")
    List<User> getList();

    @Query("SELECT EXISTS(SELECT * FROM [user] WHERE UserName =:username and Password=:password)")
    boolean isValid(String username,String password);

    @Query("SELECT * FROM [USER] WHERE UserName=:username and Password=:password")
    User retValidUser(String username,String password);

}
