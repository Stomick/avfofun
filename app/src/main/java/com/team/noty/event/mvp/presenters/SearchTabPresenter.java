package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.mvp.views.tabs.SearchTabView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class SearchTabPresenter extends BasePresenter<SearchTabView> {

    @Inject
    DataProvide<List<Category>> provideCategories;
    @Inject
    Interactor<Observable<Act>, FilterModel> filter;

    public SearchTabPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void getCategories() {
        provideCategories.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showCategory);
    }

    public void getPopular() {
        filter.call(new FilterModel().setPop(FilterModel.Popular.up).setCity(AuthData.city))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showEvents, this::onError);
    }
}
