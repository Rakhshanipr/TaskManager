package com.example.taskmanager.veiwmodel;

import com.example.taskmanager.services.model.Task;
import com.example.taskmanager.services.repository.TaskRepository;

import java.util.Calendar;

public class ListRecyclerViewTaskViewModel {

    Task mTask;

    public ListRecyclerViewTaskViewModel(Task task) {
        mTask = task;
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

    public String getTitle() {
        return mTask.getTitle();
    }

    public String getDescribe() {
        return mTask.getDescribe();
    }

    public String getState() {
        return mTask.getState().toString();
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());

        int n = calendar.get(Calendar.MONTH);
        String date = calendar.get(Calendar.YEAR) + " / " + calendar.get(Calendar.DAY_OF_MONTH) + " / " + calendar.get(Calendar.MONTH)
                + "     " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + ((calendar.get(Calendar.AM_PM) == 1) ? "AM" : "PM");
        ;


        return date;
    }

    public void deleteTask() {

    }

    public void editTask() {

    }

}
