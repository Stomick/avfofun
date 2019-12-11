package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.FilterModel;

import java.util.List;

public interface SearchView extends BaseView {
    void showList(List<Act> events);
    void openFilter(FilterModel model);
}
