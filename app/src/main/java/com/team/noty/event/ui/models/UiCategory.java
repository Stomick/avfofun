package com.team.noty.event.ui.models;

public class UiCategory {
    public String id;
    public String title;
    public String url;
    public boolean checked = false;

    public UiCategory(String id, String name, String url) {
        this.id = id;
        this.title = name;
        this.url = url;
    }
}
