package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.R;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.mvp.views.RegistrationView;
import com.team.noty.event.network.api.RegistrationApi;
import com.team.noty.event.network.models.request.RqRegistration;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.social.Social;
import com.team.noty.event.utils.social.SocialProfileManager;
import com.vk.sdk.VKAccessToken;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.HttpException;

@InjectViewState
@SuppressLint("CheckResult")
public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    @Inject
    RegistrationApi api;
    @Inject
    AccountPreferenceManager accountPreferenceManager;
    private RqRegistration registration;

    public RegistrationPresenter() {
        Injector.get().getApp().inject(this);
        registration = new RqRegistration();
    }

    public void setPhone(String phone) {
        registration.phone = phone;
    }

    public void setGenderFullNameDate(int gender, String name, String surname, String date, String city) {
        registration.gender = Integer.toString(gender).equals("1")?"true":"false";
        registration.username = name;
        registration.surename = surname;
        registration.birthday = DateFormatter.reformating(date);
        registration.city = city;
    }

    public void setEmailPassword(String email, String pass) {
        registration.email = email;
        registration.password = pass;
    }

    public void sendRegistration() {
        api(api.registration(registration))
                .doOnNext(rsRegistration -> sendSocId(rsRegistration.token))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        rsRegistration -> {
                            AuthData.token = rsRegistration.token;
                            accountPreferenceManager.saveFromData();
                            getViewState().onComplete();
                        }, throwable -> getViewState().error());
    }

    private void sendSocId(String userToken) {
        if (AuthData.fb_id != null) {
            api(api.regWithSocKey(userToken, AuthData.fb_id, SocialProfileManager.social().toString()))
                    .subscribe();
        } else if (VKAccessToken.currentToken() != null && VKAccessToken.currentToken().userId != null) {
            api(api.regWithSocKey(userToken, VKAccessToken.currentToken().userId, SocialProfileManager.social().toString()))
                    .subscribe();
        }
        AuthData.fb_id = null;
    }
}
