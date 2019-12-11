package com.team.noty.event.interceptors;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.FullAct;
import com.team.noty.event.models.User;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class FullActInterceptor implements Interactor<Single<FullAct>, ActId> {

    private ReaderRepos<Act, ActId> actReader;
    private ReaderRepos<User, Integer> userReader;

    @Inject
    FullActInterceptor(ReaderRepos<Act, ActId> actReader,
                              ReaderRepos<User, Integer> userReader) {
        this.actReader = actReader;
        this.userReader = userReader;
    }

    @Override
    public Single<FullAct> call(ActId actId) {
        return actReader.request(actId)
                .flatMap(act ->
                        Single.zip(Single.just(act),
                                userReader.request(StringUtils.toIntOr0(act.user_id)),
                                userList(act.users),
                                FullAct::new));
    }

    private Single<List<User>> userList(List<String> ids) {
        return Observable.fromIterable(ids)
                .map(StringUtils::toIntOr0)
                .flatMapSingle(userReader::request)
                .toList();
    }

}
