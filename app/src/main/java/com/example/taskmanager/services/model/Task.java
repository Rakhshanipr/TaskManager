package com.example.taskmanager.services.model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mUUID;

    private String mTitle;

    private String mDescribe;

    private State mState;

    private Date mDate;

    private UUID mUser;
}
