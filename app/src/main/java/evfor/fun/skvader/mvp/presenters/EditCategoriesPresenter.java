package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.pacoworks.rxpaper2.RxPaperBook;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.dagger2.qualifiers.PaperBook;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.Updater;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.EditCategoriesView;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.ui.activities.EditCategoriesActivity;

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
