package com.team.noty.event.models;

public class ActAdmin extends ActId {
    public boolean creator;

    public ActAdmin(String id, boolean isEvent, boolean creator) {
        super(id, isEvent);
        this.creator = creator;
    }
}
