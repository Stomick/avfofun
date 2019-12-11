package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.pacoworks.rxpaper2.RxPaperBook;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.dagger2.qualifiers.PaperBook;
import evfor.fun.skvader.exceptions.AgeLimitException;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.ActStatus;
import evfor.fun.skvader.models.ActUpdate;
import evfor.fun.skvader.models.AgeRestriction;
import evfor.fun.skvader.models.EnterStatus;
import evfor.fun.skvader.models.FullAct;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.EventView;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.ui.models.UiAct;
import evfor.fun.skvader.ui.models.UiUser;
import evfor.fun.skvader.ui.utils.LinkUtils;
import evfor.fun.skvader.utils.DateFormatter;
import evfor.fun.skvader.utils.callbacks.CallBack;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class EventPresenter extends BasePresenter<EventView> {

    @Inject
    MainApi api;
    @Inject
    Interactor<Single<FullAct>, ActId> fullActInterceptor;
    @Inject
    Interactor<Single<ActId>, String> jointActInterceptor;
    @Inject
    Interactor<Completable, ActUpdate> inOutAct;
    @Inject
    Interactor<String, Act> shapinDateInteractor;
    @Inject
    Interactor<Completable, AgeRestriction> checkAgeRestriction;
    @Inject
    ReaderRepos<User, Integer> userReader;
    @Inject
    ContentApi contentApi;

    @PaperBook(PaperBook.TYPE.USERS)
    @Inject
    RxPaperBook book;

    private CallBack callActionButton = CallBack.empty;
    private Act act;
    private User user_;
    private Act act_;

    public EventPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void getAct(ActId actId) {
        if (actId == null)
            actId = act;
        if (actId != null)
            fullActInterceptor.call(actId)

                    .doOnSuccess(fullAct -> {
                                act = fullAct.act;
                                if (!imCreator())
                                    getMe();
                            }
                    ).doOnError(thr -> Log.e("my", thr.getMessage()))
                    .doOnSuccess(fullAct -> checkAge(fullAct.act).doOnError(thr -> Log.e("my", thr.getMessage())))
                    .observeOn(AndroidSchedulers.mainThread()).doOnError(thr -> Log.e("my", thr.getMessage()))
                    .subscribeOn(AndroidSchedulers.mainThread()).doOnError(thr -> Log.e("my", thr.getMessage()))
                    .subscribe(this::response, this::exceptionHandler);

    }

    private Single<Act> checkAge(Act act) {
        return checkAgeRestriction.call(new AgeRestriction(act.age_limit))
                .toSingleDefault(act);
    }

    private void getMe() {
        Single<User> userSingle = userReader.request(AuthData.getIDInt())
                .doOnSuccess(mode -> user_ = mode);
        userSingle.subscribeOn(AndroidSchedulers.mainThread()).subscribe(model -> {
                    int a = DateFormatter.getAge(model.date);
                    if (act != null)
                        if (a < act.age_limit)
                            getViewState().ageException();
                },
                throwable -> Log.e("my", throwable.getMessage()));
    }

    private void response(FullAct fullAct) {
        showEvent(fullAct.act);
        setGoButtonStatus(fullAct.act);
        showCreator(fullAct.user);
        showParticipants(fullAct.users);
    }

    private void showCreator(User user) {

        if (AuthData.notEqualId(user.id()))
            jointActInterceptor.call(String.valueOf(user.id))
                    .zipWith(Single.just(user), (actId, user1) -> new UiUser(user1, true))
                    .onErrorResumeNext(Single.just(new UiUser(user, false)))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getViewState()::showCreator);
    }

    private void showParticipants(List<User> users) {
        if (!users.isEmpty())
            getViewState().showParts(users);
    }

    private void showEvent(Act act) {
        Single.just(act)
                .map(UiAct::new)
                .doOnSuccess(uiAct -> uiAct.formatedDate = shapinDateInteractor.call(uiAct.act))
                .subscribe(
                        getViewState()::showAct
                );
    }

    private void setGoButtonStatus(Act act) {
        if (act.enterStatus != null && AuthData.notEqualId(act.user_id)) {
            if (act.getStatus() == ActStatus.BLOCKED) {
                getViewState().blocked();
                return;
            }
            switch (act.getStatus()) {
                case BLOCKED:
                    return;
                case PASSED:
                    getViewState().passedGoBtn(act.enterStatus == EnterStatus.CONFIRMED);
                    callActionButton = this::giveFeedback;
                    return;
                case NOPLACES:
                    if (act.enterStatus != EnterStatus.CONFIRMED) {
                        getViewState().noPlacesGoBtn();
                        callActionButton = CallBack.empty;
                        return;
                    }
            }
            switch (act.enterStatus) {
                case FALSE:
                    callActionButton = this::in;
                    getViewState().falseGoBtn();
                    return;
                case REQUEST:
                    getViewState().requestGoBtn();
                    callActionButton = this::out;
                    return;
                case CONFIRMED:
                    getViewState().confirmGoBtn();
                    callActionButton = this::out;
                    return;
                case DISMISSED:
                    getViewState().dismissGoBtn();
                    callActionButton = CallBack.empty;
            }
        }
    }

    private void giveFeedback() {
        getViewState().openFeedBack(act.id());
    }

    private void exceptionHandler(Throwable throwable) {
        if (throwable instanceof AgeLimitException)
            getViewState().ageException();
        else
            getViewState().close(throwable.getMessage());
    }

    public void edit() {
        getViewState().openEdit(act);
    }

    public void goCall() {
        callActionButton.call();
    }

    public void deleteAct() {
        if (act.user_id.equals(AuthData.getID()))
            getViewState().openDelete(act);
    }

    public void openComplaint() {
        getViewState().openComplaint(act);
    }

    public void copyLink() {
        getViewState().copyLink(LinkUtils.createEventCommLink(act));
    }

    public void share() {
        getViewState().shareLink(LinkUtils.createEventCommLink(act));
    }

    private void in() {
        inOutAct.call(ActUpdate.ENTER.setAct(act))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getAct(act));
    }

    private void out() {
        inOutAct.call(ActUpdate.LEAVE.setAct(act))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getAct(act));
    }

    public ActId getAct() {
        return act;
    }

    public boolean imCreator() {
        return AuthData.equalId(act.user_id);
    }

    public void openChat() {
        getViewState().openChat(act);
    }
}
