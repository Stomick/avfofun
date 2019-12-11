package com.team.noty.event.interceptors;

import android.content.res.Resources;

import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Community;
import com.team.noty.event.models.Event;

import java.util.List;

import javax.inject.Inject;

public class ShapingDateInteractor implements Interactor<String, Act> {
    private static final String days = "1234567";

    private Resources resources;

    @Inject
    public ShapingDateInteractor(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String call(Act act) {
        return (act instanceof Event) ? date((Event) act) : date((Community) act);
    }

    private String date(Event event) {
        return event.date + " / " + event.time;
    }

    private String date(Community community) {
        return setVisitingDays(community.days) + community.bTime + "-" + community.eTime;
    }

    private String setVisitingDays(List<String> visits_days) {
        StringBuilder stringBuilder = new StringBuilder();
        if (visits_days != null)
            for (String d : visits_days)
                stringBuilder.append(getDay(d));
        return stringBuilder.toString();
    }

    private String getDay(String num) {
        int i = days.indexOf(num);
        if (i >= 0)
            return resources.getStringArray(R.array.days_of_week_short)[i] + ", ";
        else return "";
    }
}
