package com.jkg.www.outofahat.service.impl;

public enum SystemEvent {

    USER_CREATE_FAIL(1000, "Failed to create user"),
    USER_FIND_ERROR(1001, "Failed to find user info."),

    PARTICIPANT_ADD_FAIL(2000, "Failed to add participant."),
    PARTICIPANT_RETIRIEVE_FAIL(2001, "Failed to retrieve participants."),

    EVENT_CREATE_FAIL(3000, "Failed to create new event."),
    EVENT_SAVE_FAIL(3001, "Failed to save event."),
    EVENT_FIND_FAIL(3002, "Failed to find events.");

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
