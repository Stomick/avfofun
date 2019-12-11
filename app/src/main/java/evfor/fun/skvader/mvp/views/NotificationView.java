package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Notification;

import java.util.List;

public interface NotificationView extends BaseView {
    void showList(List<Notification> notifications);
    void addNotification(Notification notification);
}
