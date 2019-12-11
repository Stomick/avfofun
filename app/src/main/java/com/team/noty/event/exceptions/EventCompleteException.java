package com.team.noty.event.exceptions;

public class EventCompleteException extends Exception {
    public String id;

    public EventCompleteException(String id) {
        this.id = id;
    }
}
