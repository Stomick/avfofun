package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.models.Social;
import evfor.fun.skvader.network.api.MainApi;

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
