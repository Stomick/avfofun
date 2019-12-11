package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.RegPrewView;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.utils.PermissionController;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class RegPrewPresenter extends BasePresenter<RegPrewView> {

    @Inject
    WriterRepos<User> userWriter;

    @Inject
    ReaderRepos<User, Integer> userReader;

    @Inject
    DataProvide<List<Category>> categoryProvider;

    @Inject
    PermissionController permissionController;

    public RegPrewPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void checkPhotoPermission() {
        subIoObsMain(permissionController.storage())
                .subscribe(() -> getViewState().openPhoto(), this::onError);
    }

    public void getCategories() {
        categoryProvider.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showInterests, throwable -> Log.e("my", throwable.getMessage()));
    }

    public void setProfile(User user) {
        user.id = AuthData.getIDInt();
        if (user.id == 0)
            userReader.request(0).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(user1 -> {
                                user.id = user1.id;
                                AuthData.id = user.id();
                                userWriter.edit(user)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(AndroidSchedulers.mainThread())
                                        .subscribe(getViewState()::onComplete, throwable -> Log.e("My", throwable.getMessage()));
                            }
                    );
        else
            userWriter.edit(user)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getViewState()::onComplete, throwable -> Log.e("My", throwable.getMessage()));
    }

    public void getCity() {
        userReader.request(AuthData.getIDInt())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        model -> getViewState().setCity(model.city),
                        throwable -> Log.e("my", throwable.getMessage()));
    }
}
