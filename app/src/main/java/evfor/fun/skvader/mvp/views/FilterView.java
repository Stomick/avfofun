package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.FilterModel;

public interface FilterView extends BaseView {
    void setCity(String city);
    void finish(FilterModel filterModel);
}
