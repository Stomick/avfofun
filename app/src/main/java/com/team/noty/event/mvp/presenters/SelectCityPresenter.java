package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.location.Location;

import com.arellomobile.mvp.InjectViewState;
import com.google.android.gms.maps.model.LatLng;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.mvp.views.SelectCityView;
import com.team.noty.event.network.models.request.RqCity;
import com.team.noty.event.models.City;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
@SuppressLint("CheckResult")
public class SelectCityPresenter extends BasePresenter<SelectCityView> {

    @Inject
    Interactor<Observable<City>, RqCity> cityProvider;
    private Disposable disposable;

    public SelectCityPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void search(String text) {
        dispose();
        request(new RqCity(text));
    }

    public void search(com.team.noty.event.models.Location latLng) {
        dispose();
        request(new RqCity(latLng.latitude, latLng.longitude));
    }

    private void dispose() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void request(RqCity city) {
        disposable = cityProvider.call(city)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showList, this::onError);
    }
}
