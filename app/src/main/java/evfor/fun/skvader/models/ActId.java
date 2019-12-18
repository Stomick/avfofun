package evfor.fun.skvader.models;

import evfor.fun.skvader.repository.Identified;

import java.io.Serializable;

public class ActId implements Identified, Serializable {
    public static final String TAG = ActId.class.getSimpleName();

    public String id = "";
    public boolean isEvent;
    public Integer room_id;

    public ActId() {
    }

    public ActId(String id, boolean isEvent) {
        this.id = id;
        this.isEvent = isEvent;
        //this.room_id = Integer.valueOf(room_id);
    }

    public ActId(String id, boolean isEvent , String room_id) {
        this.id = id;
        this.isEvent = isEvent;
        this.room_id = Integer.valueOf(room_id);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ActId)
            if (id != null && !id.isEmpty() && ((ActId) obj).id != null && !((ActId) obj).id.isEmpty())
                return id.equals(((ActId) obj).id) && isEvent == ((ActId) obj).isEvent;
        return super.equals(obj);
    }

    @Override
    public String id() {
        return id;
    }

    public String type() {
        return isEvent ? "events" : "community";
    }

    public boolean isEvent(){
        return isEvent;
    }

    @Override
    public String toString() {
        return String.valueOf(id()) + " : " + type();
    }
}
