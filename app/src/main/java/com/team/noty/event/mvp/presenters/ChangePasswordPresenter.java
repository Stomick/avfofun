package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.NewPassword;
import com.team.noty.event.mvp.views.CompletableView;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.request.RqChangePassword;
import com.team.noty.event.utils.Converter;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ChangePasswordPresenter extends BasePresenter<CompletableView> {

    @Inject
    Interactor<Completable, NewPassword> changePass;

    public ChangePasswordPresenter() {
        Injector.get().getMain().inject(this);
    }

    @SuppressLint("CheckResult")
    public void changePassword(String newPassword, String oldPassword) {
        changePass.call(new NewPassword(newPassword, oldPassword))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete);
    }
}
