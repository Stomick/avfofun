package evfor.fun.skvader.ui.models;

import com.google.gson.annotations.SerializedName;

public class ChatRoom {

    @SerializedName("roomId")
    public int roomId;

    @SerializedName("name")
    public String name;

    @SerializedName("userAvatar")
    public String userAvatar;
}
