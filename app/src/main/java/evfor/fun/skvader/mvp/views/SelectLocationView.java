package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Location;

public interface SelectLocationView extends BaseView {
    void setCurrentLoc(Location loc);
}
