package evfor.fun.skvader.utils.socket.models;

public class ModelConnect {
    private static final String EVENT = "evfor.fun.skvader";
    private static final String COMMUNITY = "community";
    public String eventId;
    public String userId;
    public String type;

    public ModelConnect(String eventId, boolean isEvent) {
        this.eventId = eventId;
        this.type = isEvent ? EVENT : COMMUNITY;
    }

    public ModelConnect(String eventId, String userId, boolean isEvent) {
        this.eventId = eventId;
        this.userId = userId;
        this.type = isEvent ? EVENT : COMMUNITY;
    }

    public String getEventId() {
        return eventId;
    }

    public String getType() {
        return type;
    }

    public String getEvent(MessageSend send) {
        if (send.isPhoto())
            return photoEvent();
        if (send.isVoice())
            return audioEvent();
        return messageEvent();
    }

    public String audioEvent() {
        if (userId == null)
            return "general-audio";
        else return "audio";
    }

    public String messageEvent() {
        if (userId == null)
            return "general-message";
        else return "message";
    }

    public String photoEvent() {
        if (userId == null)
            return "general-photo";
        else return "photo";
    }

    public String writeEvent() {
        if (userId == null)
            return "general-typing-message";
        else return "typing-message";
    }

    public String readEvent() {
        return "read-messages";
    }

}
