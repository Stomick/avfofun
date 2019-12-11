package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.FavoriteView;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.utils.Pair;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
@SuppressLint("CheckResult")
public class FavoritePresenter extends BasePresenter<FavoriteView> {

    private Disposable disposable;

    @Inject
    ReaderRepos<Act, ActId> readerRepos;
    @Inject
    ReaderRepos<User, Integer> userReader;
    private int filter = 0; // 0 - all, 1 - creator, 2 - part

    public FavoritePresenter() {
        Injector.get().getMain().inject(this);
    }

    public void getEvents(int filter) {
        this.filter = filter;
        dispose();
        disposable = load(events());
    }

    public void getCommunities(int filter) {
        this.filter = filter;
        dispose();
        disposable = load(comm());
    }

    private Single<Pair<Long, Long>> count() {
        return subIoObsMain(count(events())
                .zipWith(count(comm()), Pair::new));
    }

    private Observable<Act> events() {
        return userReader.request(AuthData.getIDInt())
                .flatMapObservable(user -> Observable.fromIterable(user.events))
                .map(s -> new ActId(s, true))
                .flatMapSingle(readerRepos::request);
    }

    private Observable<Act> comm() {
        return userReader.request(AuthData.getIDInt())
                .flatMapObservable(user -> Observable.fromIterable(user.communities))
                .map(s -> new ActId(s, false))
                .flatMapSingle(readerRepos::request);
    }

    private Single<Long> count(Observable<Act> observable) {
        return observable.filter(this::filter)
                .count();
    }

    private Disposable load(Observable<Act> observable) {
        return observable
                .filter(this::filter)
                .toList()
                .doOnSuccess(Collections::sort)
                .zipWith(count(), ResultResponse::new)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showContent, this::onError);
    }

    private void showContent(ResultResponse resultResponse) {
        getViewState().showList(resultResponse.events);
        getViewState().setCounts(resultResponse.eventCommCount.getLeft().intValue(), resultResponse.eventCommCount.getRight().intValue());
    }

    private boolean filter(Act e) {
        if (filter == 0)
            return true;
        if (filter == 1)
            return AuthData.equalId(e.user_id);
        return filter == 2 && AuthData.notEqualId(e.user_id);
    }

    private void dispose() {
        if (disposable != null)
            disposable.dispose();
    }

    private class ResultResponse {
        Pair<Long, Long> eventCommCount;
        List<Act> events;

        ResultResponse(List<Act> events, Pair<Long, Long> eventCommCount) {
            this.eventCommCount = eventCommCount;
            this.events = events;
        }
    }
}
