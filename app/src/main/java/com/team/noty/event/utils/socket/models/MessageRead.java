package com.team.noty.event.utils.socket.models;

import java.util.List;

public class MessageRead {
    public String userId;
    public String recipient;
    public List<String> messages;

    public MessageRead(String userId, List<String> messages) {
        this.userId = userId;
        this.messages = messages;
    }
}
