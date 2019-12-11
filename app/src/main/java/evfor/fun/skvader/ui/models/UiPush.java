package evfor.fun.skvader.ui.models;

import android.graphics.Bitmap;

import evfor.fun.skvader.models.Notification;

public class UiPush {
    public Notification notification;
    public Bitmap image;

    public UiPush(Notification notification, Bitmap image) {
        this.notification = notification;
        this.image = image;
    }
}
