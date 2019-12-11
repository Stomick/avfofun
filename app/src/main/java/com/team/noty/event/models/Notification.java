package com.team.noty.event.models;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.Spanned;

import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.Pair;
import com.team.noty.event.utils.Predicatable;

import java.util.Objects;

public class Notification implements Comparable<Notification>, Predicatable {
    public String image_url;
    public String title;
    public String date;
    public String senderId;
    public Spanned message;
    public Intent intent;

    @Override
    public int compareTo(@NonNull Notification notification) {
        String timedate0[] = date.split("\\s+");
        String timedate1[] = notification.date.split("\\s+");
        if (timedate0.length != 2 || timedate1.length != 2)
            return date.compareTo(notification.date);
        String date0 = getDate(timedate0), date1 = getDate(timedate1),
                time0 = getTime(timedate0), time1 = getTime(timedate1);
        int compare = -DateFormatter.compareDDMMYYYY(date0, date1);
        if (compare == 0)
            return -time0.compareTo(time1);
        return compare;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(title, that.title);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {

        return Objects.hash(title);
    }

    private String getDate(String[] timedate) {
        return timedate[0].contains(":") ? timedate[1] : timedate[0];
    }

    private String getTime(String[] timedate) {
        return (!timedate[0].contains(":")) ? timedate[1] : timedate[0];
    }

    @Override
    public boolean contain(String word) {
        return title != null && title.contains(word) || message != null && message.toString().contains(word);
    }
}
