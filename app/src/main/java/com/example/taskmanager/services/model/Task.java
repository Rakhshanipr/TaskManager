package com.example.taskmanager.services.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;
import java.util.jar.Attributes;

@Entity
public class Task{

    @PrimaryKey
    @ColumnInfo(name = "Id")
    @NonNull
    private UUID mId;

    @ColumnInfo(name = "Title")
    private String mTitle;

    @ColumnInfo(name = "Describe")
    private String mDescribe;

    @ColumnInfo(name = "State")
    private State mState;

    @ColumnInfo(name = "Date")
    private Date mDate;

    @ColumnInfo(name = "User")
    private UUID mUser;



    public Task() {
        mId=UUID.randomUUID();
    }

    public Task(String title, String describe, State state, Date date, UUID user) {
        mTitle = title;
        mDescribe = describe;
        mState = state;
        mDate = date;
        mUser = user;
        mId=UUID.randomUUID();
    }

    public void setId(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescribe() {
        return mDescribe;
    }

    public void setDescribe(String describe) {
        mDescribe = describe;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getUser() {
        return mUser;
    }

    public void setUser(UUID user) {
        mUser = user;
    }

    public static class UUIDConverter{

        @TypeConverter
        public String fromUUID(UUID value){
            return value.toString();
        }

        @TypeConverter
        public UUID fromString(String value){
            return UUID.fromString(value);
        }
    }

    public static class DateConverter{
        @TypeConverter
        public Long fromDate(Date value){
            return value.getTime();
        }

        @TypeConverter
        public Date fromLong(Long value){
            return new Date(value);
        }
    }

    public static class StateEnumConverter{
        @TypeConverter
        public String fromState(State value){
            return value.toString();
        }

        @TypeConverter
        public State fromState(String value){
            return State.valueOf(value);
        }
    }

}
