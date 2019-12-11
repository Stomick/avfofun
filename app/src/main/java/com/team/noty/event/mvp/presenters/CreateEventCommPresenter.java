package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import com.pacoworks.rxpaper2.RxPaperBook;
import com.team.noty.event.app.Injector;
import com.team.noty.event.dagger2.qualifiers.PaperBook;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Category;
import com.team.noty.event.mvp.views.CreateEventCommView;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.WriterRepos;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.utils.Converter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class CreateEventCommPresenter extends BasePresenter<CreateEventCommView> {

    @Inject
    DataProvide<List<Category>> categories;
    @Inject
    ReaderRepos<Act, ActId> actReader;
    @Inject
    WriterRepos<Act> actWriter;

    public CreateEventCommPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void getCategory() {
        categories.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setCategories, this::onError);
    }

    public void getComm(String id) {
        actReader.request(new ActId(id, false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventComm -> getViewState().loadEventComm(eventComm), this::onError);
    }

    public void getEvent(String id) {
        actReader.request(new ActId(id, true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventComm -> getViewState().loadEventComm(eventComm), this::onError);
    }

    public void create(Act act) {
        actWriter.edit(act)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete,throwable -> Log.e("My",throwable.getMessage()));
    }
}
