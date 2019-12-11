package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.team.noty.event.app.Injector;
import com.team.noty.event.repository.data_observe.DataObserver;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Location;
import com.team.noty.event.mvp.views.MapContentView;
import com.team.noty.event.repository.data_provide.DataProvide;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class MapPresenter extends LocationPresenter<MapContentView> {

    @Inject
    Interactor<Observable<Act>, Location> actByLocation;
    @Inject
    DataProvide<Location> lastLocation;

    public MapPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void getList() {
        lastLocation.provide()
                .flatMapObservable(actByLocation::call)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        model->getViewState().addPoint(model),
                        throwable -> Log.e("my",throwable.getMessage()));
    }

    public void getMyLocation() {
        lastLocation.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setCurrentLoc,throwable -> getViewState().setCurrentLoc(new Location(39,50)));
    }
}
