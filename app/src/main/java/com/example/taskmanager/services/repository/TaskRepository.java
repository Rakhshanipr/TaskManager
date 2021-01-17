package com.example.taskmanager.services.repository;

import com.example.taskmanager.services.model.State;
import com.example.taskmanager.services.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {

    private static List<Task> sTaskList;
    public static TaskRepository sTaskRepository;

    private TaskRepository() {
        sTaskList = new ArrayList<>();
    }

    public static TaskRepository getInstance() {
        if (sTaskRepository == null)
            sTaskRepository = new TaskRepository();
        return sTaskRepository;
    }

    public void add(Task task) {
        sTaskList.add(task);
    }

    public void insertList(List<Task> tasks) {
        sTaskList = new ArrayList<>();
        sTaskList = tasks;
    }


    public void update(Task e) {
        Task task1 = get(e.getId());
        task1.setTitle(e.getTitle());
        task1.setDescribe(e.getDescribe());
        task1.setState(e.getState());
        task1.setUser(e.getUser());
        task1.setDate(e.getDate());
    }

    public void delete(Task task) {
        sTaskList.remove(task);
    }

    public void delete(UUID uuid) {
        for (int i = 0; i < sTaskList.size(); i++) {
            if (sTaskList.get(i).getId() == uuid) {
                sTaskList.remove(i);
                return;
            }
        }
    }


    public Task get(UUID uuid) {
        for (int i = 0; i < sTaskList.size(); i++) {
            if (sTaskList.get(i).getId() == uuid) {
                return sTaskList.get(i);
            }
        }
        return null;
    }


    public List<Task> getList() {
        return sTaskList;
    }

    public List<Task> getList(State state) {
        List<Task> list = new ArrayList<>();
        for (int i = 0; i < sTaskList.size(); i++) {
            if (sTaskList.get(i).getState().equals(state) && sTaskList.get(i).getUser() == UserRepository.getsOnlineUser().getId()) {
                list.add(sTaskList.get(i));
            }
        }
        return list;
    }

    public void deletWithState(State state) {
        boolean reapit=true;
        while (reapit) {
            reapit=false;
            for (int i = 0; i < sTaskList.size(); i++) {
                if (sTaskList.get(i).getState().equals(state) && sTaskList.get(i).getUser() == UserRepository.getsOnlineUser().getId()) {
                    sTaskList.remove(i);
                    reapit=true;
                    break;
                }
            }
        }

    }


}
