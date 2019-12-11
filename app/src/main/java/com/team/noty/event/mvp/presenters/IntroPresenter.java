package com.team.noty.event.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.mvp.views.IntroView;
import com.team.noty.event.utils.AccountPreferenceManager;

import javax.inject.Inject;

@InjectViewState
public class IntroPresenter extends BasePresenter<IntroView> {
    @Inject
    AccountPreferenceManager preferenceManager;

    public IntroPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void checkAuth() {
        if (preferenceManager.checkValue(AccountPreferenceManager.TOKEN))
            getViewState().openMain();
        else getViewState().needAuth();
    }

    public void continueWithFB(String fbId){
        AuthData.fb_id = fbId;
        getViewState().openReg();
    }

    public void continueWithVK(){
        AuthData.fb_id = null;
        getViewState().openReg();
    }
}
