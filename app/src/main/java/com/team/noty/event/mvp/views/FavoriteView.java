package com.team.noty.event.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.team.noty.event.models.Act;
import com.team.noty.event.ui.models.UiProfileEvent;

import java.util.List;

public interface FavoriteView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showList(List<Act> events);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setCounts(int events, int comm);
}
