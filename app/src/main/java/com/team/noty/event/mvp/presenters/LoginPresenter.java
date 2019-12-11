package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.support.annotation.MainThread;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.App;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.mvp.views.LoginView;
import com.team.noty.event.network.api.LoginApi;
import com.team.noty.event.network.models.request.RqLogin;
import com.team.noty.event.network.models.response.RsLogin;
import com.team.noty.event.ui.activities.RegistrationActivity;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.social.SocialProfileManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    LoginApi api;

    @Inject
    AccountPreferenceManager accountPreferenceManager;

    public LoginPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void login(String email, String pass) {
        onSubscribe(api.login(new RqLogin(email, pass)).observeOn(AndroidSchedulers.mainThread()));
    }

    public void login(String fbId) {
        String s = SocialProfileManager.social().toString();
        onSubscribe(api(api.login(fbId, SocialProfileManager.social().toString())));
    }

    @SuppressLint("CheckResult")
    private void onSubscribe(Observable<RsLogin> loginObservable) {
        loginObservable.subscribe(this::loginResponse, this::onError);
    }

    private void loginResponse(RsLogin login){

        if (login != null && !login.error) {
            AuthData.token = login.answer;
            accountPreferenceManager.saveFromData();
            getViewState().onComplete();
        } else {
            if (login.message != null)
                getViewState().errorLogin();
            else
                getViewState().gotoRegister();
        }
    }

    @Override
    void onError(Throwable e)
    {
        Log.d("ERROR","1");
    }
}
