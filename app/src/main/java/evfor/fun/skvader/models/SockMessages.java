package evfor.fun.skvader.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class SockMessages {

    @SerializedName("messages")
    public ArrayList<SockMessage> messages;

    public boolean contains(SockMessage write) {
        return messages.contains(write);
    }

    public int size() {
        return messages.size();
    }

    public SockMessage get(int position) {
        return messages.get(position);
    }

    public int indexOf(Message message) {
        return messages.indexOf(message);
    }

    public void add(SockMessage obj) {
        messages.add(obj);
    }
}
