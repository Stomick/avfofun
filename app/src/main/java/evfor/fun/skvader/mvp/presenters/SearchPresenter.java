package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.AsyncList;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.ActUpdate;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.mvp.views.SearchView;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.utils.Pair;

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
