package com.example.taskmanager.services.repository;

import android.service.autofill.UserData;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;

@Database(entities = {Task.class, User.class},version = 1,exportSchema = false)
public abstract class TaskManagerDataBase extends RoomDatabase {

    public abstract TaskDAO mTaskDAO();

    public abstract UserDAO mUserDAO();

}
