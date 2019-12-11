package com.team.noty.event.mvp.presenters;




import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.mvp.views.RestoreView;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.api.LoginApi;
import com.team.noty.event.utils.ErrorHandler;

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
