package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.mvp.views.CompletableView;
import evfor.fun.skvader.network.api.MainApi;

import javax.inject.Inject;

import io.reactivex.Completable;

@InjectViewState
@SuppressLint("CheckResult")
public class ComplaintPresenter extends BasePresenter<CompletableView> {

    @Inject
    MainApi api;

    public ComplaintPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void sendOnUser(String description, String id, Integer cause_id) {
        api(api.sendComplaint("user", id, AuthData.getID(), 16, cause_id, description))
                .toCompletable()
                .subscribe(this::complete, this::onError);
    }

    public void sendOnEvent(String description, String id, Integer cause_id) {
        api(api.sendComplaint(type(true), id, AuthData.getID(), 16, cause_id, description))
                .toCompletable()
                .subscribe(this::complete, this::onError);
    }

    public void sendOnCommunity(String description, String id, Integer cause_id) {
        api(api.sendComplaint(type(false), id, AuthData.getID(), 16, cause_id, description))
                .toCompletable()
                .subscribe(this::complete, this::onError);
    }

    public void deleteEvent(String description, String id, Integer cause) {
        api(api.deleteEvent(type(true), id, cause, description))
                .toCompletable()
                .subscribe(this::complete, this::onError);
    }

    public void deleteCommunity(String description, String id, Integer cause) {
        api(api.deleteEvent(type(false), id, cause, description))
                .toCompletable()
                .subscribe(this::complete, this::onError);
    }

    public void deletePartipicant(String id, ActId actId, Integer cause_id, String cause_message) {
        api(api.sendComplaint("user", id, AuthData.getID(), !actId.isEvent ? 1 : 2, cause_id, cause_message))
                .toCompletable().andThen(deleteUser(actId, id))
                .subscribe(this::complete, this::onError);
    }

    private Completable deleteUser(ActId actId, String id) {
        return api(api.acceptRemoveUser(type(actId.isEvent), actId.id, id, "dismissed"))
                .toCompletable();
    }

    private void complete() {
        getViewState().onComplete();
    }

    private String type(boolean isEvent){
        return isEvent ? "evfor.fun.skvader" : "community";
    }
}
