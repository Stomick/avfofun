package com.team.noty.event.mvp.views;

import com.team.noty.event.models.City;

import java.util.List;

public interface SelectCityView extends BaseView {
    void showList(List<City> cities);
}
