package com.team.noty.event.interceptors;

import com.team.noty.event.models.Social;
import com.team.noty.event.network.api.MainApi;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SendSocInteractor implements Interactor<Completable, Social> {

    private MainApi api;

    @Inject
    public SendSocInteractor(MainApi api) {
        this.api = api;
    }

    @Override
    public Completable call(Social social) {
        return api.setSoc(social.name, social.id)
                .toCompletable();
    }
}
