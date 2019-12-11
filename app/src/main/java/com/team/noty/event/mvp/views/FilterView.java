package com.team.noty.event.mvp.views;

import com.team.noty.event.models.FilterModel;

public interface FilterView extends BaseView {
    void setCity(String city);
    void finish(FilterModel filterModel);
}
