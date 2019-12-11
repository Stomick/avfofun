package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActAdmin;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.DistributionParticipant;
import evfor.fun.skvader.models.Participant;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.PartsView;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.ui.models.UiUser;
import evfor.fun.skvader.utils.ListUtils;
import evfor.fun.skvader.utils.Pair;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class PartsPresenter extends BasePresenter<PartsView> {

    @Inject
    Interactor<Observable<? extends User>, ActAdmin> loadParticipants;
    @Inject
    Interactor<Single<ActId>, String> jointActInterceptor;
    @Inject
    ReaderRepos<Act, ActId> actReader;
    @Inject
    Interactor<Completable, DistributionParticipant> adminParticipant;

    private ActId actId;
    public List<UiUser> request, joing, dissmised;
    private Disposable userLoading;
    private CallBack1<String> dissmising = CallBack1.empty();

    public enum TYPE {
        JOINING, REQUEST, DISMISSED
    }

    public PartsPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void loadAct(ActId actId, boolean creator) {
        clearLists();
        setActId(actId);
        loadParticipants.call(new ActAdmin(actId.id, actId.isEvent, creator))
                .flatMapSingle(this::checkAbilityToWrite)
                .doOnNext(this::addUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::addUser,throwable ->  Log.e("my",throwable.getMessage()), getViewState()::onComplete);
    }

    private void setActId(ActId actId) {
        this.actId = actId;
    }

    private void clearLists() {
        request = new ArrayList<>();
        joing = new ArrayList<>();
        dissmised = new ArrayList<>();
    }

    private Single<UiUser> checkAbilityToWrite(User user) {
        return jointActInterceptor.call(String.valueOf(user.id))
                .zipWith(Single.just(user), (actId1, user1) -> new UiUser(user1, true))
                .onErrorResumeNext(Single.just(new UiUser(user, false)));
    }

    private void addUser(UiUser uiUser) {
        if (uiUser.user instanceof Participant)
            switch (((Participant) uiUser.user).status) {
                case REQUEST:
                    request.add(uiUser);
                    break;
                case DISMISSED:
                    dissmised.add(uiUser);
                    break;
                case CONFIRMED:
                    joing.add(uiUser);
                    break;
            }
        else joing.add(uiUser);
    }

    public void getUsers(TYPE type) {
        if (userLoading != null && !userLoading.isDisposed())
            userLoading.dispose();
        dissmising = (type == TYPE.REQUEST) ? this::deleteUser : this::dialogDeleteUser;
        userLoading = Observable.fromIterable(type == TYPE.REQUEST ? request : type == TYPE.JOINING ? joing : dissmised)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::addUser, Throwable::printStackTrace, getViewState()::onComplete);
    }

    public void removeUser(String id) {
  //      dissmising.call(id);
        dialogDeleteUser(id);
    }

    private void deleteUser(String id) {
        if (request.contains(id))
            adminParticipant.call(DistributionParticipant.dismissed.setId(actId, id))
                    .andThen(Single.just(id))
                    .flatMap(this::moveUserToJoinedFromRequest)
                    .doOnSuccess(request::remove)
                    .subscribe(getViewState()::removed);
    }

    private void dialogDeleteUser(String id) {
        actReader.request(actId)
                .map(rsEventComm -> rsEventComm.title)
                .zipWith(Single.just(id).flatMap(s -> Single.just(ListUtils.search(joing, s))),
                        (BiFunction<String, UiUser, Pair>) Pair::new)
                .subscribe(this::subscribe, this::onError);
    }

    private void subscribe(Pair<String, UiUser> pair) {
        getViewState().showDeleteDialog(pair.getRight().user.firstname,
                pair.getLeft(),
                pair.getRight().user.id());
    }

    public void acceptUser(String id) {
        boolean b = false;
        for (UiUser uiUser:request)
        {
            if(uiUser.user.id().equals(id))
                b = true;
        }
        if (b)
            adminParticipant.call(DistributionParticipant.confirmed.setId(actId, id))
                    .andThen(Single.just(id))
                    .flatMap(this::moveUserToJoinedFromRequest)
                    .doOnSuccess(request::remove)
                    .doOnSuccess(joing::add)
                    .subscribe(getViewState()::removed,throwable -> Log.e("my",throwable.getMessage()));
    }

    private Single<UiUser> moveUserToJoinedFromRequest(String id) {
        return Single.just(id).flatMap(s -> Single.just(ListUtils.search(request, s)));
    }

    public ActId getActId() {
        return actId;
    }
}
