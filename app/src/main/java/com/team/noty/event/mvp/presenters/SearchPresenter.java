package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.AsyncList;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.ActUpdate;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.mvp.views.SearchView;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.utils.Pair;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
@SuppressLint("CheckResult")
public class SearchPresenter extends BasePresenter<SearchView> {

    @Inject
    ContentApi api;
    @Inject
    MainApi mainApi;
    @Inject
    AsyncList<ActId> banedActList;
    @Inject
    Interactor<Observable<Act>, FilterModel> filterInteractor;
    @Inject
    Interactor<Completable, ActUpdate> inOutInteractor;

    private Disposable filterDisp;
    private Pair<FilterModel, List<Act>> lastValidRequest =
            new Pair<>(new FilterModel(), new ArrayList<>());

    public SearchPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void loadList() {
        request(lastValidRequest.getLeft());
    }

    public void requestIn(ActId actId) {
        inOutInteractor.call(ActUpdate.ENTER.setAct(actId))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showInfo(R.string.accept), this::onError);
    }

    public void openFilter(){
        getViewState().openFilter(lastValidRequest.getLeft());
    }

    public void removeId(ActId actId) {
        banedActList.add(actId)
                .subscribe();
    }

    public void loadList(String word) {
        request(lastValidRequest.getLeft().setWord(word));
    }

    public void loadListWithCategory(String categoryId) {
        request(lastValidRequest.getLeft().setCat(categoryId));
    }

    public void loadListWithCategoryAndDate(String cat, String date) {
        request(lastValidRequest.getLeft().setCat(cat).setDate(date));
    }

    public void request(FilterModel filter) {
        disposeFilter();
        filterDisp = filterInteractor.call(filter)
                .toList()
                .onErrorResumeNext(Single.just(lastValidRequest.getRight()))
                .zipWith(Single.just(filter), this::setLastValidRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showList);
    }

    private List<Act> setLastValidRequest(List<Act> list, FilterModel filter) {
        this.lastValidRequest = new Pair<>(filter, list);
        return list;
    }

    @Override
    public void detachView(SearchView view) {
        super.detachView(view);
        disposeFilter();
    }

    private void disposeFilter() {
        if (filterDisp != null && !filterDisp.isDisposed())
            filterDisp.dispose();
    }
}
