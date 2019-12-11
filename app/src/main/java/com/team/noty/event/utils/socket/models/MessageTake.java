package com.team.noty.event.utils.socket.models;

public class MessageTake {
    public String message;
    public String audioUrl;
    public String photoUrl;
    public String userId;
    public String messageId;
    public String date;
    public String recipient;

    public String getMessage() {
        if (photoUrl != null)
            return photoUrl;
        if (audioUrl != null)
            return audioUrl;
        return message;
    }

    public int type(){
        if (photoUrl != null)
            return 2;
        if (audioUrl != null)
            return 1;
        return 0;
    }
}
