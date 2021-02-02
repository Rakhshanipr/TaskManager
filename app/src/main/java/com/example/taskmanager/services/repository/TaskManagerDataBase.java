package com.example.taskmanager.services.repository;

import android.service.autofill.UserData;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;

@Database(entities = {Task.class, User.class},version = 1,exportSchema = false)
@TypeConverters({Task.StateEnumConverter.class,Task.DateConverter.class,Task.UUIDConverter.class})
public abstract class TaskManagerDataBase extends RoomDatabase {

    public abstract TaskDAO mTaskDAO();

    public abstract UserDAO mUserDAO();

}
