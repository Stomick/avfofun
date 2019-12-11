package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.mvp.views.LoginView;
import evfor.fun.skvader.network.api.LoginApi;
import evfor.fun.skvader.network.models.request.RqLogin;
import evfor.fun.skvader.network.models.response.RsLogin;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import evfor.fun.skvader.utils.social.SocialProfileManager;

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
