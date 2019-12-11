package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.mvp.views.FilterView;

@InjectViewState
@SuppressLint("CheckResult")
public class FilterPresenter extends BasePresenter<FilterView> {

    private FilterModel filterModel = new FilterModel();

    public FilterPresenter() {
        Injector.get().getApp().inject(this);
    }

    public void loadCity(){
        getViewState().setCity(AuthData.city);
    }

    public void initFilterModel(FilterModel filterModel) {
        this.filterModel = filterModel;
    }

    public void finish(){
        getViewState().finish(filterModel);
    }
}
