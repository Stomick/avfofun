package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.EditProfileView;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.utils.AccountPreferenceManager;

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
