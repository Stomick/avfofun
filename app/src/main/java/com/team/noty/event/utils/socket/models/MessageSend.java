package com.team.noty.event.utils.socket.models;

public class MessageSend {
    public transient int update;
    public String message;
    public String photo;
    public String audio;
    public String userId;

    public static MessageSend TextMessage(String message, int update){
        MessageSend messageSend = new MessageSend();
        messageSend.message = message;
        messageSend.update = update;
        return messageSend;
    }

    public static MessageSend PhotoMessage(String photo, int update){
        MessageSend messageSend = new MessageSend();
        messageSend.photo = photo;
        messageSend.update = update;
        return messageSend;
    }


    public static MessageSend VoiceMessage(byte[] audio, int update){
        MessageSend messageSend = new MessageSend();
        messageSend.audio = android.util.Base64.encodeToString(audio, android.util.Base64.DEFAULT);
        messageSend.update = update;
        return messageSend;
    }

    public boolean isPhoto(){
        return photo!=null;
    }

    public boolean isVoice(){
        return audio!=null;
    }

    public boolean isText(){
        return message!=null;
    }

    public String body(){
        if(photo!=null)
            return photo;
        return message;
    }
}
