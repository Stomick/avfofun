package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.SocView;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.Updater;
import com.team.noty.event.utils.Pair;
import com.team.noty.event.utils.callbacks.CallBack1;
import com.team.noty.event.utils.social.Social;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class SocPresenter extends BasePresenter<SocView> {

    @Inject
    Interactor<Completable, com.team.noty.event.models.Social> sendSocial;
    @Inject
    Updater<User, Integer> userUpdater;
    @Inject
    ReaderRepos<User, Integer> userReader;

    public SocPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void setSoc(String fb, String tw, String vk, String inst) {
        Completable.mergeArrayDelayError(
                setSocial(Social.FACEBOOK, fb),
                setSocial(Social.TW, tw),
                setSocial(Social.VK, vk),
                setSocial(Social.INST, inst))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete, this::onError);
    }

    private Completable setSocial(Social s, String id) {
        if (id == null)
            return Completable.complete();
        return sendSocial.call(new com.team.noty.event.models.Social(s.getShortField(), id))
                .andThen(Single.just(new Pair<>(s, id)).flatMapCompletable(this::updateProfile));
    }

    private Completable updateProfile(Pair<Social, String> soc) {
        return userUpdater.update(AuthData.getIDInt(), new UserUpdate(soc));
    }

    public void load() {
        userReader.request(AuthData.getIDInt())
                .map(User::createList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::loadList, this::onError);
    }

    private class UserUpdate implements CallBack1<User> {
        Pair<Social, String> soc;

        UserUpdate(Pair<Social, String> soc) {
            this.soc = soc;
        }

        @Override
        public void call(User user) {
            switch (soc.getLeft()) {
                case FACEBOOK:
                    user.fb_id = soc.getRight();
                    break;
                case VK:
                    user.vk_id = soc.getRight();
                    break;
                case INST:
                    user.inst_id = soc.getRight();
                    break;
                case TW:
                    user.tw_id = soc.getRight();
                    break;
            }
        }
    }
}
