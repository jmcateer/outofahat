package com.jkg.www.outofahat.service.impl;

public enum SystemEvent {

    USER_CREATE_FAIL(1000, "Failed to create user"),
    USER_FIND_ERROR(1001, "Failed to find user info.");

    private int id;
    private String description;

    private SystemEvent(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description + ": ";
    }
}
