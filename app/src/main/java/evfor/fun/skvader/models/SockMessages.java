package evfor.fun.skvader.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SockMessages {

    @SerializedName("messages")
    public ArrayList<SockMessage> messages;
}
