package com.team.noty.event.interceptors;

import com.team.noty.event.app.AuthData;
import com.team.noty.event.models.ActUpdate;
import com.team.noty.event.models.User;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.repository.user.EditUserProfile;
import com.team.noty.event.repository.user.UserReader;
import com.team.noty.event.utils.StringUtils;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class InOutActInteractor implements Interactor<Completable, ActUpdate> {

    private MainApi api;
    private EditUserProfile editUserProfile;
    private UserReader userReader;

    @Inject
    public InOutActInteractor(MainApi api, EditUserProfile editUserProfile, UserReader userReader) {
        this.api = api;
        this.editUserProfile = editUserProfile;
        this.userReader = userReader;
    }

    @Override
    public Completable call(ActUpdate actUpdate) {
        Observable<RsBase> request = actUpdate.actId.isEvent
                ? api.enterEvent(actUpdate.getDoName(), actUpdate.actId.id())
                : api.enterComm(actUpdate.getDoName(), actUpdate.actId.id());
        return request
                .subscribeOn(Schedulers.io())
                .singleOrError()
                .toCompletable()
                .andThen(update(actUpdate));
    }

    public Completable update(ActUpdate actUpdate) {
        return userReader
                .request(StringUtils.toIntOr0(AuthData.getID()))
                .zipWith(Single.just(actUpdate), this::addAct)
                .flatMapCompletable(editUserProfile::edit)
                .onErrorComplete();
    }

    private User addAct(User u, ActUpdate a) {
        switch (a) {
            case ENTER:
                u.addAct(a.actId);
                break;
            case LEAVE:
                u.removeAct(a.actId);
                break;
        }
        return u;
    }
}
