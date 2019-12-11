package com.team.noty.event.network.models.request.create;

public abstract class RqEventCom {

    public String category_id;
    public String id;
    public String file;
    public String title;
    public String description;
    public String place;
    public String date;
    public String price;
    public Integer age_limit;
    public Integer count_peoples;
    public Boolean moderation;
    public String video;
    public Double latitude;
    public Double longitude;

    public abstract String path();
}
