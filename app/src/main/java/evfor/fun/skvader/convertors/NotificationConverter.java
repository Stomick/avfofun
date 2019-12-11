package evfor.fun.skvader.convertors;

import android.content.Intent;
import android.text.Html;
import android.text.Spanned;

import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.Notification;
import evfor.fun.skvader.network.URLS;
import evfor.fun.skvader.network.models.response.RsNotification;
import evfor.fun.skvader.ui.utils.NotificationIntent;

import javax.inject.Inject;

public class NotificationConverter implements Converter<Notification, RsNotification.Notification> {

    @Inject
    NotificationConverter() {
    }

    @Override
    public Notification convert(RsNotification.Notification notification) {
        Notification uiNotification = new Notification();
        uiNotification.date = notification.created;
        uiNotification.title = createTitle(notification) == null ? " " : createTitle(notification);
        uiNotification.image_url = notification.logo == null ? notification.logo : URLS.imgdomain + notification.logo;
        uiNotification.message = createMessage(notification);
        uiNotification.intent = createIntent(notification);

        return uiNotification;
    }

    private String getTitleMessages(int index) {
        return Injector.get().getApp().context().getResources().getStringArray(R.array.messages)[index];
    }

    private Spanned createMessage(RsNotification.Notification notification) {
        if (notification.notification_id == 15)
            return Html.fromHtml(getTitleMessages(notification.notification_id - 1));
        if (notification.cause_id == 1)
            return Html.fromHtml(notification.message);
        return
                Html.fromHtml(getCauseMessage(notification.cause_id, notification.title));
    }

    private String getCauseMessage(int id, String field) {
        id--;
        String[] causes = Injector.get().getApp().context().getResources().getStringArray(R.array.causes);
        if (causes.length > id && id > 0) {
            return String.format(causes[id], field);
        }
        return "";
    }

    private String getSupportField(RsNotification.Notification notification) {
        if (notification.object_type.equals("filter"))
            return notification.createObjFilter().day;
        if (notification.cause_id == 15 || notification.cause_id == 16)
            return "1516";
        return null;
    }

    private Intent createIntent(RsNotification.Notification notification) {
        return NotificationIntent.goTo(
                notification.notification_id,
                notification.getID(),
                notification.object_type.contains("evfor.fun.skvader"),
                getSupportField(notification));
    }

    private String createTitle(RsNotification.Notification notification) {
        if (notification.notification_id == 15 || notification.notification_id == 0)
            return notification.title;
        else
            return getTitleMessages(notification.notification_id - 1);
    }
}
