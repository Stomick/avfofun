package com.team.noty.event.utils.social;

public enum Social {

    FACEBOOK("facebook", "fb", "https://www.facebook.com/profile.php?id="),
    VK("vkontakte", "vk", "https://vk.com/"),
    TW("twitter", "tw", "https://twitter.com/"),
    INST("instagram", "inst", "https://www.instagram.com/");
    String field, shortField, url;

    Social(String field, String shortField, String url) {
        this.field = field;
        this.shortField = shortField;
        this.url = url;
    }

    public String getBaseUrl() {
        return url;
    }

    public String getUrl(String id) {
        if (id == null)
            return "";
        return url + id;
    }

    public String getShortField() {
        return shortField;
    }

    @Override
    public String toString() {
        return field;
    }
}
