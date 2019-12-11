package com.team.noty.event.ui.utils;

import android.content.Context;
import android.content.Intent;

import com.team.noty.event.app.Injector;
import com.team.noty.event.models.ActId;
import com.team.noty.event.ui.activities.DialogActivity;
import com.team.noty.event.ui.activities.EmptyActivity;
import com.team.noty.event.ui.activities.EventActivity;
import com.team.noty.event.ui.activities.GiveFeedbackActivity;
import com.team.noty.event.ui.activities.ParticipantsActivity;
import com.team.noty.event.ui.activities.search.SearchActivity;

public class NotificationIntent {

    public static Intent goTo(int notificationId, String objectId, boolean isEvent, String supportField) {
        Context context = Injector.get().getApp().context();
        switch (notificationId) {
            case 5:
                return GiveFeedbackActivity.intent(context, String.valueOf(objectId));
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case 7:
            case 10:
                if (String.valueOf(supportField).equalsIgnoreCase("1516"))
                    return ParticipantsActivity.intent(context, new ActId(objectId, isEvent), true);
            case 13:
            case 14:
                return EventActivity.intent(context, new ActId(String.valueOf(objectId), isEvent));
            case 8:
                return SearchActivity.getIntent(context, String.valueOf(objectId));
            case 19:
                return SearchActivity.getIntent(context, String.valueOf(objectId), supportField);
            case 15:
                return DialogActivity.intent(context, String.valueOf(objectId));
            case 12:
            case 11:
                return ParticipantsActivity.intentAdmin(context, new ActId(String.valueOf(objectId), isEvent));
            case 17:
                return EmptyActivity.cabinetIntent(context, String.valueOf(objectId));
        }
        return null;
    }
}
