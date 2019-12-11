package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Location;

public interface SelectLocationView extends BaseView {
    void setCurrentLoc(Location loc);
}
