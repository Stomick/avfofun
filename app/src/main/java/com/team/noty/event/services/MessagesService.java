package com.team.noty.event.services;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.team.noty.event.utils.Converter;
import com.team.noty.event.utils.notification.NotificationsUtils;

public class MessagesService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("my","message recieved");
        if (remoteMessage.getData() != null)
            NotificationsUtils.getINSTANCE().push(Converter.toRs(remoteMessage.getData()));
    }

    public MessagesService() {
        super();
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }
}
