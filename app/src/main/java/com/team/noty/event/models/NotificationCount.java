package com.team.noty.event.models;

public class NotificationCount {
    public String count;
    public int addCount = 0;

    public NotificationCount(String count) {
        this.count = count;
    }

    public NotificationCount(int addCount) {
        this.addCount = addCount;
    }
}
