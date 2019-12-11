package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Notification;

import java.util.List;

public interface NotificationView extends BaseView {
    void showList(List<Notification> notifications);
    void addNotification(Notification notification);
}
