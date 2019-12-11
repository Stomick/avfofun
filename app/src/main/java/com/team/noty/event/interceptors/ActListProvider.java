package com.team.noty.event.interceptors;

import android.support.annotation.NonNull;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.User;
import com.team.noty.event.repository.ReaderRepos;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ActListProvider implements ReaderRepos<List<Act>, User> {

    private ReaderRepos<Act, ActId> actReader;

    @Inject
    ActListProvider(ReaderRepos<Act, ActId> actReader) {
        this.actReader = actReader;
    }

    @NonNull
    @Override
    public Single<List<Act>> request(@NonNull User user) {
        return Observable.merge(getEvents(user), getComm(user))
                .flatMapSingle(actReader::request)
                .toList();
    }

    private Observable<ActId> getEvents(User user){
        return Single.just(user)
                .flatMapObservable(u -> Observable.fromIterable(u.events))
                .map(s -> new ActId(s, true));
    }

    private Observable<ActId> getComm(User user){
        return Single.just(user)
                .flatMapObservable(u -> Observable.fromIterable(u.communities))
                .map(s -> new ActId(s, false));
    }
}
