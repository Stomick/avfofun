package evfor.fun.skvader.utils.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Notification;
import evfor.fun.skvader.network.models.response.RsNotification;
import evfor.fun.skvader.ui.models.UiPush;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class NotificationsUtils {
    private static NotificationsUtils INSTANCE;
    @Inject
    Context context;
    @Inject
    Converter<Notification, RsNotification.Notification> converter;
    @Inject
    Interactor<Single<Bitmap>, String> imageLoader;
    @Inject
    NotificationManager notificationManager;
    @Inject
    NotificationCompat.Builder notoficationBuilder;

    public static NotificationsUtils getINSTANCE() {
        return INSTANCE;
    }

    public static void create() {
        INSTANCE = new NotificationsUtils();
    }

    private NotificationsUtils() {
        Injector.get().getMain().inject(this);
    }


    public void push(RsNotification.Notification notification) {
        Single.just(notification)
                .map(converter::convert)
                .doOnSuccess(this::post)
                .flatMap(this::loadImage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::notify, Throwable::printStackTrace);
    }

    private void post(Notification notification) {
       // EventBus.post(new NotificationCount(1));
        notify(new UiPush(notification, null));

    }

    private Single<UiPush> loadImage(Notification notification) {
        return Single.zip(
                Single.just(notification),
                imageLoader.call(notification.image_url), UiPush::new);
    }

    private void notify(UiPush push) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            AudioAttributes att = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel("10001", "Events", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI,att);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert notificationManager != null;
            NotificationCompat.Builder notification = new NotificationCompat.Builder(context);

            notification.setChannelId("10001")
                    .setLargeIcon(push.image)
                    .setContentTitle(push.notification.title)
                    .setTicker(push.notification.title)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentText(push.notification.message)
                    .setPriority(android.app.Notification.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(push.notification.message))
                    .setContentIntent(pendingIntent(push));
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(0, notification.build());
        } else
            notificationManager.notify(0, notifyBuilder(push));
    }

    private android.app.Notification notifyBuilder(UiPush push) {
        return notoficationBuilder
                .setLargeIcon(push.image)
                .setContentTitle(push.notification.title)
                .setTicker(push.notification.title)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentText(push.notification.message)
                .setPriority(android.app.Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(push.notification.message))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent(push))
                .build();
    }

    private PendingIntent pendingIntent(UiPush push) {
        return PendingIntent.getActivity(context, 0, push.notification.intent, PendingIntent.FLAG_ONE_SHOT);
    }
}
