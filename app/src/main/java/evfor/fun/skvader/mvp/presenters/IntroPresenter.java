package evfor.fun.skvader.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.mvp.views.IntroView;
import evfor.fun.skvader.utils.AccountPreferenceManager;

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
