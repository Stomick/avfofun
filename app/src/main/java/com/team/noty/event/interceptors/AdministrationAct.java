package com.team.noty.event.interceptors;

import com.team.noty.event.models.DistributionParticipant;
import com.team.noty.event.network.api.MainApi;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AdministrationAct implements Interactor<Completable, DistributionParticipant> {

    private MainApi api;

    @Inject
    AdministrationAct(MainApi api) {
        this.api = api;
    }

    @Override
    public Completable call(DistributionParticipant dPart) {
        return api.acceptRemoveUser(dPart.getActId().type(), dPart.getActId().id(), dPart.id(), dPart.name())
                .toCompletable();
    }
}
