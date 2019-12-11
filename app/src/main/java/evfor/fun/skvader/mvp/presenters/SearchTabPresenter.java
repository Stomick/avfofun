package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.mvp.views.tabs.SearchTabView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class SearchTabPresenter extends BasePresenter<SearchTabView> {

    @Inject
    DataProvide<List<Category>> provideCategories;
    @Inject
    Interactor<Observable<Act>, FilterModel> filter;

    public SearchTabPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void getCategories() {
        provideCategories.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showCategory);
    }

    public void getPopular() {
        filter.call(new FilterModel().setPop(FilterModel.Popular.up).setCity(AuthData.city))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showEvents, this::onError);
    }
}
