package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.NewPassword;
import evfor.fun.skvader.mvp.views.CompletableView;

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
