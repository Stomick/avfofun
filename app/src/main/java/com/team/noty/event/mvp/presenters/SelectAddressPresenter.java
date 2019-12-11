package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.exceptions.NotFoundException;
import com.team.noty.event.models.Address;
import com.team.noty.event.models.Location;
import com.team.noty.event.mvp.views.SelectAddressView;
import com.team.noty.event.repository.data_provide.DataProvide1;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
@SuppressLint("CheckResult")
public class SelectAddressPresenter extends BasePresenter<SelectAddressView> {

    @Inject
    DataProvide1<List<Address>, Location> placeByLoc;

    @Inject
    DataProvide1<List<Address>, String> placeByWord;

    private Disposable searchAddress;

    public SelectAddressPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void getPlace(Location latLng) {
        dispose();
        searchAddress = placeByLoc.provide(latLng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setAddressList, this::showEmptyResMessage);
    }

    private void showEmptyResMessage(Throwable throwable) {
        if (throwable instanceof NotFoundException)
            getViewState().showInfo(R.string.address_not_found);
    }

    public void getPlaceByWord(String word) {
        dispose();
        searchAddress = placeByWord.provide(word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setAddressList, this::showEmptyResMessage);
    }

    private void dispose(){
        if (searchAddress != null&&!searchAddress.isDisposed())
            searchAddress.dispose();
    }

    @Override
    public void detachView(SelectAddressView view) {
        dispose();
        super.detachView(view);
    }
}
