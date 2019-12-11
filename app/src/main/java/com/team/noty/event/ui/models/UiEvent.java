package com.team.noty.event.ui.models;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.team.noty.event.R;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.FilterCreator;

import java.util.List;

public class UiEvent implements Comparable<UiEvent> {
    public String id;
    public String title;
    public String imageUrl;
    public String date;
    public List<String> visits_days;
    public String times;
    public String address;
    public String price;
    public int already;
    public int max_peaple;
    public boolean checked;
    public String category_id;

    public String getDateTime(Context context) {
        if (date != null)
            return date;
        else return setVisitingDays(context.getResources(), visits_days) + " " + times;
    }

    private String setVisitingDays(Resources r,List<String> visits_days) {
        StringBuilder stringBuilder = new StringBuilder();
        if (visits_days != null)
            for (String d : visits_days)
                stringBuilder.append(getDay(r, d));
        return stringBuilder.toString();
    }

    private String getDay(Resources r, String num) {
        int i = "1234567".indexOf(num);
        if (i >= 0)
            return r.getStringArray(R.array.days_of_week_short)[i] + " ";
        else return "";
    }
    public boolean isComm() {
        return visits_days != null && times != null;
    }

    public int compareTo(UiEvent event, FilterCreator.SORT sort) {
        if (sort == null)
            return 0;
        switch (sort) {
            case DATE:
                int dateCompare = compareDate(event);
                if (dateCompare == 0 && times != null && event.times != null)
                    return -times.compareTo(event.times);
                else return dateCompare;
            case NAME:
                return title.compareTo(event.title);
            default:
                return 0;
        }
    }

    private int compareDate(UiEvent event) {
        String date1 = date, date2 = event.date;
        if (date1 == null)
            date1 = DateFormatter.getNearestDate(visits_days);
        if (date2 == null)
            date2 = DateFormatter.getNearestDate(event.visits_days);
        return date1.compareTo(date2);
    }

    @Override
    public int compareTo(@NonNull UiEvent o) {
        return compareTo(o, FilterCreator.SORT.DATE);
    }
}
