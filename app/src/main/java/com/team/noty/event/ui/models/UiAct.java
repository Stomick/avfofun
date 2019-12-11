package com.team.noty.event.ui.models;

import com.team.noty.event.models.Act;

public class UiAct {
    public Act act;
    public String formatedDate;

    public UiAct(Act act) {
        this.act = act;
    }

    public String getCount() {
        return String.valueOf(act.count_peoples) + ((act.max_count != 9999) ? ("/" + String.valueOf(act.max_count)) : "");
    }
}
