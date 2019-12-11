package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.RegPrewView;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.WriterRepos;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.utils.PermissionController;

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
