package evfor.fun.skvader.models;

import evfor.fun.skvader.app.AuthData;

import java.util.Date;

public class Message {

    public enum Status {
        DELIVER, READ, SEND
    }

    public enum Type {
        TEXT, IMAGE, VOICE, WRITE
    }

    public int updateId = -1;
    public String id;
    public String body;
    public Date date;
    public Status status;
    public Type type;
    public String name;
    public String sender;
    public String recipient;
    public int playProgress = -1;
    public int maxProgress = -1;
    public boolean isPlayng = false;

    public Message() {
    }

    public Message(String id, Status status) {
        this.id = id;
        this.status = status;
    }

    public static Message wrtite() {
        Message message = new Message();
        message.type = Type.WRITE;
        return message;
    }
    public boolean isRead()
    {
        return status.equals(Status.READ);
    }
    public Message(String body, Type type) {
        this.body = body;
        this.type = type;
        this.sender = AuthData.getID();
        this.status = Status.SEND;
    }

    public Message(String id, int updateId, Date date, Type type) {
        this.id = id;
        this.updateId = updateId;
        this.date = date;
        this.type = type;
    }

    public boolean fromMe() {
        return AuthData.equalId(sender);
    }

    public void update(Message message) {
        if (message.id != null)
            id = message.id;
        if (message.date != null)
            date = message.date;
        if (message.status != null)
            status = message.status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Message) {
            if (updateId >= 0 && ((Message) obj).updateId >= 0)
                return updateId == ((Message) obj).updateId;
            if (id != null && ((Message) obj).id != null)
                return id.equals(((Message) obj).id);
        }
        return super.equals(obj);
    }

    public boolean notHasUser(){
        return name == null || name.isEmpty();
    }

    public static void toDeliver(Message message){
        message.status = Status.DELIVER;
    }
}
