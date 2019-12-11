package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.EditProfileView;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.WriterRepos;
import com.team.noty.event.utils.AccountPreferenceManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class EditProfilePresenter extends BasePresenter<EditProfileView> {

    @Inject
    ReaderRepos<User, Integer> userReader;
    @Inject
    WriterRepos<User> userWriter;

    @Inject
    AccountPreferenceManager preferenceManager;

    private User original;

    public EditProfilePresenter() {
        Injector.get().getMain().inject(this);
    }

    public void loadProfile() {
        userReader.request(AuthData.getIDInt())
                .doOnSuccess(this::setOriginal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::loadProfile);
    }

    private void setOriginal(User original) {
        this.original = original;
    }

    public void edit(User user) {
        original.update(user);
        userWriter.edit(original)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete,throwable -> Log.e("my",throwable.getMessage()));
    }

    public void exitAccount(){
        preferenceManager.clear();
        Injector.get().closeMain();
        getViewState().exit();
    }

    @Override
    void onError(Throwable throwable) {
        loadProfile();
        super.onError(throwable);
    }
}
