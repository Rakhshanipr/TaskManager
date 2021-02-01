package com.example.taskmanager.services.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.model.User;

import java.util.List;
import java.util.UUID;

@Dao
public interface TaskDAO {

    @Insert
    void add(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM Task WHERE Id=:uuid")
    Task get(UUID uuid);

    @Query("SELECT * FROM Task WHERE State=:state and User=:user")
    List<Task> getList(State state, UUID user);

    @Query("SELECT * FROM Task WHERE User=:user")
    List<Task> getList(UUID user);

}
