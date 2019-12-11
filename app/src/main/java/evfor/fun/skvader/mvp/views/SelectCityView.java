package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.City;

import java.util.List;

public interface SelectCityView extends BaseView {
    void showList(List<City> cities);
}
