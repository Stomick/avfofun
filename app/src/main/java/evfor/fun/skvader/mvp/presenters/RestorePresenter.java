package evfor.fun.skvader.mvp.presenters;




import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.mvp.views.RestoreView;
import evfor.fun.skvader.network.api.LoginApi;
import evfor.fun.skvader.utils.ErrorHandler;

import javax.inject.Inject;

@InjectViewState
public class RestorePresenter extends BasePresenter<RestoreView> implements ErrorHandler.ErrorDisplay{
    @Inject
    LoginApi api;

    public RestorePresenter() {
        Injector.get().getApp().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void restore(String mail){
//        api(api.restore(ContentApi.APIKEY, mail))
//
//                .subscribe(getViewState()::onComplete, this::onError);

    }

    @Override
    void onError(Throwable throwable) {

    }

    @Override
    public void display(String title, String message) {
        getViewState().showInfo(R.string.user_not_found);
    }
}
