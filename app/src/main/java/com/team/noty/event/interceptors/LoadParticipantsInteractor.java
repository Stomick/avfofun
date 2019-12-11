package com.team.noty.event.interceptors;

import android.util.Log;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActAdmin;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Participant;
import com.team.noty.event.models.User;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsAdminUserList;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.utils.StringUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class LoadParticipantsInteractor implements Interactor<Observable<? extends User>, ActAdmin> {

    private ReaderRepos<Act, ActId> actReader;
    private ReaderRepos<User, Integer> userReader;
    private MainApi api;

    @Inject
    public LoadParticipantsInteractor(ReaderRepos<Act, ActId> actReader, ReaderRepos<User, Integer> userReader, MainApi api) {
        this.actReader = actReader;
        this.userReader = userReader;
        this.api = api;
    }

    @Override
    public Observable<? extends User> call(ActAdmin actAdmin) {
        if (actAdmin.creator)
            return loadAdminList(actAdmin);
        return loadList(actAdmin);
    }

    private Observable<Participant> loadAdminList(ActId actId) {
        return api.getAdminUsers(actId.type(), actId.id())
                .flatMapObservable(rsAdminUserList -> Observable.fromIterable(rsAdminUserList.userlist))
                .map(this::convert).doOnError(throwable -> Log.e("my",throwable.getMessage()));
    }

    private Observable<User> loadList(ActId actId) {
        return actReader.request(actId)
                .map(act -> act.users)
                .flatMapObservable(Observable::fromIterable)
                .map(StringUtils::toIntOr0)
                .flatMap(this::loadUser);
    }

    private Observable<User> loadUser(Integer id) {
        return userReader.request(id)
                .toObservable()
                .onErrorResumeNext((ObservableSource<? extends User>) throwable -> Observable.empty());
    }

    private Participant convert(RsAdminUserList.RsAdminUser user) {
        Participant participant = new Participant();
        participant.status = Participant.fromString(user.status);
        participant.city = user.userCity;
        participant.date = user.birthday;
        participant.firstname = user.username;
        participant.id = StringUtils.toIntOr0(user.usId);
        participant.phone = user.userPhone;
        participant.rating = user.userRating;
        participant.level = user.typeRank;
        participant.imageUrl = user.userAvatar;
        return participant;
    }
}
