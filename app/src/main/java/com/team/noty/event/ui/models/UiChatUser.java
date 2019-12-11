package com.team.noty.event.ui.models;

public class UiChatUser {
    public String id;
    public String imageUrl;
    public String name;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UiChatUser && id != null && ((UiChatUser) obj).id != null)
            return id.equals(((UiChatUser) obj).id);
        if (obj instanceof String)
            return obj.equals(id);
        return super.equals(obj);
    }
}
