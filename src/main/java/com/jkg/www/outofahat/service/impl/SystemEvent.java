package com.jkg.www.outofahat.service.impl;

public enum SystemEvent {

    CREATE_USER_FAIL(1000, "Failed to create user");

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
