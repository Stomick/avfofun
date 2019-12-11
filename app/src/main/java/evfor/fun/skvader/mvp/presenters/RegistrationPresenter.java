package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.mvp.views.RegistrationView;
import evfor.fun.skvader.network.api.RegistrationApi;
import evfor.fun.skvader.network.models.request.RqRegistration;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import evfor.fun.skvader.utils.DateFormatter;
import evfor.fun.skvader.utils.social.SocialProfileManager;
import com.vk.sdk.VKAccessToken;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

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
