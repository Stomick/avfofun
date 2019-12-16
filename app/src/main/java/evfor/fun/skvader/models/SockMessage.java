package evfor.fun.skvader.models;

import com.google.gson.annotations.SerializedName;

public class SockMessage {

    public int playProgress = 0;
    public int maxProgress = 0;

    public boolean fromMe() {
        return true;
    }

    public boolean notHasUser() {
        return name == null || name.isEmpty();
    }

    public enum Status {
        DELIVER, READ, SEND
    }

    public enum Type {
        TEXT, IMAGE, VOICE, WRITE
    }

    @SerializedName("user_id")
    public Integer user_id;

    @SerializedName("message_id")
    public Integer message_id;

    @SerializedName("status")
    public Integer status;

    @SerializedName("usstatus")
    public Integer usstatus;

    @SerializedName("userAvatar")
    public String userAvatar;

    @SerializedName("name")
    public String name;

    @SerializedName("date")
    public String date;

    @SerializedName("text")
    public String text;

    public SockMessage.Type type;

    public static SockMessage wrtite() {
        SockMessage message = new SockMessage();
        message.type = SockMessage.Type.WRITE;
        return message;
    }
}
