package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.FeedBack;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.GiveFeedbackView;
import evfor.fun.skvader.network.models.response.RsBase;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.utils.Pair;
import evfor.fun.skvader.utils.StringUtils;

import java.util.Calendar;

import javax.inject.Inject;

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
