package com.team.noty.event.network.models.response;

import com.google.gson.GsonBuilder;

import java.util.List;

public class RsNotification extends RsBase {
    public List<Notification> messages;

    public static class Notification {
        public String message;
        public String title;
        public String object_id;
        public String object_type;
        public Integer notification_id;
        public Integer cause_id;
        public String sender_id;
        public String status;
        public String created;
        public String logo;

        public String getID() {
            return object_type.equals("chat")
                    ? createObjId().senderId
                    : object_type.equals("filter")
                    ? createObjFilter().cat
                    : object_id;
        }

        public class ObjFilter {
            public String type, day, cat;

        }

        public class ObjId {
            public String senderId;
        }

        public ObjFilter createObjFilter() {
            return new GsonBuilder().create().fromJson(object_id, ObjFilter.class);
        }

        public ObjId createObjId() {
            return new GsonBuilder().create().fromJson(object_id, ObjId.class);
        }
    }
}
