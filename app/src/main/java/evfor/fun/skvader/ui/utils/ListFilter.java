package evfor.fun.skvader.ui.utils;

import evfor.fun.skvader.utils.Predicatable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListFilter<T extends Predicatable> implements iListFilter<T> {
    private List<T> allList;
    private Disposable filter;

    public ListFilter(List<T> allList) {
        this.allList = new ArrayList<>();
        this.allList.addAll(allList);
    }

    @Override
    public Observable<ChangeList<T>> filter(String word) {
        dispose();
        return Observable.just(allList)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::setFilter)
                .zipWith(Observable.just(word), this::zipFilter)
                .onErrorReturn(this::returnAll);
    }

    private ChangeList<T> returnAll(Throwable throwable) {
        ChangeList<T> changeList = new ChangeList<>();
        changeList.getToAdd().addAll(allList);
        return changeList;
    }

    private ChangeList<T> zipFilter(List<T> list, String word) {
        ChangeList<T> changeList = new ChangeList<>();
        for (T t : list)
            if (t.contain(word))
                changeList.getToAdd().add(t);
            else changeList.getToRemove().add(t);
        return changeList;
    }

    public void setFilter(Disposable filter) {
        this.filter = filter;
    }

    private void dispose() {
        if (filter != null && !filter.isDisposed())
            filter.dispose();
    }
}
