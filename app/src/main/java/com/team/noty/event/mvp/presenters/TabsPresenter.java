package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.pacoworks.rxpaper2.RxPaperBook;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.dagger2.qualifiers.PaperBook;
import com.team.noty.event.models.NotificationCount;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.TabbetView;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsProfile;
import com.team.noty.event.network.models.response.RsSoc;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.Updater;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.utils.Converter;
import com.team.noty.event.utils.EventBus;
import com.team.noty.event.utils.FilterCreator;
import com.team.noty.event.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class TabsPresenter extends BaseEventBusPresenter<TabbetView> {

    @Inject
    ReaderRepos<User, Integer> userReader;
    @Inject
    Updater<User, Integer> userUpdater;
    @Inject
    DataProvide<NotificationCount> notificationCountProvide;

    public TabsPresenter() {
        Injector.get().getMain().inject(this);
    }

    private Single<User> getUser() {
        return userReader.request(AuthData.getIDInt())
                .doOnSuccess(user -> AuthData.city = user.city)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void loadProfile() {
        getUser().subscribe(this::setUser, this::onError);
    }
    public void  updateNotificationCount(){
//        notificationCountProvide.provide()
//                .subscribe(EventBus::post, this::onError);
    }

    public void updateProfile() {
        userUpdater.update(AuthData.getIDInt(), this::updateUser).subscribe();
    }

    private void updateUser(User user) {
        AuthData.id = String.valueOf(user.id);
        Injector.get().getApp().accountPreferenceManager().saveFromData();
        //EventBus.post(user);
    }

    private void setUser(User user) {
        updateUser(user);
        getViewState().onComplete();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setNotCount(NotificationCount notCount) {
        int notyCount = StringUtils.toIntOr0(notCount.count);
        if (notCount.addCount == 0)
            getViewState().setNotificationCount(notCount.count);
        else getViewState().setNotificationCount(String.valueOf(notyCount + notCount.addCount));
    }
}
