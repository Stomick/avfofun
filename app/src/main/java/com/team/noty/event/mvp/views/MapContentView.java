package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.Location;

public interface MapContentView extends BaseView {
    void addPoint(Act act);
    void setCurrentLoc(Location loc);
}
