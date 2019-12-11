package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.mvp.views.SelectCityView;
import evfor.fun.skvader.network.models.request.RqCity;
import evfor.fun.skvader.models.City;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
@SuppressLint("CheckResult")
public class SelectCityPresenter extends BasePresenter<SelectCityView> {

    @Inject
    Interactor<Observable<City>, RqCity> cityProvider;
    private Disposable disposable;

    public SelectCityPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void search(String text) {
        dispose();
        request(new RqCity(text));
    }

    public void search(evfor.fun.skvader.models.Location latLng) {
        dispose();
        request(new RqCity(latLng.latitude, latLng.longitude));
    }

    private void dispose() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void request(RqCity city) {
        disposable = cityProvider.call(city)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showList, this::onError);
    }
}
