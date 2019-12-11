package com.team.noty.event.convertors;

import android.content.res.Resources;

import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActStatus;
import com.team.noty.event.models.Community;
import com.team.noty.event.models.EnterStatus;
import com.team.noty.event.models.Event;
import com.team.noty.event.network.URLS;
import com.team.noty.event.network.models.response.RsAct;

import java.util.List;

import javax.inject.Inject;

public class ActConverter implements Converter<Act, RsAct> {

    private Resources resources;

    @Inject
    ActConverter(Resources resources) {
        this.resources = resources;
    }

    @Override
    public Act convert(RsAct rsAct) {
        Act act = rsAct.isEvent() ? new Event() : new Community();
        act.id = rsAct.getID();
        act.longitude = rsAct.longitude;
        act.latitude = rsAct.latitude;
        act.address = rsAct.place;
        act.title = rsAct.title;
        act.age_limit = rsAct.age_limit;
        act.price = rsAct.price;
        act.category_id = rsAct.category_id;
        act.count_peoples = rsAct.enter_count;
        act.max_count = rsAct.max_count > 9999 ? 0 : rsAct.max_count;
        act.description = rsAct.description;
        act.imageUrl = URLS.imgdomain + rsAct.logo;
        act.video = rsAct.video;
        act.user_id = rsAct.user_id;
        act.rate = rsAct.rating;
        act.mod = rsAct.moderation != 0;
        act.users = rsAct.joined;
        act.enterStatus = EnterStatus.valueFrom(rsAct.enter_status);
        act.setStatus(ActStatus.valueFrom(rsAct.status));
        if (act instanceof Event) {
            ((Event) act).time = rsAct.time;
            ((Event) act).date = rsAct.date;
        } else {
            ((Community) act).days = rsAct.visiting_days;
            ((Community) act).bTime = String.valueOf(rsAct.start_time);
            ((Community) act).eTime = String.valueOf(rsAct.end_time);
            ((Community) act).forDisplayDays = setVisitingDays(rsAct.visiting_days);
        }
        return act;
    }

    private String setVisitingDays(List<String> visits_days) {
        StringBuilder stringBuilder = new StringBuilder();
        if (visits_days != null)
            for (String d : visits_days)
                stringBuilder.append(getDay(d));
        return stringBuilder.toString();
    }

    private String getDay(String num) {
        int i = "1234567".indexOf(num);
        if (i >= 0)
            return resources.getStringArray(R.array.days_of_week_short)[i] + " ";
        else return "";
    }
}
