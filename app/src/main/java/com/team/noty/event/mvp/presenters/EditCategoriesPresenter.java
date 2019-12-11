package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.pacoworks.rxpaper2.RxPaperBook;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.dagger2.qualifiers.PaperBook;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.Updater;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.EditCategoriesView;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.ui.activities.EditCategoriesActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class EditCategoriesPresenter extends BasePresenter<EditCategoriesView> {

    @Inject
    Updater<User, Integer> updater;

    @Inject
    ReaderRepos<User, Integer> userReader;

    @Inject
    DataProvide<List<Category>> categoriesProvider;

    @Inject
    MainApi api;
    @Inject
    ContentApi contentApi;
    @Inject
    @PaperBook(PaperBook.TYPE.USERS)
    RxPaperBook book;
    private List<String> categories;

    public EditCategoriesPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void loadList() {
        categoriesProvider.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(getViewState()::loadAllCategories)
                .observeOn(Schedulers.io())
                .toCompletable()
                .andThen(userReader.request(AuthData.getIDInt()))
                .flatMapObservable(user -> Observable.fromIterable(user.categories))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::checkCategory,throwable -> Log.e("my",throwable.getMessage()));
    }

    public void sendCategories(List<String> categoryIds) {
        categories = categoryIds;
        EditCategoriesActivity.categories = categories;
        updater.update(AuthData.getIDInt(), this::updateUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onComplete);
    }

    private void updateUser(User user) {
        user.categories = categories;
    }
}
