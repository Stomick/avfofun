package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.models.DistributionParticipant;
import evfor.fun.skvader.network.api.MainApi;

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
