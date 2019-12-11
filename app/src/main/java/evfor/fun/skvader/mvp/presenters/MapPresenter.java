package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.mvp.views.MapContentView;
import evfor.fun.skvader.repository.data_provide.DataProvide;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class MapPresenter extends LocationPresenter<MapContentView> {

    @Inject
    Interactor<Observable<Act>, Location> actByLocation;
    @Inject
    DataProvide<Location> lastLocation;

    public MapPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void getList() {
        lastLocation.provide()
                .flatMapObservable(actByLocation::call)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        model->getViewState().addPoint(model),
                        throwable -> Log.e("my",throwable.getMessage()));
    }

    public void getMyLocation() {
        lastLocation.provide()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::setCurrentLoc,throwable -> getViewState().setCurrentLoc(new Location(39,50)));
    }
}
