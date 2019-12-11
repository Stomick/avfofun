package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Location;

public interface MapContentView extends BaseView {
    void addPoint(Act act);
    void setCurrentLoc(Location loc);
}
