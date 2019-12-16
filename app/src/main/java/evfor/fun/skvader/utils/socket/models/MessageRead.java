package evfor.fun.skvader.utils.socket.models;

import java.util.List;

public class MessageRead {
    public Integer userId;
    public String recipient;
    public List<String> messages;

    public MessageRead(Integer userId, List<String> messages) {
        this.userId = userId;
        this.messages = messages;
    }
}
