package com.team.noty.event.ui.models;

import android.graphics.Bitmap;

import com.team.noty.event.models.Notification;

public class UiPush {
    public Notification notification;
    public Bitmap image;

    public UiPush(Notification notification, Bitmap image) {
        this.notification = notification;
        this.image = image;
    }
}
