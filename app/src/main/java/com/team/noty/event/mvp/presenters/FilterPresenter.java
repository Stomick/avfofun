package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.mvp.views.FilterView;

@InjectViewState
@SuppressLint("CheckResult")
public class FilterPresenter extends BasePresenter<FilterView> {

    private FilterModel filterModel = new FilterModel();

    public FilterPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void loadCity(){
        getViewState().setCity(AuthData.city);
    }

    public void initFilterModel(FilterModel filterModel) {
        this.filterModel = filterModel;
    }

    public void finish(){
        getViewState().finish(filterModel);
    }
}
