package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.R;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Comment;
import com.team.noty.event.models.UpdatePhoto;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.tabs.CabinetTabView;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.WriterRepos;
import com.team.noty.event.ui.utils.LinkUtils;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class CabinetPresenter extends BaseEventBusPresenter<CabinetTabView> {

    @Inject
    ReaderRepos<User, Integer> userReader;
    @Inject
    Interactor<Single<ActId>, String> jointAct;
    @Inject
    WriterRepos<User> userWriter;
    @Inject
    Interactor<Completable, UpdatePhoto> updatePhoto;
    @Inject
    ReaderRepos<Act, ActId> actLoader;

    public User current;

    public CabinetPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void readUser() {
        userReader.request(AuthData.getIDInt())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUser);
    }

    public void readUser(String id) {
        userReader.request(StringUtils.toIntOr0(id))
                .doOnSuccess(this::setMessageButton)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUser);
    }

    private void setMessageButton(User user) {
        jointAct.call(user.id())
                .toCompletable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showSendButton, Throwable::printStackTrace);
    }

    public void setAbout(String aboutText) {
        current.info = aboutText;
        userWriter.edit(current)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showInfo(R.string.accept));
    }

    public void openDialog() {
        getViewState().openDialog(String.valueOf(current.id));
    }

    public void editPhoto(String path) {
        updatePhoto.call(new UpdatePhoto(path))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::editPhotoResult, this::onError);
    }

    public void deletePhoto() {
        editPhoto(null);
    }

    private void editPhotoResult() {
        ImageLoader.clearCashUrl(current.imageUrl);
        //getViewState().changePhoto(current.imageUrl);
        readUser();
        getViewState().showInfo(R.string.complete);
    }

    public void openPhoto() {
        userReader.request(current.id)
                .map(user -> user.imageUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> getViewState().openPhoto(s));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUser(User user) {
        if (user.id > 0) {

            current = user;
            subIoObsMain(Single.just(user))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(u -> getViewState().loadSoc(u.createList()))
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(user1 -> getViewState().showUser(user1));
            if (user.events != null)
                loadEvents(user.events);
            if (user.communities != null)
                loadCommunities(user.communities);
            if (user.reviews != null)
                loadReviews(user.reviews);
        }
    }

    private void loadReviews(List<Comment> reviews) {
        Observable.fromIterable(reviews)
                .flatMapSingle(comment -> Single.zip(
                                userReader.request(StringUtils.toIntOr0(comment.getUserId())),
                                Single.just(comment),
                                (user, comment1) -> {
                                    comment1.setUsername(user.firstname);
                                    comment1.setUserAvatar(user.imageUrl);
                                    return comment1;
                                }))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showComments);
    }

    private void loadCommunities(List<String> communitiesIds) {
        Observable.fromIterable(communitiesIds)
                .subscribeOn(Schedulers.io())
                .map(s -> new ActId(s, false))
                .flatMapSingle(actLoader::request)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showCommunities);
    }

    private void loadEvents(List<String> eventsIds) {
        Observable.fromIterable(eventsIds)
                .map(s -> new ActId(s, true))
                .flatMapSingle(actLoader::request)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showEvents,throwable -> Log.e("my",throwable.getMessage()));
    }

    public String createLink() {
        return LinkUtils.createUserLink(String.valueOf(current.id));
    }
}
