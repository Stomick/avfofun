package evfor.fun.skvader.network.models.response;

public class RsMessage {
    public String _id;
    public String updatedAt;
    public String createdAt;
    public String eventId;
    public String senderId;
    public String sender;
    public String message;
    public String recipient;
    public String photoUrl;
    public String audioUrl;
    public boolean isRead;

    public String getSender(){
        if(sender==null)
            return senderId;
        else return sender;
    }

    public String getBody() {
        if (message != null)
            return message;
        if (photoUrl != null)
            return photoUrl;
        if (audioUrl != null)
            return audioUrl;
        return null;
    }
}

