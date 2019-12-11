package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.FeedBack;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.GiveFeedbackView;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.utils.Pair;
import com.team.noty.event.utils.StringUtils;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class GiveFeedbackPresenter extends BasePresenter<GiveFeedbackView> {

    @Inject
    ReaderRepos<Act, ActId> actReader;
    @Inject
    ReaderRepos<User, Integer> userReader;
    @Inject
    Interactor<Single<RsBase>, FeedBack> feedBackSender;
    @Inject
    Interactor<Integer, Calendar> ageCalculating;
    public String event_id;
    public GiveFeedbackPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void load(String id) {
        actReader.request(new ActId(id, true))
                .doOnSuccess(act -> loadUser(act.user_id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(act -> getViewState().loadEvent(act.imageUrl, act.title), this::onError);
    }

    private void loadUser(String userId) {
        userReader.request(StringUtils.toIntOr0(userId))
                .map(user -> new Pair<>(user, ageCalculating.call(user.getBirthday())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> getViewState().loadUser(pair.getLeft(), pair.getRight()));
    }

    public void send(FeedBack feedBack) {
        feedBackSender.call(feedBack)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t-> {
                            if(t.error) {
                                event_id = feedBack.eventId;
                                getViewState().onComplete();
                            }
                            else
                                getViewState().showError(t.message);
                        },throwable -> getViewState().showError("Вы не можете прокоментировать это событие,\n возможно вы это уже сделали?"));
    }
}
