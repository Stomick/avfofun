package evfor.fun.skvader.models;

import com.google.gson.annotations.SerializedName;

public class SockMessage {

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

}
