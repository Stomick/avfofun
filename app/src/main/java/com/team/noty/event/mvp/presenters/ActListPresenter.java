package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.EventCommListView;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;

@InjectViewState
@SuppressLint("CheckResult")
public class ActListPresenter extends BasePresenter<EventCommListView> {

    @Inject
    ReaderRepos<List<Act>, User> actListReader;
    @Inject
    ReaderRepos<User, Integer> userReader;

    private User user;
    private boolean isEvent;
    private int creatorCount = 0, partCount = 0;
    private int oldcreatorCount = 0, oldpartCount = 0;

    public ActListPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void loadUser(String id, boolean isEvent) {
        this.isEvent = isEvent;
        userReader.request(StringUtils.toIntOr0(id))
                .doOnSuccess(this::setUser)
                .toCompletable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete);
    }

    private void setUser(User user) {
        this.user = user;
    }

    public void loadActList(boolean isCreator){
        refreshCounts();

        FilterPredicate isCreatorFilter = new FilterPredicate(isCreator) {
            @Override
            public boolean test(Act act) {
                return value == user.id().equals(act.user_id);
            }
        };

        actListReader.request(user)
                .flatMapObservable(Observable::fromIterable)
                //.doOnNext(this::countCreator)
                .filter(act -> isEvent == act.isEvent)
                .filter(isCreatorFilter)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showList);
    }

    private void countCreator(Act act) {
        if (user.id().equals(act.user_id))
            creatorCount++;
        else
            partCount++;
    }

    private void refreshCounts() {
        oldcreatorCount=creatorCount==0?oldcreatorCount:creatorCount;
        oldpartCount = partCount==0?oldpartCount:partCount;
        creatorCount = 0;
        partCount = 0;
    }

    private void showList(List<Act> acts) {
        for (Act act: acts)
            countCreator(act);
        getViewState().loadList(acts);
        getViewState().setCounts(partCount==0?oldpartCount:partCount, creatorCount==0?oldcreatorCount:creatorCount);
    }

    private abstract class FilterPredicate implements Predicate<Act> {
        protected final boolean value;
        FilterPredicate(boolean value) {
            this.value = value;
        }
    }
}
