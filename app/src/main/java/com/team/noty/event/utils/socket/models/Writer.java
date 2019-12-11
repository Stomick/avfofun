package com.team.noty.event.utils.socket.models;


import com.team.noty.event.repository.Identified;

public class Writer implements Identified {
    public String userId;

    public Writer(String id) {
        this.userId = id;
    }

    @Override
    public String id() {
        return userId;
    }
}
