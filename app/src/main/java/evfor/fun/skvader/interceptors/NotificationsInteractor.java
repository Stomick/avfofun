package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.models.Notification;
import evfor.fun.skvader.models.NotificationFilter;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsNotification;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

public class NotificationsInteractor implements Interactor<Observable<Notification>, NotificationFilter> {

    private MainApi api;
    private Converter<Notification, RsNotification.Notification> converter;

    @Inject
    NotificationsInteractor(MainApi api,
                            Converter<Notification, RsNotification.Notification> converter) {
        this.api = api;
        this.converter = converter;
    }

    @Override
    public Observable<Notification> call(NotificationFilter notificationFilter) {
        return api.getNotifications()
                .map(this::get)
                .flatMapObservable(Observable::fromIterable)
                .filter(new Filter(notificationFilter))
                .map(converter::convert);
    }

    private class Filter implements Predicate<RsNotification.Notification> {

        private NotificationFilter notificationFilter;

        Filter(NotificationFilter notificationFilter) {
            this.notificationFilter = notificationFilter;
        }

        @Override
        public boolean test(RsNotification.Notification notification) {
            if (notification.object_id == null)
                return false;
            switch (notificationFilter) {
                case MESSAGE:
                    return notification.object_type.equals("chat");
                case NEW:
                    return notification.notification_id.equals(8) || notification.notification_id.equals(19);
                case SYSTEM:
                    return !notification.object_type.equals("chat") && !notification.notification_id.equals(8);
                default:
                    return true;
            }
        }
    }

    private List<RsNotification.Notification> get(RsNotification notification) {
        return notification.messages;
    }
}
