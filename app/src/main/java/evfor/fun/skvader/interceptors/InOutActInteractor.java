package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.models.ActUpdate;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsBase;
import evfor.fun.skvader.repository.user.EditUserProfile;
import evfor.fun.skvader.repository.user.UserReader;
import evfor.fun.skvader.utils.StringUtils;

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
